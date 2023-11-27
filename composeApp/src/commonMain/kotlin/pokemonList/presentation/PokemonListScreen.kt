package pokemonList.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.InvalidatingPagingSourceFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import core.configs.HttpClientKeeper
import pokemonDetails.data.api.PokemonInfoApiImpl
import pokemonDetails.data.dto.PokemonInfoDTO
import pokemonDetails.data.mapper.toPokemonSmallItemModel
import pokemonList.data.api.PokemonListApiImpl
import pokemonList.data.paging.PokemonListCacher
import pokemonList.data.paging.PokemonListPagingSource
import pokemonList.data.paging.PokemonListRemoteMediator
import pokemonList.data.paging.localCache

@OptIn(ExperimentalPagingApi::class)
@Composable
fun PokemonListScreen() {
    val viewModel = remember {
        val factory = InvalidatingPagingSourceFactory {
            PokemonListPagingSource()
        }

        val remoteMediator = PokemonListRemoteMediator(
            PokemonListApiImpl(HttpClientKeeper.httpClient),
            PokemonInfoApiImpl(HttpClientKeeper.httpClient),
            object : PokemonListCacher {
                override suspend fun cachePokemons(
                    page: Int,
                    pokemons: List<PokemonInfoDTO>
                ) {
                    localCache[page] = pokemons.map { it.toPokemonSmallItemModel() }
                }
            },
            factory
        )

        PokemonListViewModel(
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 60,
                    enablePlaceholders = true,
                    maxSize = 120,
                ),
                remoteMediator = remoteMediator,
                pagingSourceFactory = factory,
            )
        )
    }

    val pokemons = viewModel.getPokemons().collectAsLazyPagingItems()

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        columns = GridCells.Fixed(3),
    ) {
        item(span = { GridItemSpan(3) }) {
            Text(
                modifier = Modifier.statusBarsPadding(),
                text = "POKEMONS",
                fontSize = 50.sp,
            )
        }

        items(
            count = pokemons.itemCount,
            key = { it },
        ) { index ->
            pokemons[index]?.let { pokemon ->
                PokemonItem(
                    modifier = Modifier.padding(all = 8.dp),
                    url = pokemon.imageUrl,
                    name = pokemon.name,
                    color = Color.Black,
                    number = pokemon.id.toString(),
                    onClick = {},
                )
            }
        }

        pokemons.loadState.apply {
            when {
                refresh is LoadStateNotLoading && pokemons.itemCount < 1 -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(text = "No Items")
                        }
                    }
                }

                refresh is LoadStateLoading -> {
                    item(span = { GridItemSpan(3) }) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.background,
                            )
                        }
                    }
                }

                append is LoadStateLoading -> {
                    item(span = { GridItemSpan(3) }) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.background,
                            )
                        }
                    }
                }

                refresh is LoadStateError -> {
                    item {
                        ErrorItem(
                            message = "No Internet Connection.",
                            onClickRetry = pokemons::retry,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }

                append is LoadStateError -> {
                    item(span = { GridItemSpan(3) }) {
                        ErrorItem(
                            message = "No Internet Connection",
                            onClickRetry = pokemons::retry,
                        )
                    }
                }
            }
        }

        item(span = { GridItemSpan(3) }) {
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Composable
private fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            color = Color.Red,
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "Try again")
        }
    }
}
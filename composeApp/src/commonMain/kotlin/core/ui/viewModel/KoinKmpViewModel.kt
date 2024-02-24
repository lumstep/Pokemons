package core.ui.viewModel

import androidx.compose.runtime.Composable
import com.hoc081098.kmp.viewmodel.*
import com.hoc081098.kmp.viewmodel.compose.LocalSavedStateHandleFactory
import com.hoc081098.kmp.viewmodel.compose.defaultPlatformCreationExtras
import com.hoc081098.kmp.viewmodel.compose.kmpViewModel
import org.koin.core.component.KoinScopeComponent

@MainThread
@Composable
inline fun <reified VM : ViewModel> koinKmpViewModel(
    key: String? = null,
    extras: CreationExtras = defaultPlatformCreationExtras(),
    savedStateHandleFactory: SavedStateHandleFactory? = LocalSavedStateHandleFactory.current,
    crossinline factory: () -> KoinScopeComponent,
): VM = kmpViewModel(
    factory = viewModelFactory {
        val scope = factory().scope
        scope.get<VM>().apply {
            addCloseable { scope.close() }
        }
    },
    extras = extras,
    key = key,
    savedStateHandleFactory = savedStateHandleFactory,
)

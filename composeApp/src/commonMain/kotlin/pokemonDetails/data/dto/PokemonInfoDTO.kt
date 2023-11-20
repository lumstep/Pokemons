package pokemonDetails.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PokemonInfoDTO(
    @SerialName("abilities")
    val abilities: List<Ability?>?,
    @SerialName("base_experience")
    val baseExperience: Int?, // 113
    @SerialName("forms")
    val forms: List<Form?>?,
    @SerialName("game_indices")
    val gameIndices: List<GameIndice?>?,
    @SerialName("height")
    val height: Int?, // 6
    @SerialName("held_items")
    val heldItems: List<HeldItem?>?,
    @SerialName("id")
    val id: Int, // 35
    @SerialName("is_default")
    val isDefault: Boolean?, // true
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String?, // /api/v2/pokemon/35/encounters
    @SerialName("moves")
    val moves: List<Move?>?,
    @SerialName("name")
    val name: String?, // clefairy
    @SerialName("order")
    val order: Int?, // 56
    @SerialName("past_types")
    val pastTypes: List<PastType?>?,
    @SerialName("species")
    val species: Species?,
    @SerialName("sprites")
    val sprites: Sprites?,
    @SerialName("stats")
    val stats: List<Stat?>?,
    @SerialName("types")
    val types: List<Type?>?,
    @SerialName("weight")
    val weight: Int? // 75
) {
    @Serializable
    class Ability(
        @SerialName("ability")
        val ability: Ability?,
        @SerialName("is_hidden")
        val isHidden: Boolean?, // true
        @SerialName("slot")
        val slot: Int? // 3
    ) {
        @Serializable
        class Ability(
            @SerialName("name")
            val name: String?, // friend-guard
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/ability/132/
        )
    }

    @Serializable
    class Form(
        @SerialName("name")
        val name: String?, // clefairy
        @SerialName("url")
        val url: String? // https://pokeapi.co/api/v2/pokemon-form/35/
    )

    @Serializable
    class GameIndice(
        @SerialName("game_index")
        val gameIndex: Int?, // 35
        @SerialName("version")
        val version: Version?
    ) {
        @Serializable
        class Version(
            @SerialName("name")
            val name: String?, // white-2
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/version/22/
        )
    }

    @Serializable
    class HeldItem(
        @SerialName("item")
        val item: Item?,
        @SerialName("version_details")
        val versionDetails: List<VersionDetail?>?
    ) {
        @Serializable
        class Item(
            @SerialName("name")
            val name: String?, // moon-stone
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/item/81/
        )

        @Serializable
        class VersionDetail(
            @SerialName("rarity")
            val rarity: Int?, // 5
            @SerialName("version")
            val version: Version?
        ) {
            @Serializable
            class Version(
                @SerialName("name")
                val name: String?, // ruby
                @SerialName("url")
                val url: String? // https://pokeapi.co/api/v2/version/7/
            )
        }
    }

    @Serializable
    class Move(
        @SerialName("move")
        val move: Move?,
        @SerialName("version_group_details")
        val versionGroupDetails: List<VersionGroupDetail?>?
    ) {
        @Serializable
        class Move(
            @SerialName("name")
            val name: String?, // pound
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/move/1/
        )

        @Serializable
        class VersionGroupDetail(
            @SerialName("level_learned_at")
            val levelLearnedAt: Int?, // 1
            @SerialName("move_learn_method")
            val moveLearnMethod: MoveLearnMethod?,
            @SerialName("version_group")
            val versionGroup: VersionGroup?
        ) {
            @Serializable
            class MoveLearnMethod(
                @SerialName("name")
                val name: String?, // level-up
                @SerialName("url")
                val url: String? // https://pokeapi.co/api/v2/move-learn-method/1/
            )

            @Serializable
            class VersionGroup(
                @SerialName("name")
                val name: String?, // red-blue
                @SerialName("url")
                val url: String? // https://pokeapi.co/api/v2/version-group/1/
            )
        }
    }

    @Serializable
    class PastType(
        @SerialName("generation")
        val generation: Generation?,
        @SerialName("types")
        val types: List<Type?>?
    ) {
        @Serializable
        class Generation(
            @SerialName("name")
            val name: String?, // generation-v
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/generation/5/
        )

        @Serializable
        class Type(
            @SerialName("slot")
            val slot: Int?, // 1
            @SerialName("type")
            val type: Type?
        ) {
            @Serializable
            class Type(
                @SerialName("name")
                val name: String?, // normal
                @SerialName("url")
                val url: String? // https://pokeapi.co/api/v2/type/1/
            )
        }
    }

    @Serializable
    class Species(
        @SerialName("name")
        val name: String?, // clefairy
        @SerialName("url")
        val url: String? // https://pokeapi.co/api/v2/pokemon-species/35/
    )

    @Serializable
    class Sprites(
        @SerialName("back_default")
        val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/35.png
        @SerialName("back_female")
        val backFemale: String?, // null
        @SerialName("back_shiny")
        val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/35.png
        @SerialName("back_shiny_female")
        val backShinyFemale: String?, // null
        @SerialName("front_default")
        val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png
        @SerialName("front_female")
        val frontFemale: String?, // null
        @SerialName("front_shiny")
        val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/35.png
        @SerialName("front_shiny_female")
        val frontShinyFemale: String?, // null
        @SerialName("other")
        val other: Other?,
        @SerialName("versions")
        val versions: Versions?
    ) {
        @Serializable
        class Other(
            @SerialName("dream_world")
            val dreamWorld: DreamWorld?,
            @SerialName("home")
            val home: Home?,
            @SerialName("official-artwork")
            val officialArtwork: OfficialArtwork?
        ) {
            @Serializable
            class DreamWorld(
                @SerialName("front_default")
                val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/35.svg
                @SerialName("front_female")
                val frontFemale: String? // null
            )

            @Serializable
            class Home(
                @SerialName("front_default")
                val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/35.png
                @SerialName("front_female")
                val frontFemale: String?, // null
                @SerialName("front_shiny")
                val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/35.png
                @SerialName("front_shiny_female")
                val frontShinyFemale: String? // null
            )

            @Serializable
            class OfficialArtwork(
                @SerialName("front_default")
                val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/35.png
                @SerialName("front_female")
                val frontFemale: String?, // null
                @SerialName("front_shiny")
                val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/35.png
                @SerialName("front_shiny_female")
                val frontShinyFemale: String? // null
            )
        }

        @Serializable
        class Versions(
            @SerialName("generation-i")
            val generationI: GenerationI?,
            @SerialName("generation-ii")
            val generationIi: GenerationIi?,
            @SerialName("generation-iii")
            val generationIii: GenerationIii?,
            @SerialName("generation-iv")
            val generationIv: GenerationIv?,
            @SerialName("generation-v")
            val generationV: GenerationV?,
            @SerialName("generation-vi")
            val generationVi: GenerationVi?,
            @SerialName("generation-vii")
            val generationVii: GenerationVii?,
            @SerialName("generation-viii")
            val generationViii: GenerationViii?
        ) {
            @Serializable
            class GenerationI(
                @SerialName("red-blue")
                val redBlue: RedBlue?,
                @SerialName("yellow")
                val yellow: Yellow?
            ) {
                @Serializable
                class RedBlue(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/back/35.png
                    @SerialName("back_gray")
                    val backGray: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/back/gray/35.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/35.png
                    @SerialName("front_gray")
                    val frontGray: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/gray/35.png
                )

                @Serializable
                class Yellow(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/back/35.png
                    @SerialName("back_gray")
                    val backGray: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/back/gray/35.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/35.png
                    @SerialName("front_gray")
                    val frontGray: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/gray/35.png
                )
            }

            @Serializable
            class GenerationIi(
                @SerialName("crystal")
                val crystal: Crystal?,
                @SerialName("gold")
                val gold: Gold?,
                @SerialName("silver")
                val silver: Silver?
            ) {
                @Serializable
                class Crystal(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/back/35.png
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/back/shiny/35.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/35.png
                    @SerialName("front_shiny")
                    val frontShiny: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/shiny/35.png
                )

                @Serializable
                class Gold(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/back/35.png
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/back/shiny/35.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/35.png
                    @SerialName("front_shiny")
                    val frontShiny: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/shiny/35.png
                )

                @Serializable
                class Silver(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/back/35.png
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/back/shiny/35.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/35.png
                    @SerialName("front_shiny")
                    val frontShiny: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/shiny/35.png
                )
            }

            @Serializable
            class GenerationIii(
                @SerialName("emerald")
                val emerald: Emerald?,
                @SerialName("firered-leafgreen")
                val fireredLeafgreen: FireredLeafgreen?,
                @SerialName("ruby-sapphire")
                val rubySapphire: RubySapphire?
            ) {
                @Serializable
                class Emerald(
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/35.png
                    @SerialName("front_shiny")
                    val frontShiny: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/shiny/35.png
                )

                @Serializable
                class FireredLeafgreen(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/back/35.png
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/back/shiny/35.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/35.png
                    @SerialName("front_shiny")
                    val frontShiny: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/shiny/35.png
                )

                @Serializable
                class RubySapphire(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/back/35.png
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/back/shiny/35.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/35.png
                    @SerialName("front_shiny")
                    val frontShiny: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/shiny/35.png
                )
            }

            @Serializable
            class GenerationIv(
                @SerialName("diamond-pearl")
                val diamondPearl: DiamondPearl?,
                @SerialName("heartgold-soulsilver")
                val heartgoldSoulsilver: HeartgoldSoulsilver?,
                @SerialName("platinum")
                val platinum: Platinum?
            ) {
                @Serializable
                class DiamondPearl(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/back/35.png
                    @SerialName("back_female")
                    val backFemale: String?, // null
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/back/shiny/35.png
                    @SerialName("back_shiny_female")
                    val backShinyFemale: String?, // null
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/35.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/shiny/35.png
                    @SerialName("front_shiny_female")
                    val frontShinyFemale: String? // null
                )

                @Serializable
                class HeartgoldSoulsilver(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/back/35.png
                    @SerialName("back_female")
                    val backFemale: String?, // null
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/back/shiny/35.png
                    @SerialName("back_shiny_female")
                    val backShinyFemale: String?, // null
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/35.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/shiny/35.png
                    @SerialName("front_shiny_female")
                    val frontShinyFemale: String? // null
                )

                @Serializable
                class Platinum(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/back/35.png
                    @SerialName("back_female")
                    val backFemale: String?, // null
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/back/shiny/35.png
                    @SerialName("back_shiny_female")
                    val backShinyFemale: String?, // null
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/35.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/shiny/35.png
                    @SerialName("front_shiny_female")
                    val frontShinyFemale: String? // null
                )
            }

            @Serializable
            class GenerationV(
                @SerialName("black-white")
                val blackWhite: BlackWhite?
            ) {
                @Serializable
                class BlackWhite(
                    @SerialName("animated")
                    val animated: Animated?,
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/35.png
                    @SerialName("back_female")
                    val backFemale: String?, // null
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/shiny/35.png
                    @SerialName("back_shiny_female")
                    val backShinyFemale: String?, // null
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/35.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/shiny/35.png
                    @SerialName("front_shiny_female")
                    val frontShinyFemale: String? // null
                ) {
                    @Serializable
                    class Animated(
                        @SerialName("back_default")
                        val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/35.gif
                        @SerialName("back_female")
                        val backFemale: String?, // null
                        @SerialName("back_shiny")
                        val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/shiny/35.gif
                        @SerialName("back_shiny_female")
                        val backShinyFemale: String?, // null
                        @SerialName("front_default")
                        val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/35.gif
                        @SerialName("front_female")
                        val frontFemale: String?, // null
                        @SerialName("front_shiny")
                        val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/shiny/35.gif
                        @SerialName("front_shiny_female")
                        val frontShinyFemale: String? // null
                    )
                }
            }

            @Serializable
            class GenerationVi(
                @SerialName("omegaruby-alphasapphire")
                val omegarubyAlphasapphire: OmegarubyAlphasapphire?,
                @SerialName("x-y")
                val xY: XY?
            ) {
                @Serializable
                class OmegarubyAlphasapphire(
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/omegaruby-alphasapphire/35.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/omegaruby-alphasapphire/shiny/35.png
                    @SerialName("front_shiny_female")
                    val frontShinyFemale: String? // null
                )

                @Serializable
                class XY(
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/x-y/35.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/x-y/shiny/35.png
                    @SerialName("front_shiny_female")
                    val frontShinyFemale: String? // null
                )
            }

            @Serializable
            class GenerationVii(
                @SerialName("icons")
                val icons: Icons?,
                @SerialName("ultra-sun-ultra-moon")
                val ultraSunUltraMoon: UltraSunUltraMoon?
            ) {
                @Serializable
                class Icons(
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/35.png
                    @SerialName("front_female")
                    val frontFemale: String? // null
                )

                @Serializable
                class UltraSunUltraMoon(
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/35.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/shiny/35.png
                    @SerialName("front_shiny_female")
                    val frontShinyFemale: String? // null
                )
            }

            @Serializable
            class GenerationViii(
                @SerialName("icons")
                val icons: Icons?
            ) {
                @Serializable
                class Icons(
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/35.png
                    @SerialName("front_female")
                    val frontFemale: String? // null
                )
            }
        }
    }

    @Serializable
    class Stat(
        @SerialName("base_stat")
        val baseStat: Int?, // 35
        @SerialName("effort")
        val effort: Int?, // 0
        @SerialName("stat")
        val stat: Stat?
    ) {
        @Serializable
        class Stat(
            @SerialName("name")
            val name: String?, // speed
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/stat/6/
        )
    }

    @Serializable
    class Type(
        @SerialName("slot")
        val slot: Int?, // 1
        @SerialName("type")
        val type: Type?
    ) {
        @Serializable
        class Type(
            @SerialName("name")
            val name: String?, // fairy
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/type/18/
        )
    }
}
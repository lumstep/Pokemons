package pokemonDetails.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PokemonInfoDTO(
    @SerialName("abilities")
    val abilities: List<Ability?>?,
    @SerialName("base_experience")
    val baseExperience: Int?, // 64
    @SerialName("forms")
    val forms: List<Form?>?,
    @SerialName("game_indices")
    val gameIndices: List<GameIndice?>?,
    @SerialName("height")
    val height: Int?, // 7
    @SerialName("held_items")
    val heldItems: List<HeldItem?>?,
    @SerialName("id")
    val id: Int, // 1
    @SerialName("is_default")
    val isDefault: Boolean?, // true
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String?, // https://pokeapi.co/api/v2/pokemon/1/encounters
    @SerialName("moves")
    val moves: List<Move?>?,
    @SerialName("name")
    val name: String?, // bulbasaur
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
    val weight: Int? // 69
) {
    @Serializable
    class Ability(
        @SerialName("ability")
        val ability: Ability?,
        @SerialName("is_hidden")
        val isHidden: Boolean?, // false
        @SerialName("slot")
        val slot: Int? // 1
    ) {
        @Serializable
        class Ability(
            @SerialName("name")
            val name: String?, // overgrow
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/ability/65/
        )
    }

    @Serializable
    class Form(
        @SerialName("name")
        val name: String?, // bulbasaur
        @SerialName("url")
        val url: String? // https://pokeapi.co/api/v2/pokemon-form/1/
    )

    @Serializable
    class GameIndice(
        @SerialName("game_index")
        val gameIndex: Int?, // 153
        @SerialName("version")
        val version: Version?
    ) {
        @Serializable
        class Version(
            @SerialName("name")
            val name: String?, // red
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/version/1/
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
            val name: String?, // metal-powder
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/item/234/
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
            val name: String?, // razor-wind
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/move/13/
        )

        @Serializable
        class VersionGroupDetail(
            @SerialName("level_learned_at")
            val levelLearnedAt: Int?, // 0
            @SerialName("move_learn_method")
            val moveLearnMethod: MoveLearnMethod?,
            @SerialName("version_group")
            val versionGroup: VersionGroup?
        ) {
            @Serializable
            class MoveLearnMethod(
                @SerialName("name")
                val name: String?, // egg
                @SerialName("url")
                val url: String? // https://pokeapi.co/api/v2/move-learn-method/2/
            )

            @Serializable
            class VersionGroup(
                @SerialName("name")
                val name: String?, // gold-silver
                @SerialName("url")
                val url: String? // https://pokeapi.co/api/v2/version-group/3/
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
        val name: String?, // bulbasaur
        @SerialName("url")
        val url: String? // https://pokeapi.co/api/v2/pokemon-species/1/
    )

    @Serializable
    class Sprites(
        @SerialName("back_default")
        val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png
        @SerialName("back_female")
        val backFemale: String?, // null
        @SerialName("back_shiny")
        val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/1.png
        @SerialName("back_shiny_female")
        val backShinyFemale: String?, // null
        @SerialName("front_default")
        val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png
        @SerialName("front_female")
        val frontFemale: String?, // null
        @SerialName("front_shiny")
        val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png
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
                val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg
                @SerialName("front_female")
                val frontFemale: String? // null
            )

            @Serializable
            class Home(
                @SerialName("front_default")
                val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/1.png
                @SerialName("front_female")
                val frontFemale: String?, // null
                @SerialName("front_shiny")
                val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/1.png
                @SerialName("front_shiny_female")
                val frontShinyFemale: String? // null
            )

            @Serializable
            class OfficialArtwork(
                @SerialName("front_default")
                val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png
                @SerialName("front_shiny")
                val frontShiny: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/1.png
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
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/back/1.png
                    @SerialName("back_gray")
                    val backGray: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/back/gray/1.png
                    @SerialName("back_transparent")
                    val backTransparent: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/transparent/back/1.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/1.png
                    @SerialName("front_gray")
                    val frontGray: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/gray/1.png
                    @SerialName("front_transparent")
                    val frontTransparent: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/transparent/1.png
                )

                @Serializable
                class Yellow(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/back/1.png
                    @SerialName("back_gray")
                    val backGray: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/back/gray/1.png
                    @SerialName("back_transparent")
                    val backTransparent: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/transparent/back/1.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/1.png
                    @SerialName("front_gray")
                    val frontGray: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/gray/1.png
                    @SerialName("front_transparent")
                    val frontTransparent: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/transparent/1.png
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
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/back/1.png
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/back/shiny/1.png
                    @SerialName("back_shiny_transparent")
                    val backShinyTransparent: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/transparent/back/shiny/1.png
                    @SerialName("back_transparent")
                    val backTransparent: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/transparent/back/1.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/1.png
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/shiny/1.png
                    @SerialName("front_shiny_transparent")
                    val frontShinyTransparent: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/transparent/shiny/1.png
                    @SerialName("front_transparent")
                    val frontTransparent: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/transparent/1.png
                )

                @Serializable
                class Gold(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/back/1.png
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/back/shiny/1.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/1.png
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/shiny/1.png
                    @SerialName("front_transparent")
                    val frontTransparent: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/transparent/1.png
                )

                @Serializable
                class Silver(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/back/1.png
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/back/shiny/1.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/1.png
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/shiny/1.png
                    @SerialName("front_transparent")
                    val frontTransparent: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/transparent/1.png
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
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/1.png
                    @SerialName("front_shiny")
                    val frontShiny: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/shiny/1.png
                )

                @Serializable
                class FireredLeafgreen(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/back/1.png
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/back/shiny/1.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/1.png
                    @SerialName("front_shiny")
                    val frontShiny: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/shiny/1.png
                )

                @Serializable
                class RubySapphire(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/back/1.png
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/back/shiny/1.png
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/1.png
                    @SerialName("front_shiny")
                    val frontShiny: String? // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/shiny/1.png
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
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/back/1.png
                    @SerialName("back_female")
                    val backFemale: String?, // null
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/back/shiny/1.png
                    @SerialName("back_shiny_female")
                    val backShinyFemale: String?, // null
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/1.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/shiny/1.png
                    @SerialName("front_shiny_female")
                    val frontShinyFemale: String? // null
                )

                @Serializable
                class HeartgoldSoulsilver(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/back/1.png
                    @SerialName("back_female")
                    val backFemale: String?, // null
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/back/shiny/1.png
                    @SerialName("back_shiny_female")
                    val backShinyFemale: String?, // null
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/1.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/shiny/1.png
                    @SerialName("front_shiny_female")
                    val frontShinyFemale: String? // null
                )

                @Serializable
                class Platinum(
                    @SerialName("back_default")
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/back/1.png
                    @SerialName("back_female")
                    val backFemale: String?, // null
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/back/shiny/1.png
                    @SerialName("back_shiny_female")
                    val backShinyFemale: String?, // null
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/1.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/shiny/1.png
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
                    val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/1.png
                    @SerialName("back_female")
                    val backFemale: String?, // null
                    @SerialName("back_shiny")
                    val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/shiny/1.png
                    @SerialName("back_shiny_female")
                    val backShinyFemale: String?, // null
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/1.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/shiny/1.png
                    @SerialName("front_shiny_female")
                    val frontShinyFemale: String? // null
                ) {
                    @Serializable
                    class Animated(
                        @SerialName("back_default")
                        val backDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/1.gif
                        @SerialName("back_female")
                        val backFemale: String?, // null
                        @SerialName("back_shiny")
                        val backShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/shiny/1.gif
                        @SerialName("back_shiny_female")
                        val backShinyFemale: String?, // null
                        @SerialName("front_default")
                        val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/1.gif
                        @SerialName("front_female")
                        val frontFemale: String?, // null
                        @SerialName("front_shiny")
                        val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/shiny/1.gif
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
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/omegaruby-alphasapphire/1.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/omegaruby-alphasapphire/shiny/1.png
                    @SerialName("front_shiny_female")
                    val frontShinyFemale: String? // null
                )

                @Serializable
                class XY(
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/x-y/1.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/x-y/shiny/1.png
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
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/1.png
                    @SerialName("front_female")
                    val frontFemale: String? // null
                )

                @Serializable
                class UltraSunUltraMoon(
                    @SerialName("front_default")
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/1.png
                    @SerialName("front_female")
                    val frontFemale: String?, // null
                    @SerialName("front_shiny")
                    val frontShiny: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/shiny/1.png
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
                    val frontDefault: String?, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/1.png
                    @SerialName("front_female")
                    val frontFemale: String? // null
                )
            }
        }
    }

    @Serializable
    class Stat(
        @SerialName("base_stat")
        val baseStat: Int?, // 45
        @SerialName("effort")
        val effort: Int?, // 0
        @SerialName("stat")
        val stat: Stat?
    ) {
        @Serializable
        class Stat(
            @SerialName("name")
            val name: String?, // hp
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/stat/1/
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
            val name: String?, // grass
            @SerialName("url")
            val url: String? // https://pokeapi.co/api/v2/type/12/
        )
    }
}
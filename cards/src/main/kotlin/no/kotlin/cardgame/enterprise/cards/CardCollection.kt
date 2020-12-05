package no.kotlin.cardgame.enterprise.cards

import no.kotlin.cardgame.enterprise.cards.dto.CardDto
import no.kotlin.cardgame.enterprise.cards.dto.CollectionDto
import no.kotlin.cardgame.enterprise.cards.dto.Rarity.*


object CardCollection{

    fun get() : CollectionDto{

        val dto = CollectionDto()

        dto.prices.run {
            put(BRONZE, 100)
            put(SILVER, 500)
            put(GOLD,   1_000)
            put(PINK_DIAMOND, 100_000)
        }

        dto.prices.forEach { dto.millValues[it.key] = it.value / 4 }

        dto.hallProbabilities.run {
            put(SILVER, 0.10)
            put(GOLD, 0.01)
            put(PINK_DIAMOND, 0.001)
            put(BRONZE, 1 - get(SILVER)!! - get(GOLD)!! - get(PINK_DIAMOND)!!)
        }

        addCards(dto)

        return dto
    }

    private fun addCards(dto: CollectionDto){

        dto.movies.run {
            add(CardDto("c000", "Movie 1", "Movie 1lore ipsum", BRONZE, "035-monster.svg"))
            add(CardDto("c001", "Movie 2", "Movie 2 lore ipsum", BRONZE, "056-monster.svg"))
            add(CardDto("c002", "Movie 3", "lore ipsum", BRONZE, "070-monster.svg"))
            add(CardDto("c003", "Movie 4", "lore ipsum", BRONZE, "100-monster.svg"))
            add(CardDto("c004", "Movie 5", "lore ipsum", BRONZE, "075-monster.svg"))
            add(CardDto("c005", "Movie 6", "lore ipsum", BRONZE, "055-monster.svg"))
            add(CardDto("c006", "Movie 7", "lore ipsum", BRONZE, "063-monster.svg"))
            add(CardDto("c007", "Movie 8", "lore ipsum", BRONZE, "050-monster.svg"))
            add(CardDto("c008", "Movie 9", "lore ipsum", BRONZE, "019-monster.svg"))
            add(CardDto("c009", "Movie 10", "lore ipsum", BRONZE, "006-monster.svg"))
            add(CardDto("c010", "Movie 11", "lore ipsum", SILVER, "081-monster.svg"))
            add(CardDto("c011", "Movie 12", "lore ipsum", SILVER, "057-monster.svg"))
            add(CardDto("c012", "Movie 13", "lore ipsum", SILVER, "028-monster.svg"))
            add(CardDto("c013", "Movie 14", "lore ipsum", SILVER, "032-monster.svg"))
            add(CardDto("c014", "Movie 15", "lore ipsum", SILVER, "002-monster.svg"))
            add(CardDto("c015", "Movie 16", "lore ipsum", GOLD, "036-monster.svg"))
            add(CardDto("c016", "Movie 17", "lore ipsum", GOLD, "064-monster.svg"))
            add(CardDto("c017", "Movie 18", "lore ipsum", GOLD, "044-monster.svg"))
            add(CardDto("c018", "Movie 19", "lore ipsum", GOLD, "041-monster.svg"))
            add(CardDto("c019", "Movie 20", "lore ipsum", PINK_DIAMOND, "051-monster.svg"))
        }

        assert(dto.movies.size == dto.movies.map { it.movieId }.toSet().size)
        assert(dto.movies.size == dto.movies.map { it.name }.toSet().size)
        assert(dto.movies.size == dto.movies.map { it.imageId }.toSet().size)
    }

}
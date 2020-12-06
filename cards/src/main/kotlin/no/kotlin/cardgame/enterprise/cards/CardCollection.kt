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
            add(CardDto("c000", "Movie 1", "Movie 1 lore ipsum", "Director 1", 2001 ,BRONZE, "035-monster.svg"))
            add(CardDto("c001", "Movie 2", "Movie 2 lore ipsum","Director 2", 2002 ,BRONZE, "056-monster.svg"))
            add(CardDto("c002", "Movie 3", "Movie 3 lore ipsum", "Director 3", 2003, BRONZE, "070-monster.svg"))
            add(CardDto("c003", "Movie 4", "Movie 4 lore ipsum", "Director 4", 2004, BRONZE, "100-monster.svg"))
            add(CardDto("c004", "Movie 5", "Movie 5 lore ipsum", "Director 5", 2005, BRONZE, "075-monster.svg"))
            add(CardDto("c005", "Movie 6", "Movie 6 lore ipsum", "Director 6", 2006, BRONZE, "055-monster.svg"))
            add(CardDto("c006", "Movie 7", "Movie 7 lore ipsum", "Director 7", 2007, BRONZE, "063-monster.svg"))
            add(CardDto("c007", "Movie 8", "Movie 8 lore ipsum", "Director 8", 2008, BRONZE, "050-monster.svg"))
            add(CardDto("c008", "Movie 9", "Movie 9 lore ipsum","Director 9", 2009, BRONZE, "019-monster.svg"))
            add(CardDto("c009", "Movie 10", "Movie 10 lore ipsum", "Director 10", 2010, BRONZE, "006-monster.svg"))
            add(CardDto("c010", "Movie 11", "Movie 11 lore ipsum", "Director 11", 2011, SILVER, "081-monster.svg"))
            add(CardDto("c011", "Movie 12", "Movie 12 lore ipsum", "Director 12", 2012, SILVER, "057-monster.svg"))
            add(CardDto("c012", "Movie 13", "Movie 13 lore ipsum", "Director 13", 2013, SILVER, "028-monster.svg"))
            add(CardDto("c013", "Movie 14", "Movie 14 lore ipsum", "Director 14", 2014, SILVER, "032-monster.svg"))
            add(CardDto("c014", "Movie 15", "Movie 15 lore ipsum", "Director 15", 2015, SILVER, "002-monster.svg"))
            add(CardDto("c015", "Movie 16", "Movie 16 lore ipsum", "Director 16", 2016, GOLD, "036-monster.svg"))
            add(CardDto("c016", "Movie 17", "Movie 17 lore ipsum", "Director 17", 2017, GOLD, "064-monster.svg"))
            add(CardDto("c017", "Movie 18", "Movie 18 lore ipsum", "Director 18", 2018, GOLD, "044-monster.svg"))
            add(CardDto("c018", "Movie 19", "Movie 19 lore ipsum", "Director 91", 2019,GOLD, "041-monster.svg"))
            add(CardDto("c019", "Movie 20", "Movie 20 lore ipsum", "Director 20", 2020, PINK_DIAMOND, "051-monster.svg"))
        }

        assert(dto.movies.size == dto.movies.map { it.movieId }.toSet().size)
        assert(dto.movies.size == dto.movies.map { it.name }.toSet().size)
        assert(dto.movies.size == dto.movies.map { it.imageId }.toSet().size)
    }

}
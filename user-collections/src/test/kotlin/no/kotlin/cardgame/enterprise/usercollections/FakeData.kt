package no.kotlin.cardgame.enterprise.usercollections

import no.kotlin.cardgame.enterprise.cards.dto.CardDto
import no.kotlin.cardgame.enterprise.cards.dto.CollectionDto
import no.kotlin.cardgame.enterprise.cards.dto.Rarity.*


object FakeData {

    fun getCollectionDto() : CollectionDto {

        val dto = CollectionDto()

        dto.prices[BRONZE] = 100
        dto.prices[SILVER] = 500
        dto.prices[GOLD] = 1_000
        dto.prices[PINK_DIAMOND] = 100_000

        dto.prices.forEach { dto.millValues[it.key] = it.value / 4 }
        dto.prices.keys.forEach { dto.hallProbabilities[it] = 0.25 }

        dto.movies.run {
            add(CardDto(movieId = "c00", hall = BRONZE))
            add(CardDto(movieId = "c01", hall = BRONZE))
            add(CardDto(movieId = "c02", hall = BRONZE))
            add(CardDto(movieId = "c03", hall = BRONZE))
            add(CardDto(movieId = "c04", hall = SILVER))
            add(CardDto(movieId = "c05", hall = SILVER))
            add(CardDto(movieId = "c06", hall = SILVER))
            add(CardDto(movieId = "c07", hall = GOLD))
            add(CardDto(movieId = "c08", hall = GOLD))
            add(CardDto(movieId = "c09", hall = PINK_DIAMOND))
        }

        return dto
    }
}
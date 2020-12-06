package no.kotlin.cardgame.enterprise.usercollections.model

import no.kotlin.cardgame.enterprise.cards.dto.CardDto
import no.kotlin.cardgame.enterprise.cards.dto.Rarity


data class Card(
        val movieId : String,
        val hall: Rarity
){

    constructor(dto: CardDto): this(
            dto.movieId ?: throw IllegalArgumentException("Null cardId"),
            dto.hall ?: throw IllegalArgumentException("Null rarity"))
}
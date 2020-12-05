package no.kotlin.cardgame.enterprise.usercollections.model

import no.kotlin.cardgame.enterprise.cards.dto.CardDto
import no.kotlin.cardgame.enterprise.cards.dto.Rarity


data class Card(
        val cardId : String,
        val rarity: Rarity
){

    constructor(dto: CardDto): this(
            dto.cardId ?: throw IllegalArgumentException("Null cardId"),
            dto.rarity ?: throw IllegalArgumentException("Null rarity"))
}
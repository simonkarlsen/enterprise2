package no.kotlin.cardgame.enterprise.usercollections.model

import no.kotlin.cardgame.enterprise.cards.dto.CollectionDto
import no.kotlin.cardgame.enterprise.cards.dto.Rarity
import kotlin.math.abs


data class Collection(

    val cards : List<Card>,

    val prices: Map<Rarity, Int>,

    val millValues: Map<Rarity, Int>,

    val rarityProbabilities: Map<Rarity, Double>
){

    constructor(dto: CollectionDto) : this(
            dto.movies.map { Card(it) },
            dto.prices.toMap(),
            dto.millValues.toMap(),
            dto.hallProbabilities.toMap()
    )

    val cardsByRarity : Map<Rarity, List<Card>> = cards.groupBy { it.hall }

    init{
        if(cards.isEmpty()){
            throw IllegalArgumentException("No cards")
        }
        Rarity.values().forEach {
            requireNotNull(prices[it])
            requireNotNull(millValues[it])
            requireNotNull(rarityProbabilities[it])
        }

        val p = rarityProbabilities.values.sum()
        if(abs(1 - p) > 0.00001){
            throw IllegalArgumentException("Invalid probability sum: $p")
        }
    }
}
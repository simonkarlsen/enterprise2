package no.kotlin.cardgame.enterprise.usercollections

import no.kotlin.cardgame.enterprise.cards.dto.Rarity
import no.kotlin.cardgame.enterprise.cards.dto.CollectionDto
import no.kotlin.cardgame.enterprise.rest.dto.WrappedResponse
import no.kotlin.cardgame.enterprise.usercollections.model.Card
import no.kotlin.cardgame.enterprise.usercollections.model.Collection

import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.util.UriComponentsBuilder
import org.springframework.core.ParameterizedTypeReference
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory

import java.lang.Exception
import kotlin.random.Random
import org.slf4j.LoggerFactory
import javax.annotation.PostConstruct


@Service
class CardService(
        private val client: RestTemplate,
        private val circuitBreakerFactory: Resilience4JCircuitBreakerFactory
) {

    companion object{
        private val log = LoggerFactory.getLogger(CardService::class.java)
    }

    protected var collection: Collection? = null

    @Value("\${cardServiceAddress}")
    private lateinit var cardServiceAddress: String

    val cardCollection : List<Card>
        get() = collection?.cards ?: listOf()

    private val lock = Any()

    private lateinit var cb: CircuitBreaker


    @PostConstruct
    fun init(){

        cb = circuitBreakerFactory.create("circuitBreakerToCards")

        synchronized(lock){
            if(cardCollection.isNotEmpty()){
                return
            }
            fetchData()
        }
    }

    fun isInitialized() = cardCollection.isNotEmpty()

    protected fun fetchData(){

        val version = "v1_000"
        val uri = UriComponentsBuilder
                .fromUriString("http://${cardServiceAddress.trim()}/api/cards/collection_$version")
                .build().toUri()

        val response = cb.run(
                {
                    client.exchange(
                            uri,
                            HttpMethod.GET,
                            null,
                            object : ParameterizedTypeReference<WrappedResponse<CollectionDto>>() {})
                },
                { e ->
                    log.error("Failed to fetch data from Card Service: ${e.message}")
                    null
                }
        ) ?: return


        if (response.statusCodeValue != 200) {
            log.error("Error in fetching data from Card Service. Status ${response.statusCodeValue}." +
                    "Message: " + response.body.message)
        }

        try {
            collection = Collection(response.body.data!!)
        } catch (e: Exception) {
            log.error("Failed to parse card collection info: ${e.message}")
        }
    }

    private fun verifyCollection(){

        if(collection == null){
            fetchData()

            if(collection == null){
                throw IllegalStateException("No collection info")
            }
        }
    }

    fun millValue(cardId: String) : Int {
        verifyCollection()
        val card : Card = cardCollection.find { it.cardId  == cardId} ?:
            throw IllegalArgumentException("Invalid cardId $cardId")

        return collection!!.millValues[card.rarity]!!
    }

    fun price(cardId: String) : Int {
        verifyCollection()
        val card : Card = cardCollection.find { it.cardId  == cardId} ?:
                throw IllegalArgumentException("Invalid cardId $cardId")

        return collection!!.prices[card.rarity]!!
    }

    fun getRandomSelection(n: Int) : List<Card>{

        if(n <= 0){
            throw IllegalArgumentException("Non-positive n: $n")
        }

        verifyCollection()

        val selection = mutableListOf<Card>()

        val probabilities = collection!!.rarityProbabilities
        val bronze = probabilities[Rarity.BRONZE]!!
        val silver = probabilities[Rarity.SILVER]!!
        val gold = probabilities[Rarity.GOLD]!!
        //val pink = probabilities[Rarity.PINK_DIAMOND]!!

        repeat(n) {
            val p = Math.random()
            val r = when{
                p <= bronze -> Rarity.BRONZE
                p > bronze && p <= bronze + silver -> Rarity.SILVER
                p > bronze + silver && p <= bronze + silver + gold -> Rarity.GOLD
                p > bronze + silver + gold -> Rarity.PINK_DIAMOND
                else -> throw IllegalStateException("BUG for p=$p")
            }
            val card = collection!!.cardsByRarity[r].let{ it!![Random.nextInt(it.size)] }
            selection.add(card)
        }

        return selection
    }
}
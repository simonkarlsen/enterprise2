package no.kotlin.cardgame.enterprise.usercollections

import no.kotlin.cardgame.enterprise.usercollections.db.CardCopy
import no.kotlin.cardgame.enterprise.usercollections.db.User
import no.kotlin.cardgame.enterprise.usercollections.dto.CardCopyDto
import no.kotlin.cardgame.enterprise.usercollections.dto.UserDto

object DtoConverter {


    fun transform(user: User) : UserDto{

        return UserDto().apply {
            userId = user.userId
            coins = user.coins
            cardPacks = user.cardPacks
            ownedCards = user.ownedCards.map { transform(it) }.toMutableList()
        }
    }

    fun transform(cardCopy: CardCopy) : CardCopyDto{
        return CardCopyDto().apply {
            cardId = cardCopy.cardId
            numberOfCopies = cardCopy.numberOfCopies
        }
    }
}
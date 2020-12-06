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
            ticketPacks = user.ticketPacks
            ownedTickets = user.ownedTickets.map { transform(it) }.toMutableList()
        }
    }

    fun transform(cardCopy: CardCopy) : CardCopyDto{
        return CardCopyDto().apply {
            movieId = cardCopy.movieId
            numberOfCopies = cardCopy.numberOfCopies
        }
    }
}
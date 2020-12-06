package no.kotlin.cardgame.enterprise.usercollections.dto

import io.swagger.annotations.ApiModelProperty

data class UserDto(

        @get:ApiModelProperty("The id of the user")
        var userId: String? = null,

        @get:ApiModelProperty("The amount of coins owned by the user")
        var coins: Int? = null,

        @get:ApiModelProperty("The number of un-opened card packs the user owns")
        var ticketPacks: Int? = null,

        @get:ApiModelProperty("List of cards owned by the user")
        var ownedTickets: MutableList<CardCopyDto> = mutableListOf()
)
package no.kotlin.cardgame.enterprise.cards.dto

import io.swagger.annotations.ApiModelProperty

class CardDto(

        @get:ApiModelProperty("The id of the card")
        var movieId: String? = null,

        @get:ApiModelProperty("The name of this card")
        var name: String? = null,

        @get:ApiModelProperty("A description of the card effects")
        var description: String? = null,

        @get:ApiModelProperty("The director of the movie ")
        var director: String? = null,

        @get:ApiModelProperty("The year the movie was made")
        var year: Int? = null,

        @get: ApiModelProperty("The rarity of the card")
        var hall: Rarity? = null,

        @get: ApiModelProperty("The id of the image associated with this card")
        var imageId: String? = null
)
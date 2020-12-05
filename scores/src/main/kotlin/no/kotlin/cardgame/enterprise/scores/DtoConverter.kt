package no.kotlin.cardgame.enterprise.scores

import no.kotlin.cardgame.enterprise.scores.db.UserStats
import no.kotlin.cardgame.enterprise.scores.dto.UserStatsDto

object DtoConverter {

    fun transform(stats: UserStats) : UserStatsDto =
            stats.run { UserStatsDto(userId, victories, defeats, draws, score)}

    fun transform(scores: Iterable<UserStats>) : List<UserStatsDto> = scores.map { transform(it) }
}
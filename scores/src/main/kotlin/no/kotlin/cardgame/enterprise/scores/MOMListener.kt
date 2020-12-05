package no.kotlin.cardgame.enterprise.scores

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import no.kotlin.cardgame.enterprise.scores.db.UserStatsService
import org.springframework.amqp.rabbit.annotation.RabbitListener

@Service
class MOMListener(
        private val statsService: UserStatsService
) {

    companion object{
        private val log = LoggerFactory.getLogger(MOMListener::class.java)
    }

    @RabbitListener(queues = ["#{queue.name}"])
    fun receiveFromAMQP(userId: String) {
        val ok = statsService.registerNewUser(userId)
        if(ok){
           log.info("Registered new user via MOM: $userId")
        }
    }
}
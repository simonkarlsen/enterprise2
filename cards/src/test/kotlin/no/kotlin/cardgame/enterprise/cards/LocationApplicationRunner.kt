package no.kotlin.cardgame.enterprise.cards

import org.springframework.boot.SpringApplication


fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, "--spring.profiles.active=test")
}
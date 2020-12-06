package no.kotlin.cardgame.enterprise.cards

import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import io.restassured.RestAssured
import javax.annotation.PostConstruct
import io.restassured.RestAssured.given
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import no.kotlin.cardgame.enterprise.cards.RestApi.Companion.LATEST
import org.springframework.test.context.junit.jupiter.SpringExtension


@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [(Application::class)],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class RestApiTest {

    @LocalServerPort
    protected var port = 0

    @PostConstruct
    fun init() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }

//
//    @Test
//    fun testGetImg() {
//
//        given().get("/api/movies/imgs/001-monster.svg")
//                .then()
//                .statusCode(200)
//                .contentType("image/svg+xml")
//                .header("cache-control", `is`(notNullValue()))
//    }

    @Test
    fun testGetCollection() {

        given().get("/api/cards/collection_$LATEST")
                .then()
                .statusCode(200)
                .body("data.movies.size", greaterThan(10))
    }

    @Test
    fun testGetCollectionOldVersion1() {

        given().get("/api/cards/collection_v0_001")
                .then()
                .statusCode(200)
                .body("data.movies.size", greaterThan(10))
    }

    @Test
    fun testGetCollectionOldVersion2() {

        given().get("/api/cards/collection_v0_002")
                .then()
                .statusCode(200)
                .body("data.movies.size", greaterThan(10))
    }

    @Test
    fun testGetCollectionOldVersion3() {

        given().get("/api/cards/collection_v0_003")
                .then()
                .statusCode(200)
                .body("data.movies.size", greaterThan(10))
    }
}
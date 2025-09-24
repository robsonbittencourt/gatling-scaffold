package simulations

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import java.time.Duration.ofSeconds
import kotlin.time.Duration.Companion.seconds

class BasicSimulation : Simulation() {

    private val httpProtocol = http
        .baseUrl("https://testapi.jasonwatmore.com")
        .acceptHeader("application/json")
        .contentTypeHeader("application/json")

    private val getProducts = exec(
        http("GET all products")
            .get("/products")
            .check(status().`is`(200))
    )

    private val postProduct = exec(
        http("POST create product")
            .post("/products")
            .body(
                StringBody(
                    """
                    {
                        "name": "Gatling Test Product",
                        "price": "9.99"
                    }
                    """.trimIndent()
                )
            )
            .asJson()
            .check(status().`is`(200))
            .check(jsonPath("$.id").exists())
    )

    private val scn = scenario("GET and POST Real API")
        .exec(getProducts)
        .pause(1)
        .exec(postProduct)

    init {
        setUp(
            scn.injectOpen(constantUsersPerSec(3.0).during(ofSeconds(5)))
        ).protocols(httpProtocol)
    }
}

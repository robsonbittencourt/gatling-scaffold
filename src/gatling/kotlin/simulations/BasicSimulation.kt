package simulations

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http

class BasicSimulation : Simulation() {

  private val httpProtocol = http.baseUrl("https://httpstat.us")

  private val scn = scenario("BasicSimulation")
    .exec(http("Should be ok").get("/200"))
    .exec(http("Gateway timeout").get("/504"))
    .exec(http("With random response time").get("/200?sleep=5000"))

  init {
    setUp(scn.injectOpen(atOnceUsers(1)).protocols(httpProtocol))
  }
}

package sample

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpProtocol = http.baseUrl("http://computer-database.gatling.io")

  val scn = scenario("BasicSimulation")
    .exec(http("request_1")
    .get("/"))

  setUp(
    scn.inject(constantUsersPerSec(2) during(60 seconds))
  ).protocols(httpProtocol).assertions(global.responseTime.percentile3.gt(95))

}

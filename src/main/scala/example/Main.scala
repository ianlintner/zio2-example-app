package example

import zhttp.http._
import zhttp.service.Server
import zio._

object Main extends ZIOAppDefault {
  val port: Int = 8080

  // Create HTTP route
  val app:  Http[Any, Nothing, Request, Response]  = Http.collectZIO[Request] {
    case Method.GET -> !!          => ZIO.succeed(Response.text("Hello World!"))
    case Method.GET -> !! / "json" => ZIO.succeed(Response.json("""{"greetings": "Hello World!"}"""))
    case Method.GET -> !! / "error" =>
      ZIO.logErrorCause(message = "Example Error", cause = Cause.fail( new RuntimeException("Test"))) &>
      ZIO.succeed(Response.text("An error was logged."))
  }

  // Run it like any simple app
  override val run: ZIO[Any with ZIOAppArgs with Scope, Throwable, Nothing] = {
    ZIO.logInfo(s"Starting server on port $port") &>
    Server.start(port, app)
  }
}

package example

import zhttp.http._
import zhttp.service.Server
import zio._
import zio.logging.backend.SLF4J

object Main extends ZIOAppDefault {
  val port: Int = 8080
  private val loglayer: ZLayer[Any, Nothing, Unit] = Runtime.removeDefaultLoggers >>> SLF4J.slf4j


  // Create HTTP route
  val app:  Http[Any, Nothing, Request, Response]  = Http.collectZIO[Request] {
    case Method.GET -> !!          => ZIO.succeed(Response.text("Hello World!"))
    case Method.GET -> !! / "json" => ZIO.succeed(Response.json("""{"greetings": "Hello World!"}"""))
    case Method.GET -> !! / "error" => {
        ZIO.fail(new RuntimeException("Java Test Exception"))
          .tapErrorCause(e => ZIO.logErrorCause(message="Test ZIO Error", e)).orElseSucceed(()) &>
        ZIO.succeed(Response.text("An error was logged."))
    }.provideLayer(loglayer)
  }

  // Run it like any simple app
  override val run: ZIO[Any with ZIOAppArgs with Scope, Throwable, Nothing] = {
    ZIO.logInfo(s"Starting server on port $port") &>
    Server.start(port, app)
  }
}

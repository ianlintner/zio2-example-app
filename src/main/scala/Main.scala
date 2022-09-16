import zhttp.http._
import zhttp.service.Server
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

object Main extends ZIOAppDefault {
  val port: Int                  = 8080

  // Create HTTP route
  val app: HttpApp[Any, Nothing] = Http.collect[Request] {
    case Method.GET -> !!          => Response.text("Hello World!")
    case Method.GET -> !! / "json" => Response.json("""{"greetings": "Hello World!"}""")
  }

  // Run it like any simple app
  override val run: ZIO[Any with ZIOAppArgs with Scope, Throwable, Nothing] =
    Server.start(port, app)
}

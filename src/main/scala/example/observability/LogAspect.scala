package example.observability

  import zio._
  import zhttp.http.Request

  object LogAspect {
    def logAnnotateCorrelationId(
                                  req: Request
                                ): ZIOAspect[Nothing, Any, Nothing, Any, Nothing, Any] =
      new ZIOAspect[Nothing, Any, Nothing, Any, Nothing, Any] {
        override def apply[R, E, A](
                                     zio: ZIO[R, E, A]
                                   )(implicit trace: Trace): ZIO[R, E, A] =
          correlationId(req).flatMap(id => ZIO.logAnnotate("correlation-id", id)(zio))

        def correlationId(req: Request): UIO[String] =
          ZIO
            .succeed(req.header("X-Correlation-ID").map(_._2.toString))
            .flatMap(id => Random.nextUUID.map(uuid => id.getOrElse(uuid.toString)))
      }
}

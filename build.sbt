name := "zio2-example-app"
version := "0.1"
scalaVersion := "2.13.8"


lazy val zioVersion = "2.0.1"
lazy val zioHttp = "2.0.0-RC11"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-test" % zioVersion,
  "dev.zio" %% "zio-test-sbt" % zioVersion,
  //"dev.zio" %% "zhttp" % zioHttp,
  "io.d11" %% "zhttp" % zioHttp
)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")


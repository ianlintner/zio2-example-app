name := "zio2-example-app"
version := "0.1"
scalaVersion := "2.13.8"


lazy val zioVersion = "2.0.2"
lazy val zioHttp = "2.0.0-RC11"
lazy val zioLog = "2.1.0"
lazy val googleCloudLoggingLogback = "0.127.8-alpha"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-test" % zioVersion,
  "dev.zio" %% "zio-test-sbt" % zioVersion,
  //"dev.zio" %% "zhttp" % zioHttp,
  "io.d11" %% "zhttp" % zioHttp,

  "dev.zio" %% "zio-logging" % zioLog,
  "dev.zio" %% "zio-logging-slf4j" % zioLog,
  "ch.qos.logback" % "logback-core" %  "1.4.0",
  "ch.qos.logback" % "logback-classic" %  "1.4.0",
  "org.slf4j" % "slf4j-api" % "2.0.0",
  "org.slf4j" % "slf4j-simple" % "2.0.0",
"net.logstash.logback" % "logstash-logback-encoder" %  "7.2",
  "com.google.cloud" % "google-cloud-logging-logback" % googleCloudLoggingLogback
 )



testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")


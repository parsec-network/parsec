/**
* Build file for Scala Parsec modules.
* Manages build settings and dependencies (internal & external) for each submodule.
* Creates deployable units via assembly plugin.
*/

/** Dependency defs */

  import Versions._

  def baseScalaVersion = "2.11.8"
  def baseScalaBinaryVersion = "2.11"

  /** Akka */
  def akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  def akkaHttp = "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion
  def akkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion % "test"
  def akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
  def akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  def akkaStreamTestkit = "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % "test"
  def akka = Seq(
    akkaActor,
    akkaHttp,
    akkaHttpTestkit,
    akkaSlf4j,
    akkaStream,
    akkaStreamTestkit
  )

  /** Cats */
  def cats = Seq(
    "org.typelevel" %% "cats-macros" % catsVersion,
    "org.typelevel" %% "cats-core" % catsVersion
  )

  /** Datetime */
  def nScalaTime = "com.github.nscala-time" %% "nscala-time" % "2.14.0"

  /** Enum support */
  def enumeratum = "com.beachape" %% "enumeratum-macros" % "1.3.7"

  /** JSON */
  def json4sNative = "org.json4s" %% "json4s-native" % "3.3.0"

  /** Kafka */
  def avro4s = "com.sksamuel.avro4s" %% "avro4s-core" % "1.6.2"
  def kafkaCore = "org.apache.kafka" %% "kafka" % kafkaVersion
  def kafkaAvroSerializer = "io.confluent" % "kafka-avro-serializer" % confluentVersion
  def kafka = Seq(
    avro4s,
    kafkaCore,
    kafkaAvroSerializer
  )

  /** Cryptography */
  def bouncycastle = "org.bouncycastle" % "bcprov-ext-debug-jdk15on" % "1.55"


/** Logging */
  def slf4jApi = "org.slf4j" % "slf4j-api" % slf4jVersion
  def log4jOverSlf4j = "org.slf4j" % "log4j-over-slf4j" % slf4jVersion
  def logbackClassic = "ch.qos.logback" % "logback-classic" % "1.1.2"

  /** Testing */
  def scalatest = "org.scalatest" %% "scalatest" % "2.2.6" % "test"
  def scalacheck = Seq(
    "org.scalacheck" %% "scalacheck" % "1.12.5" % "test",
    "org.typelevel" %% "shapeless-scalacheck" % "0.4" % "test"
  )
  def testing = scalacheck :+ scalatest


/** Dependency settings */

  lazy val baseJavaDependencies = Seq(
    "org.apache.commons" % "commons-lang3" % "3.3.2",
    "commons-io" % "commons-io" % "2.4",
    "com.typesafe" % "config" % "1.2.1",
    slf4jApi,
    log4jOverSlf4j,
    logbackClassic,
    bouncycastle
  )

  lazy val baseScalaDependencies = Seq(
    "org.scala-lang" % "scala-reflect" % baseScalaVersion
  ) ++ cats

/** Repositories */

  val sonatypeSnapshots = "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
  val ukMavenCentral = "UK Maven Central" at "http://uk.maven.org/maven2"
  val typesafeReleases = "Typesafe releases" at "http://repo.typesafe.com/typesafe/releases/"

  lazy val resolvers = Seq(Resolver.defaultLocal, sonatypeSnapshots, ukMavenCentral, typesafeReleases)

/** Project settings */

  lazy val baseProjectSettings = Seq(
    organization := "org.parsec",
    scalaBinaryVersion := baseScalaBinaryVersion,
    scalaVersion := baseScalaVersion,
    externalResolvers := resolvers,
    moduleConfigurations := Seq(),
    retrieveManaged := true,
    transitiveClassifiers in Scope.GlobalScope := Seq("sources"),
    ivyLoggingLevel := UpdateLogging.Quiet,
    crossPaths := false,
    publishMavenStyle := true,
    javacOptions := Seq("-source", "1.8", "-target", "1.8", "-encoding", "utf8"),
    javaOptions := Seq("-server", "-XX:ReservedCodeCacheSize=192m", "-Xss2m"),
    javaOptions in Test := Seq("-server", "-Xmx2g", "-XX:ReservedCodeCacheSize=192m", "-Xss2m"),
    testFrameworks := Seq(TestFrameworks.ScalaTest),
    noTestCompletion(),
    scalacOptions := Seq(
      "-deprecation",
      "-optimize",
      "-unchecked",
      "-encoding", "utf8",
      "-target:jvm-1.8",
      "-Xlog-reflective-calls",
      "-feature",
      "-language:_"
    ) ++ Seq(
      "by-name-right-associative",
      "delayedinit-select",
      "doc-detached",
      "inaccessible",
      "missing-interpolator",
      "nullary-override",
      "option-implicit",
      "package-object-classes",
      "poly-implicit-overload",
      "private-shadow",
      "unsound-match"
    ).map(x => s"-Xlint:$x"),
    compileOrder := CompileOrder.JavaThenScala,
    fork in Test := true,
    testOptions in Test := Seq(Tests.Filter(testName => testName.endsWith("Tests"))),
    testOptions in Test += Tests.Argument("-oDF")
  )

  lazy val javaProjectSettings = baseProjectSettings ++ Seq(
    libraryDependencies ++= baseJavaDependencies
  )

  lazy val scalaProjectSettings = javaProjectSettings ++ Seq(
    libraryDependencies ++= baseScalaDependencies
  )

/** Module projects; not configured for deployment as jar */

  val fullScope = "compile->compile;test->test"

  lazy val build = Project(
    "build",
    file("."),
    settings = baseProjectSettings
  ).aggregate(common, domain, simulator)

  lazy val common = Project(
    "common",
    file("common"),
    settings = scalaProjectSettings ++ Seq(
      libraryDependencies ++= testing :+ enumeratum
    )
  )

  lazy val domain = Project(
    "domain",
    file("domain"),
    settings = scalaProjectSettings ++ Seq(
      libraryDependencies ++= testing
    )
  ).dependsOn(common % fullScope)

  /** Deployable projects; use sbt/assembly task to build fat jar */

  lazy val restApi = Project(
    "rest-api",
    file("rest-api"),
    settings = scalaProjectSettings ++ Seq(
      assemblyJarName in assembly := "parsec-rest-api.jar",
      libraryDependencies ++= testing ++ akka
    )
  )

  lazy val simulator = Project(
    "simulator",
    file("simulator"),
    settings = scalaProjectSettings ++ Seq(
      mainClass in (Compile, assembly) := Some("org.parsec.ParsecSimulator"),
      mainClass in run := Some("org.parsec.ParsecSimulator"),
      assemblyJarName in assembly := "parsec-simulator.jar",
      libraryDependencies ++= testing ++ kafka :+ nScalaTime
    )
  ).dependsOn(common % fullScope, domain % fullScope)

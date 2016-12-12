resolvers ++= Seq(
  "Sonatype Repository" at "https://oss.sonatype.org/content/groups/public"
)

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")
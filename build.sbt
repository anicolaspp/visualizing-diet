name := "ref"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  Resolver.bintrayRepo("stanch", "maven"),
  Resolver.bintrayRepo("drdozer", "maven")
)

libraryDependencies += "org.stanch" %% "reftree" % "0.7.1"

libraryDependencies += "org.typelevel" %% "dogs-core" % "0.2.2"
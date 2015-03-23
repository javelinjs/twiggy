name := "twiggy"

version := "0.1"

traceLevel in run := 0

fork in run := true

scalacOptions ++= Seq("-optimize")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.1.1"
)

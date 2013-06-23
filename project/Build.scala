import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "angular"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    "com.typesafe.slick" %% "slick" % "1.0.1"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    testOptions in Test += Tests.Argument("junitxml", "console")
  )

}

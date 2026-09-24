val giteaMaven = "https://gitea.local.vgerasimov.dev/api/packages/wlad031/maven"
val giteaCredentials = for { user <- sys.env.get("GITEA_USERNAME"); token <- sys.env.get("GITEA_TOKEN") } yield Credentials("Gitea Package API", "gitea.local.vgerasimov.dev", user, token)

val root = project
  .in(file("."))
  .settings(
    scalaVersion := "3.3.1",
    organization := "dev.vgerasimov",
    name := "common-scala",
    version := "0.1.0",
    publishTo := Some("gitea" at giteaMaven),
    publishMavenStyle := true,
    credentials ++= giteaCredentials,
    scalacOptions ++= Seq(
      "-rewrite",
      "-source", "future"
    ),
    libraryDependencies ++= {
      val munitVersion = "0.7.29"
      Seq(
        "org.scalameta" %% "munit"            % munitVersion % Test,
        "org.scalameta" %% "munit-scalacheck" % munitVersion % Test
      )
    },
  )
  

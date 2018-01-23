enablePlugins(ScalaNativePlugin, JniNative)
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
bintrayOrganization := Some("sunglassframework")
scalaVersion := "2.11.12"
organization := "io.sunglass"
name := "uv"
version := "0.1.0"

scalacOptions ++= Seq("-deprecation", "-feature", "-language:implicitConversions")
nativeMode := "debug"
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.0" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")
nativeLinkStubs := true
crossPaths := true // sbt-jni disables this for some reason
sourceDirectory in nativeCompile := sourceDirectory.value / "main" / "native"
nativeLinkingOptions ++= {
  val target = s"${baseDirectory.value}/target/native/${nativePlatform.value}/bin"
  Seq(s"-L$target", "-rpath", target)
}
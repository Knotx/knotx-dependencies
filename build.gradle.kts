/*
 * Copyright (C) 2019 Knot.x Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
  `java-platform`
  id("io.knotx.release-base")
  id("io.knotx.maven-publish")
}

defaultTasks("publishToMavenLocal")

repositories {
  jcenter()
  maven { url = uri("https://plugins.gradle.org/m2/") }
  maven { url = uri("https://repo.maven.apache.org/maven2") }
  maven { url = uri("https://oss.sonatype.org/content/groups/staging/") }
}

group = "io.knotx"
description = "Knot.x Dependencies - BOM containing external Knot.x dependencies versions."

javaPlatform {
  allowDependencies()
}

dependencies {
  constraints {
    api("io.vertx:vertx-rx-java2-gen:${prop("vertx.stack.version")}")
    api("ch.qos.logback:logback-classic:${prop("logback-classic.version")}")
    api("com.google.guava:guava:${prop("guava.version")}")
    api("org.apache.commons:commons-lang3:${prop("commons-lang3.version")}")
    api("org.apache.commons:commons-collections4:${prop("commons-collections4.version")}")
    api("commons-io:commons-io:${prop("commons-io.version")}")
    api("com.typesafe:config:${prop("typesafe.version")}")

    api("org.mockito:mockito-core:${prop("mockito2.version")}")
    api("org.mockito:mockito-junit-jupiter:${prop("mockito2.version")}")
    api("com.github.tomakehurst:wiremock:${prop("wiremock.version")}")
    api("org.junit.platform:junit-platform-surefire-provider:${prop("junit5-surefire.version")}")
    api("me.alexpanov:free-port-finder:${prop("free-port-finder.version")}")
    api("org.assertj:assertj-core:${prop("assertj.version")}")
  }
  api(platform("io.vertx:vertx-dependencies:${prop("vertx.stack.version")}"))
  api(platform("org.junit:junit-bom:${prop("junit5.version")}"))
}

publishing {
  publications {
    getByName<MavenPublication>("mavenJava") {
      pom {
        inceptionYear.set("2016")

        withXml {
          asNode().apply {
            appendNode("distributionManagement")
                .apply {
                  appendNode("repository")
                      .apply {
                        appendNode("id", "ossrh")
                        appendNode("url", "https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                      }
                }
          }
        }
      }
      from(components["javaPlatform"])
    }
  }
}

extra["isReleaseVersion"] = !version.toString().endsWith("SNAPSHOT")
tasks.withType<Sign>().configureEach {
  onlyIf { project.extra["isReleaseVersion"] as Boolean }
}

fun prop(id: String): String = project.properties[id] as String

tasks {
  named("build") {
    mustRunAfter("setVersion")
  }

  named("updateChangelog") {
    dependsOn("build", "setVersion")
  }

  register("prepare") {
    group = "release"
    dependsOn("updateChangelog", "publishToMavenLocal")
  }

  register("publishArtifacts") {
    group = "release"
    dependsOn("publish")
    logger.lifecycle("Publishing java artifacts")
  }
}

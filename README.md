[![Build Status](https://dev.azure.com/knotx/Knotx/_apis/build/status/Knotx.knotx-dependencies?branchName=master)](https://dev.azure.com/knotx/Knotx/_build/latest?definitionId=4&branchName=master)
[![Gradle Status](https://gradleupdate.appspot.com/Knotx/knotx-dependencies/status.svg)](https://gradleupdate.appspot.com/Knotx/knotx-dependencies/status)

# Knot.x Dependencies

## Use in your project
This project is a [Bill of Materials](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#bill-of-materials-bom-poms) for Knot.x projects. It specifies external dependencies versions.

To import it, simply include it in your project dependencies as a [`platform`](https://docs.gradle.org/5.0/userguide/managing_transitive_dependencies.html#sec:bom_import):
```kotlin
implementation(platform("io.knotx:knotx-dependencies:${project.version}"))
```
Then use deps without versions and the BOM logic will resolve the versions accordingly, e.g.
```kotlin
testImplementation("org.junit.jupiter:junit-jupiter-api")
```

To use it in a [composite build](https://docs.gradle.org/current/userguide/composite_builds.html) include it in your `settings.gradle.kts` first:
```kotlin
includeBuild("../knotx-dependencies")
```

The `build` task (or any other main task you depend on) of your project should be executed after building the `knotx-dependencies` module. Add this snippet to your `build.gradle.kts`:

```kotlin
tasks {
    named("build") {
        dependsOn(gradle.includedBuild("knotx-dependencies").task(":build"))
    }
}
```

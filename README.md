[![Build Status](https://dev.azure.com/knotx/Knotx/_apis/build/status/Knotx.knotx-dependencies?branchName=master)](https://dev.azure.com/knotx/Knotx/_build/latest?definitionId=4&branchName=master)

# Knot.x Dependencies

## Use in your project
Import dependencies versions
```kotlin
implementation(platform("io.knotx:knotx-dependencies:${project.version}"))
```
Then use deps without versions, e.g.
```kotlin
testImplementation("org.junit.jupiter:junit-jupiter-api")
```

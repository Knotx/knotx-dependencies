[![][license img]][license]
[![Build Status](https://dev.azure.com/knotx/Knotx/_apis/build/status/Knotx.knotx-dependencies?branchName=master)](https://dev.azure.com/knotx/Knotx/_build/latest?definitionId=4&branchName=master)

# Knot.x Dependencies

## Use in your project
Import dependencies versions
```xml
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>io.knotx</groupId>
        <artifactId>knotx-dependencies</artifactId>
        <version>${knotx.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
```
Then use deps without versions, e.g.
```xml
  <dependency>
    <groupId>io.knotx</groupId>
    <artifactId>knotx-server-http-api</artifactId>
  </dependency>
```

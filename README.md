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
        <artifactId>knotx-core</artifactId>
      </dependency>
```

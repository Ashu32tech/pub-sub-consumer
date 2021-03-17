# Google pub sub consumer

* Create maven project using below command
```
mvn archetype:generate -DgroupId=com.infogain.gcp.poc -DartifactId=pub-sub-consumer -Dversion=1.0.0 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

* Filter prefixes
```
hasPrefix(attributes.mobileNumber, "900")

hasPrefix(attributes.mobileNumber, "901")
```
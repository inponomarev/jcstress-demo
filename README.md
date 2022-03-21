#JCStress examples for my Java Concurrency lectures

This is a set of demos for my [Java lectures](https://inponomarev.ru/corejava) written with [JCStress](https://github.com/openjdk/jcstress). They demonstrate examples of correct and broken concurrency code.

This project was created from Maven artifact:

```
mvn archetype:generate -DinteractiveMode=false -DarchetypeGroupId=org.openjdk.jcstress -DarchetypeArtifactId=jcstress-java-test-archetype -DarchetypeVersion=0.15 -DgroupId=ee.eek -DartifactId=jcstresstest -Dversion=1.0
```
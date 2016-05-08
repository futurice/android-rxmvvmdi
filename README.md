# android-rxmvvmdi
Project for introducing Android developers to how one can combine Rx, MVVM, DI, and unit testing concepts. Each activity introduces a certain set of concepts which are documented in the activity class itself.

## Setup
### JDK8
This project uses retrolambda, so you need to make sure to have [JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed and the JAVA8_HOME environment variable set to point to it.

### Android Studio & Gradle
Android Studio >= v2.1 recommended. May work with earlier versions, but you might run into issues with the combination of data-binding libraries, retrolambda, and dagger that uses android-apt. The project depends on android build tools v2.0.0, it does not build with v2.1.0.
# android-rxmvvmdi
Project for introducing Android developers to how one can combine Rx, MVVM, DI, and unit testing concepts

## Setup
### JDK8
This project uses retrolambda, so you need to make sure to have [JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed and the JAVA8_HOME environment variable set to point to it.

### Android Studio & Gradle
Android Studio >= v2.1 recommended. This project depends on gradle 2.1 (may work with earlier versions, but you might run into issues with the combination of data-binding libraries, retrolambda, and dagger that uses android-apt).
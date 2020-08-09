# EmployeeHub

## Endpoint configuration
The endpoint is currently hardcoded to `http://localhost:5000`.
You can modify the endpoint by updating the [AppConfiguration](app/src/main/java/com/anifichadia/employeehub/shared/AppConfiguration.kt) class.

## Features
This app is written using Kotlin, including the gradle files!
This uses centralised dependency management in the [buildSrc](buildSrc).

There are two build variants, `debug` and `release`.
Using the `debug` variant is recommended, since it's the only variant that allows cleartext / http traffic to local services.
The `release` variant is for productionisation and ideally it would communicate over https and not require cleartext traffic.
Additionally logging can be disabled in this variant to prevent leaking app behaviour.

Screens are developed using MVVM.
Interfaces are used for important classes, like screen contracts and use cases.
This allows components to be mocked and prevents the reliance on concretions.
The app also uses dagger for dependency injection.

ViewModel testing has also been included in the [test package](app/src/test/java/com/anifichadia/employeehub).
Tests use JUnit and Mockito and dependencies are designed to be mocked / injected using their interfaces.

Rest API integration is implemented using retrofit and can be found in the [service](app/src/main/java/com/anifichadia/employeehub/service) package.

Logging has been included for network traffic, and can be managed in the  [AppConfiguration](app/src/main/java/com/anifichadia/employeehub/shared/AppConfiguration.kt) class using the `debug` flag.

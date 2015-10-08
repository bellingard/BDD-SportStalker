SportStalker  version 1.2.0
==================

Usage instructions
==================

You can use the SDK in three different ways, according to your preferences:

* As a Gradle dependency
* By building a JAR and copying it in the libs folder of your project
* By copying the source code in your project

Use the SDK as a Gradle dependency
-------------------

To build the SDK, simply run `./gradlew clean install`. This will install the JAR files (binaries, sources and javadoc) in your local Maven repository.

It can be imported in your project by adding the following lines to your `build.gradle` file:

```groovy
dependencies {
    compile "net.apispark.webapi:SportStalker:1.0.0"
}

repositories {
    mavenLocal()
}
```

Use the SDK as a JAR
-------------------

To package the SDK, run `./gradlew clean build`. The corresponding JAR files (binaries, sources and javadoc) will be generated under the `build/libs` directory.

To integrate it in your project, copy the JAR file in the `app/libs` folder of your project. This folder is already imported in your project dependencies (`compile fileTree(dir: 'libs', include: ['*.jar'])`).

You’ll then have to add the following dependencies to your `build.gradle` file:

```groovy
dependencies {
    compile("org.restlet.android:org.restlet.ext.jackson:2.3.4") {
        exclude group: 'javax.xml.stream', module: 'stax-api'
    }
}
```

Click the "Sync your project with Gradle files" button to update your project.

Use the source directly
-------------------

Another usage option is to directly copy the SDK sources (in `src/main/java`) to your project.
In order to build them, add the following lines to your `build.gradle` file:

```groovy
dependencies {
    compile("org.restlet.android:org.restlet.ext.jackson:2.3.4") {
        exclude group: 'javax.xml.stream', module: 'stax-api'
    }
}
```

Gradle packaging options
------------------------

The following set of packaging options are required for Jackson library to work properly:

```groovy
android {
    packagingOptions {
        exclude 'META-INF/services/com.fasterxml.jackson.core.JsonFactory'
        exclude 'META-INF/services/com.fasterxml.jackson.core.ObjectCodec'
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/NOTICE'
    }
}
```

Permissions
-----------

Update permissions in `AndroidManifest.xml` to enable Internet, by adding the following line:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```


Getting started with the SDK
=================

Project structure
-----------

* The main entry point to make API calls is the `Sdk` class
* The representation beans for input and output payloads are in package `net.apispark.webapi.representation`
* The classes for authentication are in package `net.apispark.webapi.auth`. See section "Configure credentials" below.


Usage
-----------

Considering an API called sdk_test with the following endpoints:
* GET /companies/{companyId}
* POST /companies

With this methods, an API call would be done with the following code :

```java
Sdk sdkTest = new Sdk();

// Let's set up the basic auth
SecurityConfig securityConfig = sdkTest.getConfig().getSecurityConfig();
// Method name depends of the supported security scheme of the API. For more details about how to configure security, see section "Configure credentials" below

securityConfig.configureAuthBasic("login", "password");

// Get the company of id 1
try {
    Company company = sdkTest.company("1").getCompany();
    Log.i("myapplication", "Company with ID 1: " + company.getName());
} catch(ResourceException e) {
    Log.e("myapplication", "Status: " + e.getStatus(), e); // An exception occurs when getting the company with id 1
}

// Add a new company
Company newCompany = new Company();
newCompany.setName("My company");
Address newCompanyAddress = new Address();
newCompanyAddress.setStreet("");
newCompanyAddress.setZipcode("");
newCompanyAddress.setCity("");
newCompany.setAddress(newCompanyAddress);
newCompany.setTags(Arrays.asList("high technologies"));

try {
    Company addedCompany = sdkTest.companyList().postCompanyList(newCompany);
    Log.i("myapplication", "New company created with ID " + addedCompany.getId());
} catch(ResourceException e) {
    Log.e("myapplication", "Status: " + e.getStatus(), e);
}
```

Configure credentials
-----------

* Configure settings for security schemes declared in the API definition

```java
sdk.getConfig().getSecurityConfig()
        // Security schemes declared in the API definition have their own methods to configure them.
            .configureAuthBasic("userId", "password");
```

* Configure a custom security scheme. In the case when the API definition is not up-to-date or incorrect,
declared schemes can be overridden by any kind of authentication mechanism (simply implement Authenticator
or use one of the generic implementations found in `net.apispark.webapi.auth.generic`).

```java
sdk.getConfig().getSecurityConfig()
            .configureCustomAuth(MyApiKeyAuthenticator.class,
                    new HeaderApiKeyAuthenticator("X-Custom-Auth", "customToken"));
```

* Configure a custom security scheme. In case the API definition doesn't include any security information, 
a custom global scheme can be configured. This scheme will be used as a fallback for all calls.

```java
sdk.getConfig().getSecurityConfig()
            .configureCustomGlobalAuth(
                    new HeaderApiKeyAuthenticator("X-Custom-Auth", "customToken"));
```

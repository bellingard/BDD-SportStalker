SportStalker  version 1.1.0
==================

Usage instructions
==================

This project is a maven project. To package it, run `mvn clean install`. The corresponding jar file is generated
under the `target` directory and is now available from you maven local repository. 
You can then import it in your project as a maven dependency.

Project structure
=================

* The main entry point to make API calls is the `Sdk` class
* The representation beans for input and output payloads are in package `net.apispark.webapi.representation`
* The classes for authentication are in package `net.apispark.webapi.auth`. See section "Configure credentials" below.

Maven integration 
===============

In your `pom.xml`, add the following lines:

```
<dependencies>
    <!-- ... -->
    <dependency>
        <groupId>net.apispark.webapi</groupId>
        <artifactId>SportStalker</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

Usage
=====

Considering an API called sdk_test with the following endpoints:
* GET /companies/{companyId}
* POST /companies

API calls would be made like this:

```java
Sdk sdkTest = new Sdk();

SecurityConfig securityConfig = sdkTest.getConfig().getSecurityConfig();

// Configure one of the security schemes defined in the API definition. The security
// configuration exposes one method per scheme, with the name of the scheme used as a
// suffix for the method’s name. For more details about how to configure security, see
// section "Configure credentials" below.
securityConfig.configureAuthBasic("login", "password");

// Get the company of id 1
try {
    Company company = sdkTest.company("1").getCompany();
    System.out.println("Company with ID 1: " + company.getName());
} catch(ResourceException e) {
    System.err.println("Status: " + e.getStatus());
}

// Add a new company

Company newCompany = new Company();
newCompany.setName("My company");
Address newCompanyAddress = new Address();
newCompanyAddress.setStreet("");
newCompanyAddress.setZipcode("");
newCompanyAddress.setCity("");
newCompany.setAddress(newCompanyAddress);
newCompany.setTags(Arrays.asList("high-tech"));

try {
    Company addedCompany = sdkTest.companyList().postCompanyList(newCompany);
    System.out.println("New company created with ID " + addedCompany.getId());
} catch(ResourceException e) {
    System.err.println("Status: " + e.getStatus());
}
```

Configure credentials
=====================

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

Logging
=======

By default, the API server uses the java logging (JUL).
You can specify the configuration file with the following system property `-Djava.util.logging.config.file="/path/to/logging.properties"`

For more explanations, see: http://restlet.com/technical-resources/restlet-framework/guide/2.3/editions/jse/logging


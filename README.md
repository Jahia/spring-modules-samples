# spring-modules-samples

This repository contains sample modules to demonstrate possible usages of [Spring Framework](https://spring.io/) in Jahia.

**Please note that direct usage of Spring in Jahia, to handle objects lifecycle and wiring, is discouraged.** OSGI offers
natively several alternatives: [Declarative Services](https://docs.osgi.org/specification/osgi.cmpn/7.0.0/service.component.html),
[Blueprint](https://docs.osgi.org/specification/osgi.cmpn/7.0.0/service.blueprint.html) or
[CDI](https://docs.osgi.org/specification/osgi.enterprise/7.0.0/service.cdi.html).

## Sample modules

- [blueprint](#blueprint)
- [gemini-blueprint](#gemini-blueprint)
- [spring-feature](#spring-feature)
- [spring-embedded](#spring-embedded)

## [blueprint](./blueprint)

A module to demonstrate usage of [OSGI Blueprint](https://docs.osgi.org/specification/osgi.cmpn/7.0.0/service.blueprint.html).
Blueprint is part of OSGI specifications and provides a declarative way to define beans, services and service references using
XML. Blueprint has been inspired by Spring Framework and is thus very similar.

The implementation of OSGI Blueprint provided by Jahia is Gemini Blueprint.

**If you are looking for a way to declare OSGI services and wiring through XML files, this is
the recommended way to do so.**

Bootstrapping a module with OSGI Blueprint, is just a matter of adding a
[blueprint.xml](./blueprint/src/main/java/resources/OSGI-INF/blueprint/blueprint.xml)
file in your bundle's `OSGI-INF/blueprint`.

However a known limitation regarding usage of Blueprint with Jahia is that services must
implement an interface, otherwise they cannot be referenced from a bundle using Blueprint.
The following entry is thus only valid if `com.bar.Foo` is a Java interface.

```xml
<reference id="foo" interface="com.bar.Foo"/>
```

Since some services exposed by Jahia do not implement an interface this may prevent you from
using Blueprint. As a workaround, you could leverage a specific of Gemini Blueprint, the
Blueprint implementation provided with Jahia, to declare beans using the Spring namespace which
is less restrictive in that matter.

## [gemini-blueprint](./gemini-blueprint)

A module to demonstrate usage of Gemini Blueprint. Gemini Blueprint is an implementation of OSGI
Blueprint and is built on top of Spring Framework.

[Gemini Blueprint 1.0.2](https://www.eclipse.org/gemini/blueprint/documentation/reference/1.0.2.RELEASE/html/blueprint.html)
is provided with Jahia, and is relying on Spring 3.2.13

Because it is build on top of Spring Framework, Gemini Blueprint gives you access to the underlying
application context, events and lifecycle. It does also allow you to use Spring namespaces for XML
declarations as an alternative to the Blueprint equivalent. When using those, XML definitions are regular
Spring files with an additional namespace for OSGI specifics. One of the benefit of using the Spring
namespaces is that it will circonvene the limitation of OSGI Blueprint that is requiring services to
implement an interface. However another limitation you may face is that those services without an interface
must have a default public constructor in order to be instrumented properly by the framework.

Bootstrapping a module with Blueprint using Spring namespace, is just a matter of adding 
[*.xml](./gemini-blueprint/src/main/java/resources/OSGI-INF/blueprint/sample-applicationContext.xml)
files, using Spring namespaces for bean definitions, in your bundle's `OSGI-INF/blueprint` folder.

**Please be aware that developing modules that relies on specifics of a given implementation of the OSGI Blueprint
specification is not advised. Forthcoming releases of Jahia may ship with an alternate implementation of the
specification, and thus break your integration.**

## [spring-feature](./spring-feature)

A module to demonstrate usage of Spring 5, provided as a Karaf feature with Jahia.

Starting from Jahia 8, Spring 5.1.9.RELEASE_1 is provided as a feature with Jahia. Module developers can now
benefit from using those shared libraries in their modules without having to embed Spring jars. This can come
handy if one need to benefit from sugar candies provided by version 5 of the Spring Framework.

Because a dependency to an old (3.x) version of Spring Framework is inherited from the module parent provided
by Jahia, you should carefully review your module's pom (and generated MANIFEST.MF) to ensure your module is
properly instructed to use the version of Spring you are expecting. You can refer to the [pom.xml](./spring-feature/pom.xml)
from this sample project for guidelines.

**Be aware though that by using Spring that way, the Spring Framework will not be managed by the OSGI container.**
This means that the container will not create any application context on its own for your modules. It would
thus be the responsability of the module developer to handle the creation of application contexts, as well as
handling any interaction between such contexts and the OSGI container.

This sample module come with a [bundle activator](./spring-feature/src/main/java/org/jahia/samples/module/springfeature/SpringActivator.java)
that illustrates how one could create an application context.

## [spring-embedded](./spring-embedded)

A module to demonstrate usage of a custom version of Spring (4.3.27).

If one need to use any other version of Spring Framework than the ones provided with Jahia, it is still possible
to achieve this by embedding that specific version of Spring inside the modules. If several modules were to require
those libraries then each of them will have to embed its own version of Spring though.

Because a dependency to an old (3.x) version of Spring Framework is inherited from the module parent provided
by Jahia, you should carefully review your module's pom (and generated MANIFEST.MF) to ensure your module is
properly instructed to use the version of Spring you are expecting. You can refer to the [pom.xml](./spring-embedded/pom.xml)
from this sample project for guidelines.

**Be aware that by using Spring that way, the Spring Framework will not be managed by the OSGI container**, and
that the same limitations than the one expressed with [spring-feature](#spring-feature) apply.

This sample module come with a [bundle activator](./spring-embedded/src/main/java/org/jahia/samples/module/springembedded/SpringActivator.java)
that illustrates how one could create an application context.


# spring-modules-samples

This repository contains sample modules to demonstrate different possible usage of <a href="https://spring.io/">Spring Framework</a>
in Jahia.

**Please note that direct usage of Spring in Jahia is discouraged.** OSGI does natively support several alternatives to handle
objects lifecycle and wiring (Declarative Services, Blueprint or CDI).

## Modules

- [OSGI Blueprint](#blueprint)
- [Gemini Blueprint](#gemini-blueprint)
- [Spring Feature](#spring-feature)
- [Spring Embedded](#spring-embedded)

## OSGI Blueprint

A module to demonstrate usage of OSGI blueprint. Blueprint is part of OSGI specifications
and provides a declarative way to define beans, services and service references using XML.
Blueprint has been inspired by Spring Framework and is thus very similar.

The implementation of OSGI blueprint provided by Jahia is [Gemini Blueprint](#gemini-blueprint)

**If you are looking for a way to declare OSGI services and wiring through XML files, this is
the recommended way to do so.**

However a known limitation regarding usage of OSGI blueprint with Jahia is that services must
implement an interface, otherwise they cannot be referenced from a bundle using OSGI blueprint.
The following entry is thus only valid if `com.bar.Foo` is a Java interface.

```xml
<reference id="foo" interface="com.bar.Foo"/>
```

Since some services exposed by Jahia do not implement an interface this may prevent you from
using OSGI Blueprint. As a workaround, you could leverage a specific of the [Gemini Blueprint](#gemini-blueprint)
implementation, and declare beans using the Spring namespace which is less restrictive in that matter.

## Gemini Blueprint

A module to demonstrate usage of Gemini Blueprint. Gemini Blueprint is an implementation
of OSGI blueprint and is built on top of Spring Framework.

Gemini Blueprint 1.0.2 is provided with Jahia, and is relying on Spring 3.2.13

Because it is build on top of Spring Framework, Gemini Blueprint give you access to the underlying
application context, events and lifecycle. It does also allow you to use Spring namespaces for XML
declarations as an alternative to the Blueprint equivalent. In that case, XML definitions are regular
Spring files with an additional namespace for OSGI specifics. One of the benefit of using the Spring namespaces
is that it will circonvene the limitation of OSGI Blueprint requiring services to implement an interface.
However another limitation you may face is that those services without an interface must have a default public
constructor in order to be instrumented properly by the framework.

**Please be aware that developing modules that relies on specifics of a given implementation of the OSGI Blueprint
specification is not advised. Forthcoming releases of Jahia may ship with an alternate implementation of the specification.**

## Spring Feature

A module to demonstrate usage of Spring 5, provided as a Karaf feature with Jahia.

Starting from Jahia 8, Spring 5.1.9.RELEASE_1 is provided as a feature. Module developers can now benefit
from using those shared libraries in their modules without having to embed Spring jars. This can come handy
if one need to benefit from sugar candies provided by Spring Framework.

**Be aware though that by using Spring that way, the Spring Framework will not be managed by the OSGI container.**
This means that the container will not create any application context on its own for your modules. It would
thus be the responsability of the module developer to handle the creation of application contexts if necessary
as well as any interaction between such contexts and the OSGI container.

## Spring Embedded

A module to demonstrate usage of a custom version of Spring (4.3.27).

If one would need to use any other version of Spring Framework than the ones provided with Jahia, it is possible
to embed the needed jars in the modules. If several modules were to require those libraries then they the Spring
jars would have to be embedded in each of those modules though.

**Be aware that by using Spring that way, the Spring Framework will not be managed by the OSGI container**, and
that the same limitations than the one expressed with [Spring Feature](#spring-feature) apply.

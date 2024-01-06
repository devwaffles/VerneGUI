VerneGUI
===

This library aims to eliminate most of the boilerplate associated with
creating custom GUIs. Inspired by the [ACF](https://github.com/aikar/commands) command framework, it uses annotations
to define the GUI elements. The syntax to add items to the GUI is declarative in nature and abstracts away the
implementation details of dealing with inventories and item stacks. A custom item builder is provided, along with
default implementations for the most common GUI elements.

## Features

- Declarative syntax for defining GUI contents.
- Custom item builder.
- Type-safe event handling.
- Clickable items.
- Pagination

## Installation

Gradle (Kotlin DSL)

```kotlin
// JitPack repository
maven("https://jitpack.io")
```
```kotlin
// Dependency
implementation("com.github.devwaffles:VerneGUI:1.2.2-beta")
```

Maven
```xml
<!-- JitPack repository -->
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```xml
<!-- Dependency -->
<dependency>
    <groupId>com.github.devwaffles</groupId>
    <artifactId>VerneGUI</artifactId>
    <version>1.2.2-beta</version>
</dependency>
```
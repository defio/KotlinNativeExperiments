# KotlinNativeExperiments
This repo is a Kotlin Native playground.

I've followed the [official guide](https://github.com/JetBrains/kotlin-native/blob/master/MULTIPLATFORM.md) to setup the iOS and the Android modules

This is the things that i wanna explore:

- [x] Enum
- [ ] Enum with arguments
- [ ] Sealed classes
- [ ] Object 
- [ ] Unit tests in the common module
- [ ] Distribution as library
   - [ ] maven for the (android + common) as android library
   - [ ] cocoapods for the (iOS + common) as framework


### Enum

Enum definition
```kotlin
enum class SimpleEnum{
    FIRST,SECOND,THIRD
}
```

Into the android module I'm able to do all the common stuff that we are used to do with enums (access to the cases, iterate and count). So we can write a test that look like

```kotlin
@Test
fun usageOfSimpleEnum() {
    assertEquals(3, SimpleEnum.values().size)
    SimpleEnum.values().forEach {
        println(it)
    }
}
```

Into the ios module I can access to the enum cases, but I don't know how iterate or get the enum size.
So we can write a test that look like

```swift
func testUsageOfSimpleEnum() {
    print(KotlinLibrarySimpleEnum.first)
    print(KotlinLibrarySimpleEnum.second)
    print(KotlinLibrarySimpleEnum.third)
}
```
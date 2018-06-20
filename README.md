# KotlinNativeExperiments
This repo is a Kotlin Native playground.

I've followed the [official guide](https://github.com/JetBrains/kotlin-native/blob/master/MULTIPLATFORM.md) to setup the iOS and the Android modules

This is the things that i wanna explore:

- [x] Enum
- [x] Enum with arguments
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
    FIRST, SECOND, THIRD
}
```

#### Android
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
#### iOS
Into the ios module I can access to the enum cases, but I don't know how iterate or get the enum size.
So we can write a test that look like

```swift
func testUsageOfSimpleEnum() {
    print(KotlinLibrarySimpleEnum.first)
    print(KotlinLibrarySimpleEnum.second)
    print(KotlinLibrarySimpleEnum.third)
}
```

### Enum with arguments

Enum definition
```kotlin
enum class EnumWithValue(val associatedValue: Int) {
    ONE(1),
    TWO(2)
}
```
#### Android
As in the previous section, even with the enums with argument, we can do everything we expect with an enum. So the test can be:
```kotlin
@Test
fun usageOfEnumWithValue() {
    assertEquals(2, EnumWithValue.values().size)
    EnumWithValue.values().forEach {
        println("$it : ${it.associatedValue}")
    }
}
```
#### iOS
As above, the same "issues" with the enum still be present even with the enums with values. At least we can access the value of the variable:
```swift
func testUsageOfEnumWithValue() {
    XCTAssert(KotlinLibraryEnumWithValue.one.associatedValue == 1)
    XCTAssert(KotlinLibraryEnumWithValue.two.associatedValue == 2)
}
```
# KotlinNativeExperiments
This repo is a Kotlin Native playground.

I've followed the [official guide](https://github.com/JetBrains/kotlin-native/blob/master/MULTIPLATFORM.md) to setup the iOS and the Android modules

This is the things that i wanna explore:

- [x] Enum
- [x] Enum with arguments
- [x] Sealed classes
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

### Sealed class

sealed class definition
```kotlin
sealed class SealedClassExample {
    class Success(val successMessage: String) : SealedClassExample()
    object Error : SealedClassExample()
}
```

#### Android
As usual into the android module we can do everything we expect with an sealed class. So the test can be:
```kotlin
@Test
fun usageOfSealedClass() {
    val successMessage = "We are the champions"
    val success: SealedClassExample = SealedClassExample.Success(successMessage)
    assertSuccess(success, successMessage)
    val error: SealedClassExample = SealedClassExample.Error
    assertError(error)

    when (success) {
        is SealedClassExample.Success -> println("SealedClassExample.Success instance")
        is SealedClassExample.Error -> println("SealedClassExample.Error instance")
    }
}

private fun assertSuccess(sealed: SealedClassExample, expectedMessage: String) {
    assertTrue(sealed is SealedClassExample.Success)
    assertTrue((sealed as SealedClassExample.Success).successMessage == expectedMessage)
}

private fun assertError(sealed: SealedClassExample) {
    assertTrue(sealed === SealedClassExample.Error)
}
```

#### iOS
As far as I know swift and objective-c have no concept of sealed classes. Therefore I do not think it is possible to have something similar to the `when` of kotlin. 
The tests we can have are like:
```swift
func testSealedClassUsage() {
    let successMessage = "I'm the winner"
    assertSuccessInstance(selead : KotlinLibrarySealedClassExampleSuccess(successMessage: successMessage), expectedMessage : successMessage)
    assertErrorInstance(selead : KotlinLibrarySealedClassExampleError())
}

private func assertSuccessInstance(selead : KotlinLibrarySealedClassExample, expectedMessage: String){
    XCTAssert(selead is KotlinLibrarySealedClassExampleSuccess,"Failed: sealed is \(String(describing: selead.self))")
    let success = selead as! KotlinLibrarySealedClassExampleSuccess
    XCTAssert(success.successMessage == expectedMessage,"Failed: message is '\(String(describing: success.successMessage))' the expected is '\(String(describing: expectedMessage))' ")
}

private func assertErrorInstance(selead : KotlinLibrarySealedClassExample){
    XCTAssert(selead is KotlinLibrarySealedClassExampleError)
}
```
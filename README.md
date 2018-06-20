# KotlinNativeExperiments
This repo is a Kotlin Native playground.

I've followed the [official guide](https://github.com/JetBrains/kotlin-native/blob/master/MULTIPLATFORM.md) to setup the iOS and the Android modules

This is the things that I wanna explore:

- [x] Enums
- [x] Enums with arguments
- [x] Sealed classes
- [x] Objects
- [x] Nullability
- [ ] Companion objects
- [ ] Generics
- [ ] Higher-Order Functions and Lambdas
- [ ] Unit tests in the common module
- [ ] Distribution as library
   - [ ] maven for the (android + common) as android library
   - [ ] cocoapods for the (iOS + common) as framework


## Enums

Enum definition
```kotlin
enum class SimpleEnum{
    FIRST, SECOND, THIRD
}
```

#### Android
Into the android module I'm able to do all the common stuff that we are used to do with enums (access to the cases, iterate and count). So we can write a test that looks like

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
So we can write a test that looks like

```swift
func testUsageOfSimpleEnum() {
    print(KotlinLibrarySimpleEnum.first)
    print(KotlinLibrarySimpleEnum.second)
    print(KotlinLibrarySimpleEnum.third)
}
```

## Enums with arguments

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
As above, the same "issues" with the enum are still present even with the enums with values. At least we can access the value of the variable:
```swift
func testUsageOfEnumWithValue() {
    XCTAssert(KotlinLibraryEnumWithValue.one.associatedValue == 1)
    XCTAssert(KotlinLibraryEnumWithValue.two.associatedValue == 2)
}
```

## Sealed classes

sealed class definition
```kotlin
sealed class SealedClassExample {
    class Success(val successMessage: String) : SealedClassExample()
    object Error : SealedClassExample()
}
```

#### Android
As usual into the android module we can do everything we expect with a sealed class. So the test can be:
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

## Objects

Object definition
```kotlin
object KotlinObject {
    val INITIAL_VALUE_FOR_NULLABLE_STRING_PROPERTY = null
    const val INITIAL_VALUE_FOR_INT_PROPERTY = 0
    const val INITIAL_VALUE_FOR_NULLABLE_ANY_PROPERTY = "any variable initial state"

    var internalStateNullableString: String? = INITIAL_VALUE_FOR_NULLABLE_STRING_PROPERTY
    var internalStateInt: Int = INITIAL_VALUE_FOR_INT_PROPERTY
    var internalStateNullableAny: Any? = INITIAL_VALUE_FOR_NULLABLE_ANY_PROPERTY

}
```

#### Android
As usual, no surprises

```kotlin
@Test
fun usageOfKotlinObject() {
    assertTrue(KotlinObject === KotlinObject)
}
```
#### iOS
Well, even here in the ios module the kotlin object is a singleton :tada:
`KotlinLibraryKotlinObject()` give us always the same instance

```swift
func testKotlinObjectUsage() {
    XCTAssert(KotlinLibraryKotlinObject() === KotlinLibraryKotlinObject())
}
```

## Nullability

Reusing the object defined in the section above

#### Android
Once again, no surprises

```kotlin
@Test
fun usageOfKotlinObject_properties() {
    playWithIntProperty()
    playWithNullableAnyProperty()
    playWithNullableStringProperty()
}

private fun playWithIntProperty() {
    assertTrue(KotlinObject.internalStateInt == KotlinObject.INITIAL_VALUE_FOR_INT_PROPERTY)
    KotlinObject.internalStateInt = 123
    assertTrue(KotlinObject.internalStateInt == 123)
}

private fun playWithNullableAnyProperty() {
    assertTrue(KotlinObject.internalStateNullableAny == KotlinObject.INITIAL_VALUE_FOR_NULLABLE_ANY_PROPERTY)
    KotlinObject.internalStateNullableAny = 123
    assertTrue(KotlinObject.internalStateInt == 123)
    KotlinObject.internalStateNullableAny = "Now I'm a string"
    assertTrue(KotlinObject.internalStateNullableAny == "Now I'm a string")
}

private fun playWithNullableStringProperty() {
    assertTrue(KotlinObject.internalStateNullableString == KotlinObject.INITIAL_VALUE_FOR_NULLABLE_STRING_PROPERTY)
    KotlinObject.internalStateNullableString = "Now I'm not null"
    assertTrue(KotlinObject.internalStateNullableString == "Now I'm not null")
}
```
#### iOS
There's something weird happening

```swift
func testKotlinObjectUsage_properties() {
    playWithIntProperty()
    playWithNullableAnyProperty()
    playWithNullableStringProperty()
}

private func playWithIntProperty(){
    XCTAssert(KotlinLibraryKotlinObject().internalStateInt == KotlinLibraryKotlinObject().initial_VALUE_FOR_INT_PROPERTY) // #1
    KotlinLibraryKotlinObject().internalStateInt = 123
    XCTAssert(KotlinLibraryKotlinObject().internalStateInt == 123)
}

private func playWithNullableAnyProperty(){
    XCTAssert((KotlinLibraryKotlinObject().internalStateNullableAny as! String) == KotlinLibraryKotlinObject().initial_VALUE_FOR_NULLABLE_ANY_PROPERTY) 
    KotlinLibraryKotlinObject().internalStateNullableAny = 123
    XCTAssert((KotlinLibraryKotlinObject().internalStateNullableAny as! Int) == 123) 
    KotlinLibraryKotlinObject().internalStateNullableAny = "Now I'm a string"
    XCTAssert((KotlinLibraryKotlinObject().internalStateNullableAny as! String) == "Now I'm a string")
}

private func playWithNullableStringProperty(){
    XCTAssert(KotlinLibraryKotlinObject().internalStateNullableString == (KotlinLibraryKotlinObject().initial_VALUE_FOR_NULLABLE_STRING_PROPERTY as! String?)) #2
    KotlinLibraryKotlinObject().internalStateNullableString = "Now I'm not null"
    XCTAssert(KotlinLibraryKotlinObject().internalStateNullableString! == "Now I'm not null")
}
```

1. the kotlin val defined as `const val INITIAL_VALUE_FOR_INT_PROPERTY = 0` is mapped in `initial_VALUE_FOR_INT_PROPERTY` with "initial" lowercase
2. the kotlin val defined as `val INITIAL_VALUE_FOR_NULLABLE_STRING_PROPERTY = null` has `Nothing?` as type [(doc as reference :grimacing:)](https://kotlinlang.org/docs/reference/exceptions.html#the-nothing-type).
Xcode tell us that the kotlin val was mapped into a `var initial_VALUE_FOR_NULLABLE_STRING_PROPERTY: KotlinLibraryStdlibNothing? ` so, a cast to `String?` seems to be inevitable
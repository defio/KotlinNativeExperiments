# KotlinNativeExperiments
This repo is a Kotlin Native playground.

I've followed the [official guide](https://github.com/JetBrains/kotlin-native/blob/master/MULTIPLATFORM.md) to setup the iOS and the Android modules

This is the things that I wanna explore:

- [x] Enums
- [x] Enums with arguments
- [x] Sealed classes
- [x] Objects
- [x] Nullability
- [x] Companion objects
- [ ] Generics
- [x] Higher-Order Functions and Lambdas
- [x] DSL
- [ ] Unit tests in the common module
- [ ] Distribution as library
   - [ ] maven for the (android + common) as android library
   - [ ] cocoapods for the (iOS + common) as framework


<!-- TOC -->

- [KotlinNativeExperiments](#kotlinnativeexperiments)
    - [Enums](#enums)
            - [Android](#android)
            - [iOS](#ios)
    - [Enums with arguments](#enums-with-arguments)
            - [Android](#android)
            - [iOS](#ios)
    - [Sealed classes](#sealed-classes)
            - [Android](#android)
            - [iOS](#ios)
    - [Objects](#objects)
            - [Android](#android)
            - [iOS](#ios)
    - [Nullability](#nullability)
            - [Android](#android)
            - [iOS](#ios)
    - [Companion Objects](#companion-objects)
            - [Android](#android)
            - [iOS](#ios)
    - [Higher-Order Functions and Lambdas](#higher-order-functions-and-lambdas)
            - [Android](#android)
            - [iOS](#ios)
    - [DSL](#dsl)
            - [Android](#android)
            - [iOS](#ios)

<!-- /TOC -->

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
Xcode tell us that the kotlin val was mapped into a `var initial_VALUE_FOR_NULLABLE_STRING_PROPERTY: KotlinLibraryStdlibNothing?` so, a cast to `String?` seems to be inevitable

## Companion Objects

Companion object definition

```kotlin
class Container(val state: String) {

    fun getDecoratedState(): String {
        return "${sharedVar ?: ""} $state"
    }

    companion object {
        const val MY_SUPER_NICE_CONST = 12

        var sharedVar: String? = null
    }
}
```

#### Android

As usual, no surprises

```kotlin
@Test
fun usageOfCompanionObject() {
    assertEquals(12, Container.MY_SUPER_NICE_CONST)
    val firstContainer = Container("first")
    val secondContainer = Container("second")

    assertFalse(firstContainer === secondContainer)

    Container.sharedVar = "Hello"

    assertEquals("Hello first", firstContainer.getDecoratedState())
    assertEquals("Hello second", secondContainer.getDecoratedState())
}
```

#### iOS

As we could see into the objects section, even the swift usage of a companion object is straightforward

```swift
func testCompanionObjectUsage() {
    XCTAssertEqual(12, KotlinLibraryContainerCompanion().my_SUPER_NICE_CONST)
    let firstContainer = KotlinLibraryContainer(state: "first")
    let secondContainer = KotlinLibraryContainer(state: "second")
    
    XCTAssertFalse(firstContainer === secondContainer)
    
    KotlinLibraryContainerCompanion().sharedVar = "Hello"
    
    XCTAssert(firstContainer.getDecoratedState() == "Hello first")
    XCTAssert(secondContainer.getDecoratedState() == "Hello second")
}
```

## Higher-Order Functions and Lambdas

```kotlin
fun topLevelFunction(): String {
    return "I am a top level"
}

fun Int.extensionFuction(): Int {
    return this * 2
}

fun higherOrderFunctionWithParameter(a: Int, b: Int, f: (Int, Int) -> Int): Int {
    return f(a, b)
}

fun higherOrderFunctionWithReturn(functionType: FunctionType): (Int, Int) -> Int {
    return when (functionType) {
        FunctionType.SUM -> { a, b -> a + b }
        FunctionType.MULTIPLY -> { a, b -> a * b }
    }
}

fun higherOrderFunctionBoth(a: Double, b: Double, f: (Double, Double) -> Double): Double {
    return f(a, b)
}

fun lambdaWithReceiver(arg: Int, func: Int.() -> Int): Int {
    return func(arg)
}

enum class FunctionType {
    SUM, MULTIPLY
}
```

#### Android

As usual, no surprises. It is still useful to report the tests for those who do not usually develop in kotlin.

```kotlin
@Test
fun topLevelFunctionUsage() {
    assertEquals("I am a top level", topLevelFunction())
}

@Test
fun extensionFunctionUsage() {
    assertEquals(4, 2.extensionFuction())
}

@Test
fun higherOrderFunctionWithParameterUsage() {
    assertEquals(5, higherOrderFunctionWithParameter(2, 3) { a, b -> a + b })
}

@Test
fun higherOrderFunctionWithReturnUsage() {
    assertEquals(5, higherOrderFunctionWithReturn(FunctionType.SUM)(2, 3))

    val higherOrderFunctionWithReturn = higherOrderFunctionWithReturn(FunctionType.MULTIPLY)
    assertEquals(6, higherOrderFunctionWithReturn(2, 3))
}

@Test
fun higherOrderFunctionBothUsage() {
    assertEquals(25, higherOrderFunctionBoth(2.0, 3.0) { a, b -> (a + b).pow(2) }.toInt())
}
```

#### iOS

Kotlin **top level functions** are accessible directly from `KotlinLibrary`.

Also the **extension function** are accessible via `KotlinLibrary`, as we could expect, since kotlin generates objective-c code, extensions can not be invoked directly on the type that extend.

The higher order functions seems to be mapped as function pointers, for this reason the parameters defined as `double` in kotlin are now `NSNumber`

```swift
func testTopLevelFunctionUsage() {
    XCTAssert("I am a top level" == KotlinLibrary.topLevelFunction())
}

func testExtensionFunctionUsage() {
    XCTAssert(4 == KotlinLibrary.extensionFuction(2))
}

func testHigherOrderFunctionWithParameterUsage() {
    XCTAssert(5 == KotlinLibrary.higherOrderFunctionBoth(a: 2, b: 3, f: {
        NSNumber(value: ($0.doubleValue + $1.doubleValue))
    }))
}

func testHigherOrderFunctionWithReturnUsage() {
    XCTAssert(5 == KotlinLibrary.higherOrderFunctionWithReturn(functionType: KotlinLibraryFunctionType.sum)(2, 3))

    let higherOrderFunctionWithReturn = KotlinLibrary.higherOrderFunctionWithReturn(functionType: KotlinLibraryFunctionType.multiply)
    XCTAssert(6 == higherOrderFunctionWithReturn(2, 3))
}

func testHigherOrderFunctionBothUsage() {
    XCTAssert(25 == KotlinLibrary.higherOrderFunctionBoth(a: 2, b: 3, f: {
        NSNumber(value: pow(($0.doubleValue + $1.doubleValue),2))
    }))
}
```

## DSL

From the [official documentation](https://kotlinlang.org/docs/reference/type-safe-builders.html#type-safe-builders)

>By using well-named functions as builders in combination with function literals 
>with receiver it is possible to create type-safe, statically-typed builders in >Kotlin.  
>
>Type-safe builders allow for creating Kotlin-based domain-specific languages (DSLs) suitable for building complex hierarchical data structures in a semi-declarative way. Some of the example use cases for the builders are:  
>
> - Generating markup with Kotlin code, such as HTML or XML;
> - Programmatically laying out UI components: Anko
> - Configuring routes for a web server: Ktor.

Here everything we need for define a micro DSL:

```kotlin
data class Port(var value: Int = 0, var isSecure: Boolean = false) {
    operator fun invoke(init: Port.() -> Unit) {
        init()
    }
}

data class Configuration(var host: String = "", var port: Port = Port())

fun configuration(init: Configuration.() -> Unit): Configuration {
    val configuration = Configuration()
    configuration.init()
    return configuration
}
```

- Two data class: `Port` and `Configuration`
- An operator overload: `operator fun invoke(init: Port.() -> Unit)`
- A lambda with receiver `fun configuration(init: Configuration.() -> Unit): Configuration`

#### Android

With the code above we can write a test like:

```kotlin
@Test
fun dslUsage() {
    val conf = configuration {
        host = "127.0.0.1"
        port {
            value = 8080
            isSecure = false
        }
    }
    
    assertEquals("127.0.0.1", conf.host)
    assertEquals(8080, conf.port.value)
    assertEquals(false, conf.port.isSecure)
}
```

#### iOS
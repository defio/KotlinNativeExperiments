package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.Container
import com.ndefiorenze.EnumWithValue
import com.ndefiorenze.FunctionType
import com.ndefiorenze.Greeting
import com.ndefiorenze.KotlinObject
import com.ndefiorenze.SealedClassExample
import com.ndefiorenze.SimpleEnum
import com.ndefiorenze.configuration
import com.ndefiorenze.extensionFuction
import com.ndefiorenze.higherOrderFunctionBoth
import com.ndefiorenze.higherOrderFunctionWithParameter
import com.ndefiorenze.higherOrderFunctionWithReturn
import com.ndefiorenze.lambdaWithReceiver
import com.ndefiorenze.topLevelFunction
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.pow

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun sampleTest() {
        assertEquals("Hello, Android", Greeting().greeting())
    }

    @Test
    fun usageOfSimpleEnum() {
        assertEquals(3, SimpleEnum.values().size)
        SimpleEnum.values().forEach {
            println(it)
        }
    }

    @Test
    fun usageOfEnumWithValue() {
        assertEquals(2, EnumWithValue.values().size)
        EnumWithValue.values().forEach {
            println("$it : ${it.associatedValue}")
        }
    }

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

    @Test
    fun usageOfKotlinObject() {
        assertTrue(KotlinObject === KotlinObject)
    }

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



}

package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.EnumWithValue
import com.ndefiorenze.Greeting
import com.ndefiorenze.KotlinObject
import com.ndefiorenze.SealedClassExample
import com.ndefiorenze.SimpleEnum
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

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

}

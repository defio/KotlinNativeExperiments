package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.EnumWithValue
import com.ndefiorenze.Greeting
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



}

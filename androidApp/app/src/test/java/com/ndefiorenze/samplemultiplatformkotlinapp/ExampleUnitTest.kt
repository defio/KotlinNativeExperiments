package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.Greeting
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals("Hello, Android", Greeting().greeting())
    }
}

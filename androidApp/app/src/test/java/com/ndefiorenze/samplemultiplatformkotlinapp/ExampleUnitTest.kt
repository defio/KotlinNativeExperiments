package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.Greeting
import com.ndefiorenze.SimpleEnum
import org.junit.Assert.assertEquals
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
}

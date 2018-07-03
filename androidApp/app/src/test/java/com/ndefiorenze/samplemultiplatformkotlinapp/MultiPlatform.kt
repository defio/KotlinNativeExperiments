package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.Greeting
import org.junit.Assert
import org.junit.Test

class MultiPlatform {

    @Test
    fun sampleTest() {
        Assert.assertEquals("Hello, Android", Greeting().greeting())
    }

}
package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.Container
import org.junit.Assert
import org.junit.Test

class CompanionObject {

    @Test
    fun usageOfCompanionObject() {
        Assert.assertEquals(12, Container.MY_SUPER_NICE_CONST)
        val firstContainer = Container("first")
        val secondContainer = Container("second")

        Assert.assertFalse(firstContainer === secondContainer)

        Container.sharedVar = "Hello"

        Assert.assertEquals("Hello first", firstContainer.getDecoratedState())
        Assert.assertEquals("Hello second", secondContainer.getDecoratedState())
    }

}
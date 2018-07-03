package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.FunctionType
import com.ndefiorenze.extensionFuction
import com.ndefiorenze.higherOrderFunctionBoth
import com.ndefiorenze.higherOrderFunctionWithParameter
import com.ndefiorenze.higherOrderFunctionWithReturn
import com.ndefiorenze.topLevelFunction
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import kotlin.math.pow

class Functions {

    @Test
    fun topLevelFunctionUsage() {
        Assert.assertEquals("I am a top level", topLevelFunction())
    }

    @Test
    fun extensionFunctionUsage() {
        Assert.assertEquals(4, 2.extensionFuction())
    }

    @Test
    fun higherOrderFunctionWithParameterUsage() {
        Assert.assertEquals(5, higherOrderFunctionWithParameter(2, 3) { a, b -> a + b })
    }

    @Test
    fun higherOrderFunctionWithReturnUsage() {
        Assert.assertEquals(5, higherOrderFunctionWithReturn(FunctionType.SUM)(2, 3))

        val higherOrderFunctionWithReturn = higherOrderFunctionWithReturn(FunctionType.MULTIPLY)
        assertEquals(6, higherOrderFunctionWithReturn(2, 3))
    }

    @Test
    fun higherOrderFunctionBothUsage() {
        assertEquals(25, higherOrderFunctionBoth(2.0, 3.0) { a, b -> (a + b).pow(2) }.toInt())
    }

}
package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.EnumWithValue
import com.ndefiorenze.SimpleEnum
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test

class Enum{

    @Test
    fun usageOfSimpleEnum() {
        assertEquals(3, SimpleEnum.values().size)
        SimpleEnum.values().forEach {
            println(it)
        }
    }

    @Test
    fun usageOfEnumWithValue() {
        Assert.assertEquals(2, EnumWithValue.values().size)
        EnumWithValue.values().forEach {
            println("$it : ${it.associatedValue}")
        }
    }

}
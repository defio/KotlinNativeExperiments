package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.KotlinObject
import org.junit.Assert
import org.junit.Test

class Object {

    @Test
    fun usageOfKotlinObject() {
        Assert.assertTrue(KotlinObject === KotlinObject)
    }

    @Test
    fun usageOfKotlinObject_properties() {
        playWithIntProperty()
        playWithNullableAnyProperty()
        playWithNullableStringProperty()
    }

    private fun playWithIntProperty() {
        Assert.assertTrue(KotlinObject.internalStateInt == KotlinObject.INITIAL_VALUE_FOR_INT_PROPERTY)
        KotlinObject.internalStateInt = 123
        Assert.assertTrue(KotlinObject.internalStateInt == 123)
    }

    private fun playWithNullableAnyProperty() {
        Assert.assertTrue(KotlinObject.internalStateNullableAny == KotlinObject.INITIAL_VALUE_FOR_NULLABLE_ANY_PROPERTY)
        KotlinObject.internalStateNullableAny = 123
        Assert.assertTrue(KotlinObject.internalStateInt == 123)
        KotlinObject.internalStateNullableAny = "Now I'm a string"
        Assert.assertTrue(KotlinObject.internalStateNullableAny == "Now I'm a string")
    }

    private fun playWithNullableStringProperty() {
        Assert.assertTrue(KotlinObject.internalStateNullableString == KotlinObject.INITIAL_VALUE_FOR_NULLABLE_STRING_PROPERTY)
        KotlinObject.internalStateNullableString = "Now I'm not null"
        Assert.assertTrue(KotlinObject.internalStateNullableString == "Now I'm not null")
    }

}
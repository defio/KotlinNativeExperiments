package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.SealedClassExample
import org.junit.Assert
import org.junit.Test

class SealedClass{

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
        Assert.assertTrue(sealed is SealedClassExample.Success)
        Assert.assertTrue((sealed as SealedClassExample.Success).successMessage == expectedMessage)
    }

    private fun assertError(sealed: SealedClassExample) {
        Assert.assertTrue(sealed === SealedClassExample.Error)
    }

}
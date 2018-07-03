package com.ndefiorenze.samplemultiplatformkotlinapp

import com.ndefiorenze.configuration
import org.junit.Assert
import org.junit.Test

class DSL {

    @Test
    fun dslUsage() {
        val conf = configuration {
            host = "127.0.0.1"
            port {
                value = 8080
                isSecure = false
            }
        }

        Assert.assertEquals("127.0.0.1", conf.host)
        Assert.assertEquals(8080, conf.port.value)
        Assert.assertEquals(false, conf.port.isSecure)
    }

}
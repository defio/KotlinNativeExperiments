package com.ndefiorenze

data class Port(var value: Int = 0, var isSecure: Boolean = false) {
    operator fun invoke(init: Port.() -> Unit) {
        init()
    }
}

data class Configuration(var host: String = "", var port: Port = Port())

fun configuration(init: Configuration.() -> Unit): Configuration {
    val configuration = Configuration()
    configuration.init()
    return configuration
}
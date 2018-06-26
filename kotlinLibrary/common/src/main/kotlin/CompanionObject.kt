package com.ndefiorenze

class Container(val state: String) {

    fun getDecoratedState(): String {
        return "${sharedVar ?: ""} $state"
    }

    companion object {
        const val MY_SUPER_NICE_CONST = 12

        var sharedVar: String? = null
    }
}
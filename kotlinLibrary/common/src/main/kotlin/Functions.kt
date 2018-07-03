package com.ndefiorenze

fun topLevelFunction(): String {
    return "I am a top level"
}

fun Int.extensionFuction(): Int {
    return this * 2
}

fun higherOrderFunctionWithParameter(a: Int, b: Int, f: (Int, Int) -> Int): Int {
    return f(a, b)
}

fun higherOrderFunctionWithReturn(functionType: FunctionType): (Int, Int) -> Int {
    return when (functionType) {
        FunctionType.SUM -> { a, b -> a + b }
        FunctionType.MULTIPLY -> { a, b -> a * b }
    }
}

fun higherOrderFunctionBoth(a: Double, b: Double, f: (Double, Double) -> Double): Double {
    return f(a, b)
}

fun lambdaWithReceiver(arg: Int, func: Int.() -> Int): Int {
    return func(arg)
}


enum class FunctionType {
    SUM, MULTIPLY
}


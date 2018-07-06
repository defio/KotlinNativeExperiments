package com.ndefiorenze

object KotlinObject {
    val INITIAL_VALUE_FOR_NULLABLE_STRING_PROPERTY = null
    const val INITIAL_VALUE_FOR_INT_PROPERTY = 0
    const val INITIAL_VALUE_FOR_NULLABLE_ANY_PROPERTY = "any variable initial state"

    var internalStateNullableString: String? = INITIAL_VALUE_FOR_NULLABLE_STRING_PROPERTY
    var internalStateInt: Int = INITIAL_VALUE_FOR_INT_PROPERTY
    var internalStateNullableAny: Any? = INITIAL_VALUE_FOR_NULLABLE_ANY_PROPERTY
}
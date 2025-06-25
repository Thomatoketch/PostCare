package com.postcare.app

data class Question(
    val text: String,
    val hasTextField: Boolean = false,
    var answer: String = ""
)
package com.example.lint.exception

import java.io.File

fun funcA() {
    try {
        throwEx(4, 1)
    } catch (ex: Exception) {

    }
}

fun throwEx(param1: Int, param2: Int) {
    throw Exception()
}

fun main() {
    val file = File("abc")
    val isSuccess = file.createNewFile()

    throwEx(5,6)
    ExceptionJava.funException()

}



package com.example.lint.newthread


class NewThreadKotlin {
    private val thread = Thread(Runnable {
        //run sth
    })

    private val thread2 = Thread()

    private val thread3 = Thread("thread")
}
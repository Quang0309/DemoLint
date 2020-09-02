package com.example.lint.exception;

import java.io.File;
import java.io.IOException;

public class ExceptionJava {
    static void funException() throws IOException {
        throw new IOException();
    }

    static void funcD() throws IOException {
        funException();
    }

    void funC() {
        File file = new File("abc");
    }


}

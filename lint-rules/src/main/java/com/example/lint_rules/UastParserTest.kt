@file:Suppress("UnstableApiUsage")

package com.example.lint_rules

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.*
import org.junit.Test

class UastParserTest : LintDetectorTest() {
    @Test
    fun testKotlin() {
        lint().files(
            kotlin(
                """
                package test
                    class MyTest {
                        val s: String = "Hello world"
                        private fun main(args: String?) {
                            val a = s + "1"
                            throw java.lang.Exception
                        }
                        private fun funcA() {
                            try{
                                main("a")
                            } catch (ex : java.lang.Exception) {
                            
                            }
                        }
                    }
                    fun throwEx() {
                        throw Exception()
                    }

                    fun funcA() {
                        throwEx()
                    }
            """
            ).indented()
        ).run()
    }

    @Test
    fun testJava() {
        lint().files(
            java(
                    """
                package test;
                    class MyTest {
                        String s = "Hello world";
                        private fun main(String s) {
                            String a = s + "1";
                        }
                    }
            """
            ).indented()
        ).run()
    }

    @Test
    fun testKotlinException() {
        lint().files(
                kotlin(
                        """
                    fun throwEx() {
                        throw Exception()
                    }

                    fun funcA() {
                        throwEx()
                    }
                    
                    fun funcB(){
                        val file = File("abc")
                        file.createNewFile()
                    }
            """
                ).indented()
        ).run()
    }

    override fun getDetector(): Detector = ParcelableDetector()

    override fun getIssues(): MutableList<Issue> = mutableListOf(ParcelableDetector.IssueParcelable)

}
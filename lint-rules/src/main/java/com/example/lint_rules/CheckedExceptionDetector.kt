package com.example.lint_rules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.*
import org.jetbrains.uast.java.JavaUCallExpression
import org.jetbrains.uast.java.JavaUDeclarationsExpression


@Suppress("UnstableApiUsage")
class CheckedExceptionDetector: Detector(), Detector.UastScanner {
    companion object {
        val IssueException = Issue.create(
            "Exception",
            "Should try catch this exception",
            "Fail to try catch exception could lead to crash",
            Category.CORRECTNESS,
            10,
            Severity.FATAL,
            Implementation(CheckedExceptionDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UCallExpression::class.java)
    }


    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return CheckedExceptionHandler(context)
    }


}
class CheckedExceptionHandler(private val context: JavaContext) : UElementHandler() {
    override fun visitCallExpression(node: UCallExpression) {
        if (node::class == JavaUCallExpression::class) return
        //val isJava = if (declarationNode.javaClass == JavaUDeclarationsExpression::class.java) true else false
        val method = node.resolve() ?: return
        val uMethod = method.getUMethod() ?: return
        var isThrowException = false
        uMethod.asRecursiveLogString {
            if (it is UThrowExpression)
                isThrowException= true
            ""
        }

        if (isThrowException) {
            val tryExpression = node.getParentOfType<UTryExpression>(UTryExpression::class.java)
            if (tryExpression == null) {
                context.report(CheckedExceptionDetector.IssueException, context.getLocation(node), "Need to handle exception here")
            }
        }
    }
}
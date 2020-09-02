@file:Suppress("UnstableApiUsage")

package com.example.lint_rules

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

class NewThreadDetector : Detector(), Detector.UastScanner {
    companion object {
        val IssueNewThread = Issue.create(
            "NewThreadId",
            "Should not create Thread",
            "Please use Executor instead of Thread",
            Category.CORRECTNESS, 1, Severity.ERROR,
            Implementation(NewThreadDetector::class.java, Scope.JAVA_FILE_SCOPE))
    }
    override fun getApplicableConstructorTypes(): List<String>? {
        return listOf("java.lang.Thread")
    }

    override fun visitConstructor(
        context: JavaContext,
        node: UCallExpression,
        constructor: PsiMethod
    ) {
        context.report(
            IssueNewThread, node, context.getLocation(node), "new Thread will create overhead " +
                    "-> reduce application performance"
        )
    }
}
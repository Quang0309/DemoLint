@file:Suppress("UnstableApiUsage")

package com.example.lint_rules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.intellij.psi.JavaElementVisitor
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiMethodCallExpression
import org.jetbrains.uast.UMethod
import java.util.*

class DupCodeDetector : Detector(), Detector.UastScanner {
    override fun getApplicableUastTypes() = listOf(UMethod::class.java)

    override fun createUastHandler(context: JavaContext) =
        DuplicateHandler(context)

}

class DuplicateHandler(private val context: JavaContext) : UElementHandler() {
    override fun visitMethod(node: UMethod) {
        if (node.name.contains("(.*file.*size.*)+|(.*size.*file.*)+".toRegex(RegexOption.IGNORE_CASE))) {
            context.report(
                IssueProperlyDuplicateFun,
                node,
                context.getLocation(node),
                "The Util may have the function you need")
        }
    }

    companion object {
        val IssueProperlyDuplicateFun = Issue.create("DuplicationId",
            "Maybe duplicate method",
            "The Util may have the function you need",
            Implementation(DupCodeDetector::class.java, EnumSet.of(Scope.JAVA_FILE)),
            category = Category.USABILITY,
            priority = 5,
            severity = Severity.INFORMATIONAL,
            enabledByDefault = true)
    }
}
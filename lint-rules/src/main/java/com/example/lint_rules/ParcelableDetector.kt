@file:Suppress("UnstableApiUsage")

package com.example.lint_rules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement
import org.jetbrains.uast.asRecursiveLogString

class ParcelableDetector : Detector(), Detector.UastScanner {
    companion object {
        val IssueParcelable = Issue.create(
            "Parcelable",
            "Should annotate with @Parcelize",
            "Use can use @Parcelable to auto generate code ",
            Category.COMPLIANCE,
            5,
            Severity.INFORMATIONAL,
            Implementation(ParcelableDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UClass::class.java)
    }

    override fun applicableSuperClasses(): List<String>? {
        return listOf("android.os.Parcelable")
    }

    override fun visitClass(context: JavaContext, declaration: UClass) {
        super.visitClass(context, declaration)

        var found = false
        declaration.uAnnotations.forEach {
            if (it.qualifiedName.equals("kotlinx.android.parcel.Parcelize", true)) {
                found = true
            }
        }
        if (!found) {
            context.report(
                IssueParcelable, declaration, context.getNameLocation(declaration), "Should use @Parcelize"
            )
        }
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        println(context.uastFile?.asRecursiveLogString())
        return super.createUastHandler(context)
    }
}
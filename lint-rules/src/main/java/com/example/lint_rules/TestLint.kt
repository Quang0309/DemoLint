@file:Suppress("UnstableApiUsage")

package com.example.lint_rules

import com.android.tools.lint.checks.infrastructure.LintDetectorTest

import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import org.junit.Test

class TestLint : LintDetectorTest() {
    @Test
    fun testParcel() {
        lint().files(
            kotlin(
                """
                class Parcel : Parcelable {
                    override fun writeToParcel(dest: Parcel?, flags: Int) {
                    TODO("Not yet implemented")
                    }

                override fun describeContents(): Int {
                    TODO("Not yet implemented")
                    }
                }
            """
            ).indented()
        )
            .issues(ParcelableDetector.IssueParcelable)
            .run()
            .expectClean()
    }

    override fun getDetector(): Detector = ParcelableDetector()

    override fun getIssues(): MutableList<Issue> = mutableListOf(ParcelableDetector.IssueParcelable)

}


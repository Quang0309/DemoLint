@file:Suppress("UnstableApiUsage")

package com.example.lint_rules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.*
import com.example.lint_rules.DuplicateHandler.Companion.IssueProperlyDuplicateFun
import com.example.lint_rules.NewThreadDetector.Companion.IssueNewThread
import com.example.lint_rules.ParcelableDetector.Companion.IssueParcelable
import com.example.lint_rules.CheckedExceptionDetector.Companion.IssueException


class IssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(
            IssueProperlyDuplicateFun,
            IssueNewThread,
            IssueParcelable,
            IssueException
        )

    override val api: Int
        get() = CURRENT_API
}




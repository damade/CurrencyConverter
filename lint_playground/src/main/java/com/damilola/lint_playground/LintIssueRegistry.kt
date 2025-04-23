package com.damilola.lint_playground

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class LintIssueRegistry: IssueRegistry() {

    override val issues: List<Issue> = listOf(
        TestMethodNameDetector.FORMAT,
    )

    override val api: Int = CURRENT_API

    override val minApi: Int = 12

    override val vendor: Vendor = Vendor(
        vendorName = "Currency Converter",
        feedbackUrl = "https://github.com/damade/CurrencyConverter/issues",
        contact = "https://github.com/damade",
    )
}
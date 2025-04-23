package com.damilola.lint_playground

import com.android.tools.lint.detector.api.AnnotationInfo
import com.android.tools.lint.detector.api.AnnotationUsageInfo
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.android.tools.lint.detector.api.TextFormat.RAW
import com.damilola.lint_playground.utils.issue
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.getParentOfType
import kotlin.io.path.Path

/**
 *
 */
class TestMethodNameDetector : Detector(), SourceCodeScanner {

    override fun applicableAnnotations() = listOf("org.junit.Test")

    override fun visitAnnotationUsage(
        context: JavaContext,
        element: UElement,
        annotationInfo: AnnotationInfo,
        usageInfo: AnnotationUsageInfo,
    ) {
        //val annotatedFunc = usageInfo.usage.getParentOfType<UMethod>(strict = true) ?: return
        val annotatedFunc = usageInfo.referenced
        println("Beginning")
        println(annotatedFunc)
        val method = usageInfo.referenced as? PsiMethod ?: return

        println("During Visit: ${method.name}")

        method.detectFormat(context, usageInfo)
    }

    private fun JavaContext.isUnitTest() = Path(path = "test") in file.toPath()

    private fun PsiMethod.detectFormat(
        context: JavaContext,
        usageInfo: AnnotationUsageInfo,
    ) {
        if (!context.isUnitTest()) return
        println("Before Matching: ${name}")
        val regex = Regex("""^when\s+([a-zA-Z\s'"\-.,!?]+?),\s+then\s+([a-zA-Z\s'"\-.,!?]+)$""")
        if (regex.matches(name)) return
        println("After Matching: ${name}")
        context.report(
            issue = FORMAT,
            scope = usageInfo.usage,
            location = context.getNameLocation(element = this),
            message = FORMAT.getBriefDescription(RAW),
        )
    }

    companion object {

        @JvmField
        val FORMAT: Issue = issue(
            id = "TestMethodNameFormat",
            briefDescription = "Test method does not follow the `when, then` format",
            explanation =  """
                Test method should follow the `when_then` format. \n
                Example will be be following: \n\n
                @Test
                fun `when clearConversionHistory fails, then an exception is thrown`(){
                    // given
               
                    // when
                    
                    // then
                    
                }
            """.trimIndent(),
        )
    }
}
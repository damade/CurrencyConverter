package com.damilola.lint_playground

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import org.junit.Test

class TestFunctionCommentDetectorLintTest: BaseKotlinLintTest() {

    override fun getDetector(): Detector {
        return TestFunctionCommentDetector()
    }

    override fun getIssues(): List<Issue> {
        return listOf(TestFunctionCommentDetector.ISSUE)
    }

    @Test
    fun testCommentDetectionInTestSuccess() {
            lint()
            .files(
                *commonStubs,
                TestFiles.kotlin(
                    """
                    package com.damilola.ft_currency
                    
                    import org.junit.Test

                    class CurrencyConverterTest {
                    
                        @Test
                        fun `test conversion from dollar to naira`() {
                            // given
                            val dollar = 100
                            val naira = 500
                            
                            // when
                            val result = dollar * naira
                            
                            // then
                            assert(result == 50000)
                        }
                    }
                    """
                ).indented()

            )
            .issues(TestFunctionCommentDetector.ISSUE)
            .run()
            .expect("No warnings.")
    }

    @Test
    fun testCommentDetectionInTestFailure() {
        lint()
            .files(
                *commonStubs,
                TestFiles.kotlin(
                    """
                    package com.damilola.ft_currency
                    
                    import org.junit.Test

                    class CurrencyConverterTest {
                    
                        @Test
                        fun `test conversion from dollar to naira`() {
                            // TODO Given
                            val dollar = 100
                            val naira = 500
                            
                            // When the here is a typo
                            val result = dollar * naira
                            
                            // then
                            assert(result == 50000)
                        }
                    }
                    """
                ).indented()

            )
            .issues(TestFunctionCommentDetector.ISSUE)
            .run()
            .expect("Test comment should be restricted to // given, // when, and // then")
    }

    @Test
    fun testCommentDetectionInTestSkip() {
        lint()
            .files(
                *commonStubs,
                TestFiles.kotlin(
                    """
                    package com.damilola.ft_currency
                    
                    import org.junit.Test

                    class CurrencyConverterTest {
                        
                        fun `test conversion from dollar to naira`() {
                            // TODO Given
                            val dollar = 100
                            val naira = 500
                            
                            // When the here is a typo
                            val result = dollar * naira
                            
                            // then
                            assert(result == 50000)
                        }
                    }
                    """
                ).indented()

            )
            .issues(TestFunctionCommentDetector.ISSUE)
            .run()
            .expect("No warnings.")
    }

    @Test
    fun testCommentDetectionInTestSkipClass() {
        lint()
            .files(
                *commonStubs,
                TestFiles.kotlin(
                    """
                    package com.damilola.ft_currency
                    
                    import org.junit.Test

                    class CurrencyConverter {
                        
                        @Test
                        fun `test conversion from dollar to naira`() {
                            // TODO Given
                            val dollar = 100
                            val naira = 500
                            
                            // When the here is a typo
                            val result = dollar * naira
                            
                            // then
                            assert(result == 50000)
                        }
                    }
                    """
                ).indented()

            )
            .issues(TestFunctionCommentDetector.ISSUE)
            .run()
            .expect("No warnings.")
    }


}
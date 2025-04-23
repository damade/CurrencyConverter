package com.damilola.lint_playground

import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import com.damilola.lint_playground.TestMethodNameDetector.Companion.FORMAT
import org.junit.Test

class TestMethodNameDetectorTest : BaseKotlinLintTest() {

    override fun getDetector(): Detector {
        return TestMethodNameDetector()
    }

    override fun getIssues(): List<Issue> {
        return listOf(FORMAT)
    }

    @Test
    fun testDetectFormat() {
        lint().issues(FORMAT)
            .files(
                *commonStubs,
                kotlin(
                    "src/test/com/example/Test.kt",
                    """
                    import org.junit.Test
                    class Test {
                        @Test
                        fun `given we reload, when, then`() {
                            // given
                            val a = 3 + 5
                            
                            // then
                        }
                        @Test
                        fun `given we refresh, when, then`(): Unit {
                            // given
                            
                            // then
                        }
                    }
                """,
                ).indented(),
            )
            .run()
            .expect(
                """
                src/test/com/example/Test.kt:4: Error: Test method does not follow the when, then format [TestMethodNameFormat]
                    fun `given we reload, when, then`(){
                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~
                src/test/com/example/Test.kt:10: Error: Test method does not follow the when, then format [TestMethodNameFormat]
                    fun `given we refresh, when, then`(): Unit {
                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

                2 errors, 0 warnings
                """.trimIndent(),
            )
    }

//    @Test
//    fun testDetectFormats() {
//        lint().issues(FORMAT)
//            .files(
//                *commonStubs,
//                kotlin(
//                    "src/test/com/example/ExampleTest.kt",
//                    """
//                    import org.junit.Test
//                    class ExampleTest {
//                        @Test
//                        fun `when we click on reload, then loading state is emitted`() = Unit
//
//                        @Test
//                        fun `when we click on 1st reload, then loading state is emitted`() = Unit
//                        @Test
//                        fun foo() = Unit
//                        @Test
//                        fun foo_bar_baz_qux() = Unit
//                        @Test
//                        fun `foo bar baz`() = Unit
//                        @Test
//                        fun `when, then`() = Unit
//                        @Test
//                        fun `given we clicked on loading, when, then`() = Unit
//                        @Test
//                        fun `given we reload, when, then`() {
//                            // given
//
//                            // then
//                        }
//                        @Test
//                        fun `given we refresh, when, then`(): Unit {
//                            // given
//
//                            // then
//                        }
//                    }
//                """,
//                ).indented(),
//            )
//            .run()
//            .expect(
//                """
//                src/test/com/example/Test.kt:7: Error: Test method does not follow the when, then format [TestMethodNameFormat]
//                    fun `when we click on 1st reload, then loading state is emitted`() = Unit
//                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//                src/test/com/example/Test.kt:9: Error: Test method does not follow the when, then format [TestMethodNameFormat]
//                    fun foo() = Unit
//                        ~~~
//                src/test/com/example/Test.kt:11: Error: Test method does not follow the when, then format [TestMethodNameFormat]
//                    fun foo_bar_baz_qux() = Unit
//                        ~~~~~~~~~~~~~~~
//                src/test/com/example/Test.kt:13: Error: Test method does not follow the when, then format [TestMethodNameFormat]
//                    fun `foo bar baz`() = Unit
//                        ~~~~~~~~~~~~~
//                src/test/com/example/Test.kt:15: Error: Test method does not follow the when, then format [TestMethodNameFormat]
//                    fun `when, then`() = Unit
//                        ~~~~~~~~~~~~
//                src/test/com/example/Test.kt:17: Error: Test method does not follow the when, then format [TestMethodNameFormat]
//                    fun `given we clicked on loading, when, then`() = Unit
//                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//                src/test/com/example/Test.kt:19: Error: Test method does not follow the when, then format [TestMethodNameFormat]
//                    fun `given we reload, when, then`(){
//                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~
//                src/test/com/example/Test.kt:22: Error: Test method does not follow the when, then format [TestMethodNameFormat]
//                    fun `given we refresh, when, then`(): Unit {
//                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//                8 errors, 0 warnings
//                """.trimIndent(),
//            )
//    }
}
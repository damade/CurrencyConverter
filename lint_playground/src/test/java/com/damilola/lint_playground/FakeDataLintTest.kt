package com.damilola.lint_playground

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import org.junit.Test

class FakeDataLintTest: BaseComposeLintTest() {

    override fun getDetector(): Detector {
        return FakeDataDetector()
    }

    override fun getIssues(): List<Issue> {
        return listOf(FakeDataDetector.ISSUE)
    }

    @Test
    fun `when fake uimodel is used in regular composable, then error is returned`() {
        lint()
            .files(
                *commonStubs,
                TestFiles.kotlin(
                    """
                    package com.damilola.ft_currency.frontend.components
                    
                    import androidx.compose.foundation.clickable
                    import androidx.compose.foundation.layout.Column
                    import androidx.compose.foundation.layout.padding
                    import androidx.compose.runtime.Composable
                    import androidx.compose.ui.Modifier
                    import androidx.compose.ui.unit.dp

                    object JohnDoeFake { fun data(): String = "test" }

                    @Composable
                    fun TestModifier() {
                        Column(
                            modifier = Modifier
                                .padding(all = 16.dp)
                                .clickable { }
                        ) {
                            val johnDoe = JohnDoeFake.data()
                        }
                    }
                    """
                ).indented()

            )
            .issues(FakeDataDetector.ISSUE)
            .run()
            .expect(
                """
src/com/damilola/ft_currency/frontend/components/JohnDoeFake.kt:19: Error: Fake data should only be used in functions/classes annotated with @Preview or @Test or class with suffix Test [FakeDataObject]
        val johnDoe = JohnDoeFake.data()
                      ~~~~~~~~~~~~~~~~~~
1 errors, 0 warnings               
            """.trimIndent()
            )
    }

    @Test
    fun `when fake model is used in regular composable, then error is returned`() {
        lint()
            .files(
                *commonStubs,
                TestFiles.kotlin(
                    """
                    package com.damilola.ft_currency.frontend.components
                    
                    import androidx.compose.foundation.clickable
                    import androidx.compose.foundation.layout.Column
                    import androidx.compose.foundation.layout.padding
                    import androidx.compose.runtime.Composable
                    import androidx.compose.ui.Modifier
                    import androidx.compose.ui.unit.dp

                    @Composable
                    fun TestModifier() {
                        Column(
                            modifier = Modifier
                                .padding(all = 16.dp)
                                .clickable { }
                        ) {
                            val name = NameUi.fake()
                        }
                    }
                    """
                ).indented()

            )
            .issues(FakeDataDetector.ISSUE)
            .run()
            .expect(
                """
src/com/damilola/ft_currency/frontend/components/test.kt:17: Error: Fake data should only be used in functions/classes annotated with @Preview or @Test or class with suffix Test [FakeDataObject]
        val name = NameUi.fake()
                   ~~~~~~~~~~~~~
1 errors, 0 warnings              
            """.trimIndent()
            )
    }

    @Test
    fun `when fake ui model is used in a raw preview composable, then no error is returned`() {
        lint()
            .files(
                *commonStubs,
                TestFiles.kotlin(
                    """
                    package com.damilola.ft_currency.frontend.components

                    import androidx.compose.runtime.Composable
                    import androidx.compose.ui.tooling.preview.Preview

                    object JohnDoeFake { fun data(): String = "test" }

                    @Preview
                    @Composable
                    fun TestModifier() {
                        val johnDoe = JohnDoeFake.data()
                    }
                    """
                ).indented()

            )
            .issues(FakeDataDetector.ISSUE)
            .run()
            .expectClean()
    }

    @Test
    fun `when fake ui model is used in a preview composable, then no error is returned`() {
        lint()
            .files(
                *commonStubs,
                TestFiles.kotlin(
                    """
                    package com.damilola.ft_currency.frontend.components
                    
                    import androidx.compose.foundation.clickable
                    import androidx.compose.foundation.layout.Column
                    import androidx.compose.foundation.layout.padding
                    import androidx.compose.runtime.Composable
                    import androidx.compose.ui.Modifier
                    import androidx.compose.ui.tooling.preview.Preview
                    import androidx.compose.ui.unit.dp

                    object JohnDoeFake { fun data(): String = "test" }

                    @Preview
                    @Composable
                    fun TestModifier() {
                        Column(
                            modifier = Modifier
                                .padding(all = 16.dp)
                                .clickable { }
                        ) {
                            val johnDoe = JohnDoeFake.data()
                        }
                    }
                    """
                ).indented()

            )
            .issues(FakeDataDetector.ISSUE)
            .run()
            .expectClean()
    }

    @Test
    fun `when fake model is used in regular composable, then no error is returned`() {
        lint()
            .files(
                *commonStubs,
                TestFiles.kotlin(
                    """
                    package com.damilola.ft_currency.frontend.components
                    
                    import androidx.compose.foundation.clickable
                    import androidx.compose.foundation.layout.Column
                    import androidx.compose.foundation.layout.padding
                    import androidx.compose.runtime.Composable
                    import androidx.compose.ui.Modifier
                    import androidx.compose.ui.tooling.preview.Preview
                    import androidx.compose.ui.unit.dp

                    @Preview
                    @Composable
                    fun TestModifier() {
                        Column(
                            modifier = Modifier
                                .padding(all = 16.dp)
                                .clickable { }
                        ) {
                            val name = NameUi.fake()
                        }
                    }
                    """
                ).indented()

            )
            .issues(FakeDataDetector.ISSUE)
            .run()
            .expectClean()
    }

}
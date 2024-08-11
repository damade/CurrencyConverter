package com.damilola.lint_playground

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import org.junit.Test

class ModifierLintTest: BaseComposeLintTest() {

    override fun getDetector(): Detector {
        return ModifierClickablePaddingDetector()
    }

    override fun getIssues(): List<Issue> {
        return listOf(ModifierClickablePaddingDetector.ISSUE)
    }

    @Test
    fun testModifierParameterOrder() {
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
                        }
                    }
                    """
                ).indented()

            )
            .issues(ModifierClickablePaddingDetector.ISSUE)
            .run()
            .expect("Clickable Modifier after Padding Modifier")
    }

}
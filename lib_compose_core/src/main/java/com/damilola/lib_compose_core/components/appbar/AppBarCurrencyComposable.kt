package com.damilola.lib_compose_core.components.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.damilola.core_android.R
import com.damilola.lib_compose_core.resources.AppColour
import com.damilola.lib_compose_core.resources.PreviewContainer
import com.damilola.lib_compose_core.resources.dp100
import com.damilola.lib_compose_core.resources.dp16
import com.damilola.lib_compose_core.resources.dp32
import com.damilola.lib_compose_core.resources.sp24

@Composable
fun AppBarCurrencyComposable(
    navigateUp: () -> Unit,
) {
    BoxWithConstraints {
        val constraints = if (minWidth < 600.dp) {
            decoupledConstraints(margin = dp16) // Portrait constraints
        } else {
            decoupledConstraints(margin = dp32) // Landscape constraints
        }

        ConstraintLayout(
            constraintSet = constraints,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_history_24),
                contentDescription = stringResource(id = R.string.history),
                modifier = Modifier
                    .layoutId("goToHistoryImage")
                    .clickable(
                        onClick = navigateUp
                    )

            )
            Text(
                text = stringResource(id = R.string.hello),
                color = AppColour,
                fontSize = sp24,
                modifier = Modifier.layoutId("signUpText")
            )
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val goToHistoryImage = createRefFor("goToHistoryImage")
        val signUpText = createRefFor("signUpText")
        val signUpStartGuideline = createGuidelineFromEnd(dp100)

        constrain(goToHistoryImage) {
            top.linkTo(anchor = parent.top, margin = margin)
            start.linkTo(anchor = parent.start, margin = margin)
            bottom.linkTo(anchor = parent.bottom, margin = margin)


        }
        constrain(signUpText) {
            top.linkTo(anchor = parent.top, margin = margin)
            end.linkTo(anchor = parent.end, margin = margin)
            bottom.linkTo(anchor = parent.bottom, margin = margin)
            start.linkTo(anchor = signUpStartGuideline)

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AppBarDefaultPreview() {
    PreviewContainer {
        AppBarCurrencyComposable(navigateUp = { })
    }
}
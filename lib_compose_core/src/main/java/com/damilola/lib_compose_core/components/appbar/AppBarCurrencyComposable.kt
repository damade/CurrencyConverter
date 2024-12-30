package com.damilola.lib_compose_core.components.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.layoutId
import com.damilola.core_android.R
import com.damilola.lib_compose_core.components.modifiers.centerHorizontally
import com.damilola.lib_compose_core.components.modifiers.centerVertically
import com.damilola.lib_compose_core.components.ui_providers.FillSpaceSpacer
import com.damilola.lib_compose_core.resources.AppColour
import com.damilola.lib_compose_core.resources.PreviewContainer
import com.damilola.lib_compose_core.resources.sp24

@Composable
fun AppBarCurrencyComposable(
    navigateUp: () -> Unit,
) {
    Row(modifier = Modifier.padding(all = 16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_history_24),
            contentDescription = stringResource(id = R.string.history),
            modifier = Modifier
                .layoutId("goToHistoryImage")
                .clickable(
                    onClick = navigateUp
                )

        )
        FillSpaceSpacer()
        Text(
            text = stringResource(id = R.string.hello),
            color = AppColour,
            fontSize = sp24,
            modifier = Modifier.layoutId("signUpText")
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun AppBarDefaultPreview() {
    PreviewContainer {
        Column {
            AppBarCurrencyComposable(navigateUp = { })
            Text(
                text = stringResource(id = R.string.hello),
                color = AppColour,
                fontSize = sp24,
                modifier = Modifier
                    .centerHorizontally()
                    .layoutId("signUpText")
            )

            Row {
                Text(
                    text = "JSJSJSJSJS\nJSJSJ",
                    color = AppColour,
                    fontSize = sp24,
                    modifier = Modifier
                        .layoutId("signUpText1")
                )
                Text(
                    text = stringResource(id = R.string.hello),
                    color = AppColour,
                    fontSize = sp24,
                    modifier = Modifier
                        .layoutId("signUpText2")
                        .centerVertically()
                )
                Text(
                    text = "stringResource(id = R.string.hello)",
                    color = AppColour,
                    fontSize = sp24,
                    modifier = Modifier
                        .layoutId("signUpText3")
                )
            }
        }
    }
}
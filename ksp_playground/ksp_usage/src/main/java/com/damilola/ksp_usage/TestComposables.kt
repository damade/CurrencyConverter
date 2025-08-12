package com.damilola.ksp_usage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.layoutId
import com.damilola.lib_compose_core.components.modifiers.centerHorizontally
import com.damilola.lib_compose_core.components.modifiers.centerVertically
import com.damilola.lib_compose_core.resources.AppColour
import com.damilola.lib_compose_core.resources.PreviewContainer
import com.damilola.lib_compose_core.resources.sp24

@Preview(showBackground = true)
@Composable
private fun TestComposablePreview() {
    PreviewContainer {
        Column {
            val dataFake = DataUiFakeIt.fake(fullName = "Desmond John Doe")
            val name = NameUiFakes.data(fullName = "Hallowed John Doe")
            Text(
                text = "Hello",
                color = AppColour,
                fontSize = sp24,
                modifier = Modifier
                    .centerHorizontally()
                    .layoutId("signUpText")
            )

            Text(
                text = dataFake.fullName,
                color = AppColour,
                fontSize = sp24,
                modifier = Modifier
                    .centerHorizontally()
                    .layoutId("signUpText")
            )

            Text(
                text = name.fullName,
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
                    text = "Hello",
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
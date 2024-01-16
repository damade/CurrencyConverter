package com.damilola.lib_compose_core.components.appbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.damilola.core_android.R
import com.damilola.lib_compose_core.resources.PreviewContainer
import com.damilola.lib_compose_core.ui.theme.CurrencyConverterTheme

@Composable
fun CurrencyConverterAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onClickNavUp: (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier
            .height(56.dp)
            .shadow(elevation = 4.dp, clip = false),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (onClickNavUp != null) {
                IconButton(
                    onClick = onClickNavUp,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = Modifier.width(width = 20.dp))
            Text(
                text = title,
                style = CurrencyConverterTheme.typography.display3.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.weight(weight = 1F)
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun CurrencyConverterAppBarPreview() {
    PreviewContainer {
        Scaffold(
            topBar = {
                CurrencyConverterAppBar(title = "The App Bar") {
                }
            },
        ) {
            Text(
                text = "Some awesome things are happening",
                modifier = Modifier.padding(it),
            )
        }
    }
}

package com.damilola.lib_compose_core.components.spinner

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.damilola.lib_compose_core.resources.PreviewContainer


@Composable
fun SpinnerSample(
    list: List<MyData>,
    preselected: MyData,
    onSelectionChanged: (myData: MyData) -> Unit,
    modifier: Modifier = Modifier
) {

    var selected by remember { mutableStateOf(preselected) }
    var expanded by remember { mutableStateOf(false) } // initial value

    OutlinedCard(
        modifier = modifier.clickable {
            expanded = !expanded
        }
    ) {


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {

            Text(
                text = selected.name,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Icon(Icons.Outlined.ArrowDropDown, null, modifier = Modifier.padding(8.dp))

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()   // delete this modifier and use .wrapContentWidth() if you would like to wrap the dropdown menu around the content
            ) {
                list.forEach { listEntry ->

                    DropdownMenuItem(
                        onClick = {
                            selected = listEntry
                            expanded = false
                            onSelectionChanged(selected)
                        },
                        text = {
                            Text(
                                text = listEntry.name,
                                modifier = Modifier
                                    //.wrapContentWidth()  //optional instad of fillMaxWidth
                                    .fillMaxWidth()
                                    .align(Alignment.Start)
                            )
                        },
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SpinnerSample_Preview() {
    PreviewContainer {
        val myData = listOf(MyData(0, "Apples"), MyData(1, "Bananas"), MyData(2, "Kiwis"))

        SpinnerSample(
            myData,
            preselected = myData.first(),
            onSelectionChanged = { },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

data class MyData(
    val id: Int,
    val name: String
)
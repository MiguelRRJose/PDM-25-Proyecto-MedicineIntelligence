package com.pdmproyecto.mymedicine.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenu(
    modifier: Modifier = Modifier.fillMaxWidth(),
    items: List<String>,
    selectedItem: String,
    onItemSelection: (String) -> Unit
){

    val expanded = remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value },
    ) {

        OutlinedTextField(
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)}
        )


        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {expanded.value = false}
        ) {
            items.forEach {
                item ->
                DropdownMenuItem(
                    text = { Text(text = item.toString()) },
                    onClick = {
                        onItemSelection(item)
                        expanded.value = false
                    }
                )
            }
        }
    }
}
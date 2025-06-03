package org.interapidisimo.interrapidisimoapp.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> CustomRecyclerView(
    itemList: List<T>,
    itemContent: @Composable (T) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        items(items = itemList) { item ->
            itemContent(item)
        }
    }
}
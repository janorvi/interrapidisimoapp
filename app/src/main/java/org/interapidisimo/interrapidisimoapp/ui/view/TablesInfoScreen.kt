package org.interapidisimo.interrapidisimoapp.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.interapidisimo.interrapidisimoapp.data.model.TableInfoModel
import org.interapidisimo.interrapidisimoapp.ui.viewmodel.MainViewModel

@Composable
fun TablesInfoScreen(
    mainViewModel: MainViewModel
){
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        val tablesInfo: List<TableInfoModel> by mainViewModel.tableInfoList.observeAsState(emptyList())
        Text(text = "Tablas informacion")
        CustomRecyclerView(
            itemList = tablesInfo
        ){
            TableInfoItem(it)
        }
        mainViewModel.getTablesInfoFromServer()
    }
}

@Composable
fun TableInfoItem(tableInfo: TableInfoModel) {
    Surface(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text("Table name: ${tableInfo.tableName}", style = MaterialTheme.typography.h6)
    }
}
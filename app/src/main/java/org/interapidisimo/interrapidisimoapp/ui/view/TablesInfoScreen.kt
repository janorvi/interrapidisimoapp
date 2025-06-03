package org.interapidisimo.interrapidisimoapp.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.interapidisimo.interrapidisimoapp.data.model.TableInfoModel
import org.interapidisimo.interrapidisimoapp.ui.viewmodel.TablesInfoViewModel

@Composable
fun TablesInfoScreen(
    snackbarHostState: SnackbarHostState,
    tablesInfoViewModel: TablesInfoViewModel = hiltViewModel()
){
    val event by tablesInfoViewModel.message.observeAsState()

    LaunchedEffect(event) {
        tablesInfoViewModel.getTablesInfoFromServer()
    }

    LaunchedEffect(event) {
        event?.getContentIfNotHandled()?.let { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        val tablesInfo: List<TableInfoModel> by tablesInfoViewModel.tableInfoList.observeAsState(emptyList())
        Text(text = "Tablas informacion")
        CustomRecyclerView(
            itemList = tablesInfo
        ){
            TableInfoItem(it)
        }
    }
}

@Composable
fun TableInfoItem(tableInfo: TableInfoModel) {
    Surface(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text("Table name: ${tableInfo.tableName}", style = MaterialTheme.typography.h6)
    }
}
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
import org.interapidisimo.interrapidisimoapp.data.model.LocalityModel
import org.interapidisimo.interrapidisimoapp.ui.viewmodel.LocalitiesViewModel

@Composable
fun LocalitiesScreen(
    snackbarHostState: SnackbarHostState,
    localitiesViewModel: LocalitiesViewModel = hiltViewModel()
){

    val event by localitiesViewModel.message.observeAsState()

    LaunchedEffect(Unit) {
        localitiesViewModel.getLocalitiesFromServer()
    }

    LaunchedEffect(event) {
        event?.getContentIfNotHandled()?.let { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        val localities: List<LocalityModel> by localitiesViewModel.localityList.observeAsState(emptyList())
        Text(text = "Localidades")
        CustomRecyclerView(
            itemList = localities
        ){
            LocalityItem(it)
        }
    }
}

@Composable
fun LocalityItem(locality: LocalityModel) {
    Surface(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text("Localidad: ${locality.name}", style = MaterialTheme.typography.h6)
    }
}
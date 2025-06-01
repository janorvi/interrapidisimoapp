package org.interapidisimo.interrapidisimoapp.ui.view

import androidx.compose.runtime.Composable
import org.interapidisimo.interrapidisimoapp.ui.viewmodel.MainViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import org.interapidisimo.interrapidisimoapp.data.model.LocalityModel
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun LocalitiesScreen(
    mainViewModel: MainViewModel
){
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        val localities: List<LocalityModel> by mainViewModel.localityList.observeAsState(emptyList())
        Text(text = "Localidades")
        CustomRecyclerView(
            itemList = localities
        ){
            LocalityItem(it)
        }
        mainViewModel.getLocalitiesFromServer()
    }
}

@Composable
fun LocalityItem(locality: LocalityModel) {
    Surface(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text("Localidad: ${locality.name}", style = MaterialTheme.typography.h6)
    }
}
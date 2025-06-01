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

/*@Composable
fun itemRecyclerView(authorization: LocalityModel, mainViewModel: MainViewModel){
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
    ){
        Column(
            modifier = Modifier.padding(24.dp).fillMaxWidth(),
        ){
            Row(

            ){
                Column(
                    modifier = Modifier.weight(1f)
                ){
                    Text( text = "Authorization")
                    Text(
                        text = "ID: " + authorization.id,
                        style = MaterialTheme.typography.h5.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "CARD No: " + authorization.card,
                        style = MaterialTheme.typography.h5.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                if(authorization.authorizationStatus == "Aprobada") {
                    Button(
                        onClick = {
                            mainViewModel.cancelAuthorization(
                                AnnulmentRequest(
                                    authorization.receiptId,
                                    authorization.rrn
                                )
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = orange,
                            contentColor = white
                        ),
                        modifier = Modifier.width(20.dp).weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Anular",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}*/
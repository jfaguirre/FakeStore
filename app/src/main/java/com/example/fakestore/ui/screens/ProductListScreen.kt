package com.example.fakestore.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fakestore.viewmodel.ProductUiState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp

@Composable
fun ProductListScreen(
    uiState: ProductUiState,
    onProductClick: (Int) -> Unit
) {

    when (uiState) {

        is ProductUiState.Loading -> {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CircularProgressIndicator()

                Text(
                    text = "Cargando productos..."
                )
            }
        }

        is ProductUiState.Success -> {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                items(uiState.products) { product ->

                    ProductCard(
                        product = product,
                        onClick = {
                            onProductClick(product.id)
                        }
                    )
                }
            }
        }

        is ProductUiState.Error -> {

            Text(
                text = "Error: ${uiState.message}"
            )
        }
    }
}
package com.example.fakestore.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.fakestore.viewmodel.ProductDetailUiState

@Composable
fun ProductDetailScreen(
    uiState: ProductDetailUiState,
    onBackClick: () -> Unit
) {

    when (uiState) {

        is ProductDetailUiState.Loading -> {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CircularProgressIndicator()

                Text(
                    text = "Cargando producto..."
                )
            }
        }

        is ProductDetailUiState.Success -> {

            val product = uiState.product

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    model = product.image,
                    contentDescription = product.title,
                    modifier = Modifier.size(250.dp)
                )

                Text(
                    text = product.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                Text(
                    text = "$${product.price}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp)
                )

                Text(
                    text = "⭐ ${product.rating.rate} (${product.rating.count} reseñas)",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = product.category,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = product.description,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                Button(
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "← Volver"
                    )
                }
            }
        }

        is ProductDetailUiState.Error -> {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Error al cargar el producto"
                )

                Text(
                    text = uiState.message,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
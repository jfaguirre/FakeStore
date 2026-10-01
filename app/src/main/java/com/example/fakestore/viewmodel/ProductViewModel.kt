package com.example.fakestore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.model.Product
import com.example.fakestore.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ProductUiState {

    data object Loading : ProductUiState

    data class Success(
        val products: List<Product>
    ) : ProductUiState

    data class Error(
        val message: String
    ) : ProductUiState
}


class ProductViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(
        ProductUiState.Loading
    )

    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {

        viewModelScope.launch {

            _uiState.value = ProductUiState.Loading

            try {

                val products = RetrofitInstance.api.getProducts()

                _uiState.value = ProductUiState.Success(products)

            } catch (e: Exception) {

                _uiState.value = ProductUiState.Error(
                    e.message ?: "Ocurrió un error desconocido"
                )
            }
        }
    }
}
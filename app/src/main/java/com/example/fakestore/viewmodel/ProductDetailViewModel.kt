package com.example.fakestore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.model.Product
import com.example.fakestore.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ProductDetailUiState {

    data object Loading : ProductDetailUiState

    data class Success(
        val product: Product
    ) : ProductDetailUiState

    data class Error(
        val message: String
    ) : ProductDetailUiState
}


class ProductDetailViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow<ProductDetailUiState>(
            ProductDetailUiState.Loading
        )

    val uiState: StateFlow<ProductDetailUiState> =
        _uiState.asStateFlow()

    fun getProduct(id: Int) {

        viewModelScope.launch {

            _uiState.value = ProductDetailUiState.Loading

            try {

                val product = RetrofitInstance.api.getProduct(id)

                _uiState.value =
                    ProductDetailUiState.Success(product)

            } catch (e: Exception) {

                _uiState.value =
                    ProductDetailUiState.Error(
                        e.message ?: "Ocurrió un error desconocido"
                    )
            }
        }
    }
}
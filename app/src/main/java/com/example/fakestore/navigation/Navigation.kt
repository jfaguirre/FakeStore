package com.example.fakestore.navigation

import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fakestore.viewmodel.ProductViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fakestore.ui.screens.ProductDetailScreen
import com.example.fakestore.ui.screens.ProductListScreen
import com.example.fakestore.viewmodel.ProductDetailViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "products"
    ) {

        composable("products") {

            val viewModel: ProductViewModel = viewModel()

            ProductListScreen(
                uiState = viewModel.uiState.collectAsState().value,
                onProductClick = { productId ->

                    navController.navigate(
                        "product/$productId"
                    )
                }
            )
        }

        composable(
            route = "product/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val productId =
                backStackEntry.arguments?.getInt("productId")

            val viewModel: ProductDetailViewModel = viewModel()

            LaunchedEffect(productId) {

                if (productId != null) {
                    viewModel.getProduct(productId)
                }
            }

            ProductDetailScreen(
                uiState = viewModel.uiState.collectAsState().value,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
package com.example.fakestore

import com.example.fakestore.navigation.Navigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fakestore.ui.screens.ProductListScreen
import com.example.fakestore.ui.theme.FakeStoreTheme
import com.example.fakestore.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            FakeStoreTheme {

                Navigation()
            }
        }
    }
}
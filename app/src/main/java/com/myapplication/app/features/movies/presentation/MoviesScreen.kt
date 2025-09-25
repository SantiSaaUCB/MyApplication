package com.myapplication.app.features.movies.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesScreen(
    moviesViewModel: MoviesViewModel = koinViewModel()
) {
    val state by moviesViewModel.state.collectAsState()

    LaunchedEffect(Unit) { moviesViewModel.fetchPopularMovies() }

    when (val s = state) {
        is MoviesViewModel.UiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        is MoviesViewModel.UiState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(s.message)
                Spacer(Modifier.height(12.dp))
                Button(onClick = { moviesViewModel.retry() }) { Text("Reintentar") }
            }
        }
        is MoviesViewModel.UiState.Success -> {
            val list = s.movies
            if (list.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { Text("Sin resultados") }
            } else {
                PopularMoviesScreen(movies = list)
            }
        }
        else -> {}
    }
}

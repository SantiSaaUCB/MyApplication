package com.myapplication.app.features.movies.domain.repository

import com.myapplication.app.features.movies.domain.model.MovieModel

interface IMovieRepository {
    suspend fun getPopularMovies(): Result<List<MovieModel>>
}

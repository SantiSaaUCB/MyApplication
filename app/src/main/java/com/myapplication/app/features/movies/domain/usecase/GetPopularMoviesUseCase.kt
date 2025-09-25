package com.myapplication.app.features.movies.domain.usecase

import com.myapplication.app.features.movies.domain.model.MovieModel
import com.myapplication.app.features.movies.domain.repository.IMovieRepository

class GetPopularMoviesUseCase(
    private val movieRepository: IMovieRepository
) {
    suspend operator fun invoke(): Result<List<MovieModel>> {
        return movieRepository.getPopularMovies()
    }
}
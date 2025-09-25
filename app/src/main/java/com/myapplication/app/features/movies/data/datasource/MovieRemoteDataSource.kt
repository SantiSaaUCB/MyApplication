package com.myapplication.app.features.movies.data.datasource

import com.myapplication.app.features.movies.data.api.MovieService
import com.myapplication.app.features.movies.data.api.dto.MovieListDto
import com.myapplication.app.features.movies.domain.model.MovieModel

class MovieRemoteDataSource(
    private val api: MovieService
) {
    suspend fun getPopular(): Result<MovieListDto> {
        val res = api.popular()
        return if (res.isSuccessful && res.body()!=null)
            Result.success(res.body()!!)
        else
            Result.failure(Exception("TMDB error ${res.code()}"))
    }
}

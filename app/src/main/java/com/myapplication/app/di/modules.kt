package com.myapplication.app.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.myapplication.app.features.dollar.data.database.AppRoomDatabase
import com.myapplication.app.features.dollar.data.datasource.DollarLocalDataSource
import com.myapplication.app.features.dollar.data.datasource.DollarRealTimeDataSource
import com.myapplication.app.features.dollar.data.repository.DollarRepository
import com.myapplication.app.features.dollar.domain.repository.IDollarRepository
import com.myapplication.app.features.dollar.domain.usecase.GetDollarUseCase
import com.myapplication.app.features.dollar.presentation.DollarViewModel
import com.myapplication.app.features.github.data.api.GithubService
import com.myapplication.app.features.github.data.datasource.GithubRemoteDataSource
import com.myapplication.app.features.github.data.repository.GithubRepository
import com.myapplication.app.features.github.domain.repository.IGithubRepository
import com.myapplication.app.features.github.domain.usecase.GithubFindByNickNameUseCase
import com.myapplication.app.features.github.presentation.GithubViewModel
import com.myapplication.app.features.movies.data.api.MovieService
import com.myapplication.app.features.movies.data.database.AppRoomDataBase
import com.myapplication.app.features.movies.data.datasource.MovieLocalDataSource
import com.myapplication.app.features.movies.data.datasource.MovieRemoteDataSource
import com.myapplication.app.features.movies.data.repository.MovieRepository
import com.myapplication.app.features.movies.domain.repository.IMovieRepository
import com.myapplication.app.features.movies.domain.usecase.GetPopularMoviesUseCase
import com.myapplication.app.features.movies.presentation.MoviesViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkConstants {
    const val RETROFIT_GITHUB = "RetrofitGithub"
    const val GITHUB_BASE_URL = "https://api.github.com/"
    const val RETROFIT_MOVIE = "RetrofitMovie"
    const val MOVIE_BASE_URL = "https://api.themoviedb.org/3/"
}

val appModule = module {
    single { Gson() }
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single(named(NetworkConstants.RETROFIT_MOVIE)) {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.MOVIE_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single(named(NetworkConstants.RETROFIT_GITHUB)) {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.GITHUB_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<GithubService> { get<Retrofit>(named(NetworkConstants.RETROFIT_GITHUB)).create(GithubService::class.java) }
    single { GithubRemoteDataSource(get()) }
    single<IGithubRepository> { GithubRepository(get()) }
    factory { GithubFindByNickNameUseCase(get()) }
    viewModel { GithubViewModel(get()) }
    single<MovieService> { get<Retrofit>(named(NetworkConstants.RETROFIT_MOVIE)).create(MovieService::class.java) }
    single { MovieRemoteDataSource(get()) }
    single {
        Room.databaseBuilder(get<Application>(), AppRoomDataBase::class.java, "app.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppRoomDataBase>().movieDao() }
    single { MovieLocalDataSource(get(), get()) }
    single<IMovieRepository> { MovieRepository(get(), get()) }
    factory { GetPopularMoviesUseCase(get()) }
    viewModel { MoviesViewModel(get()) }

    single { AppRoomDatabase.getDatabase(get()) }
    single { get<AppRoomDatabase>().dollarDao() }
    single { DollarRealTimeDataSource() }
    single { DollarLocalDataSource(get()) }
    single<IDollarRepository> { DollarRepository(get(), get()) }
    factory { GetDollarUseCase(get()) }
    viewModel{ DollarViewModel(get()) }
}

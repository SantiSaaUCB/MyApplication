package com.myapplication.app.features.github.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.app.features.github.domain.model.GithubUserModel
import com.myapplication.app.features.github.domain.usecase.GithubFindByNickNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GithubViewModel(
    val usecase: GithubFindByNickNameUseCase,
): ViewModel() {
    sealed class GithubStateUI {
        object Init: GithubStateUI()
        object Loading: GithubStateUI()
        class Error(val message: String): GithubStateUI()
        class Success(val github: GithubUserModel): GithubStateUI()
    }
    private val _state = MutableStateFlow<GithubStateUI>(GithubStateUI.Init)
    val state : StateFlow<GithubStateUI> = _state.asStateFlow()
    fun fetchAlias(nickname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = GithubStateUI.Loading
            val result = usecase.invoke(nickname)
            result.fold(
                onSuccess = {
                    user ->
                        _state.value = GithubStateUI.Success(user)
                },
                onFailure = {
                    error ->
                        _state.value = GithubStateUI.Error(message = error.message ?: "Unknown error")
                }
            )
        }
    }
}
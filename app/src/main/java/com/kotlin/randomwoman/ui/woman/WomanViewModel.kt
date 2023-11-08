package com.kotlin.randomwoman.ui.woman

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.randomwoman.data.repository.WomanRepository
import com.kotlin.randomwoman.util.Resource
import com.kotlin.randomwoman.util.WomanListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WomanViewModel @Inject constructor(
    private val womanRepository: WomanRepository
):ViewModel() {

    private val _uiState = MutableStateFlow(WomanListState())
    val uiState: StateFlow<WomanListState> = _uiState.asStateFlow()

    init {
        womanRepository.getWoman().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    _uiState.update { it.copy(women = result.data ?: emptyList()) }
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

}
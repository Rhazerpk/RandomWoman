package com.kotlin.randomwoman.util

import com.kotlin.randomwoman.data.remote.dto.WomanDto

data class WomanListState(
    val isLoading: Boolean = false,
    val women: List<WomanDto> = emptyList(),
    val error: String = "",
)

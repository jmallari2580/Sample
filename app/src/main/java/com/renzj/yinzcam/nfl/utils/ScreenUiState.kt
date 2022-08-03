package com.renzj.yinzcam.nfl.utils

interface ScreenUiState {
    val loadingState: LoadingState

    enum class LoadingState{
        Loading,
        Completed,
        Error,
        Empty;
    }
}
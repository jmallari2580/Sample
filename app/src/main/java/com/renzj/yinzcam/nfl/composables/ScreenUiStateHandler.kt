package com.renzj.yinzcam.nfl.composables

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import com.renzj.yinzcam.nfl.utils.ScreenUiState

@Composable
fun ScreenUiStateHandler(
    uiState: ScreenUiState,
    loading: @Composable (() -> Unit)? = null,
    empty: @Composable (() -> Unit)? = null,
    error: @Composable (() -> Unit)? = null,
    completed: @Composable () -> Unit
) {
    Crossfade(targetState = uiState.loadingState) {
        when (it) {
            ScreenUiState.LoadingState.Loading -> loading?.invoke()
            ScreenUiState.LoadingState.Empty -> empty?.invoke()
            ScreenUiState.LoadingState.Error -> error?.invoke()
            ScreenUiState.LoadingState.Completed -> completed.invoke()
        }
    }
}
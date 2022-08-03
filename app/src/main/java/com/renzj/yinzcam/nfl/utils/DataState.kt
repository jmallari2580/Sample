package com.renzj.yinzcam.nfl.utils

sealed class DataState<T> {
    class Success<T>(val data:T): DataState<T>()
    class Empty<T>(val tag: Any? = null): DataState<T>()
    class Failed<T>(val error: Throwable): DataState<T>()
}
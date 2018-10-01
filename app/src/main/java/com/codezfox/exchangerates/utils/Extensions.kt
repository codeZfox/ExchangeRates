package com.codezfox.exchangerates.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main
import retrofit2.Call
import retrofit2.HttpException


typealias Response<K> = Deferred<Result<K>>

fun launchUI(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        onCompletion: CompletionHandler? = null,
        block: suspend CoroutineScope.() -> Unit
): Job {
    return GlobalScope.launch(Dispatchers.Main, start, onCompletion, block)
}

inline fun <T> CoroutineScope.asyncR(crossinline callback: () -> T): Response<T> {
    return async(Dispatchers.Default) {
        Result.runCatching {
            callback.invoke()
        }
    }
}

suspend inline fun <V> Response<V>.awaitFold(onSuccess: (value: V) -> Unit, onFailure: (exception: Throwable) -> Unit) {
    return this.await().fold(onSuccess, onFailure)
}

fun <T> Call<T>.bodyOrError(): T {
    return this.execute().bodyOrError()
}

fun <T> retrofit2.Response<T>.bodyOrError(): T {
    if (this.isSuccessful) {
        return this.body()!!
    }

    throw HttpException(this)
}

fun <T> Call<T>.isSuccessfulOrError(): Boolean {
    return this.execute().isSuccessfulOrError()
}

fun <T> retrofit2.Response<T>.isSuccessfulOrError(): Boolean {
    if (this.isSuccessful) {
        return true
    }

    throw HttpException(this)
}



inline fun <reified T : ViewModel> FragmentActivity.obtainViewModel(factory: ViewModelProvider.Factory) =
        ViewModelProviders.of(this, factory).get(T::class.java)

inline fun <reified T : ViewModel> Fragment.obtainViewModel(factory: ViewModelProvider.Factory) =
        ViewModelProviders.of(this, factory).get(T::class.java)

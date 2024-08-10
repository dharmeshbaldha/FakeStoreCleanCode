package com.example.fakestorecleancode.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

fun isNetworkAvailable(@ApplicationContext context: Context): Boolean {
    (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
        return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        ) ?: false
    }
}

suspend fun <T> safeAPICall(dispatcher: CoroutineDispatcher, apiCall: T): NetworkResult<T> {
    return withContext(dispatcher) {
        try {
            NetworkResult.Success(apiCall)
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    NetworkResult.Error(message = "IO Exception")
                }

                is HttpException -> {
                    val error = JSONObject(throwable.response()?.errorBody()?.charStream()?.readText() ?: "")
                    NetworkResult.Error(message = error.toString())
                }

                is CancellationException -> {
                    NetworkResult.Error(message = "Cancellation Exception")
                }

                else -> {
                    NetworkResult.Error(message = "Something went wrong.")
                }
            }
        }
    }
}
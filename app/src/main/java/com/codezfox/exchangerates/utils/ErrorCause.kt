package com.codezfox.exchangerates.utils

import android.content.Context
import com.codezfox.exchangerates.R
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorCause(exception: Throwable, message: String? = null) : Exception(message, exception) {

    var messageResId: Int? = null

    var isConnectException = false

    fun getMessageFull(context: Context): String {

        val stringBuilder = StringBuilder()

        val errorMessage = message

        if (errorMessage != null) {

            stringBuilder.append(errorMessage)

        } else {

            if (messageResId != null) {
                stringBuilder.append(context.getString(messageResId!!))
            }

            if (cause != null) {
                if (messageResId != null) {
                    stringBuilder.append("\n\n")
                }
                stringBuilder.append(cause.localizedMessage)
            }

        }

        return stringBuilder.toString()
    }

    fun getMessage(context: Context): String? {

        val errorMessage = message

        if (errorMessage != null) {

            return errorMessage

        } else {

            if (messageResId != null) {
                return context.getString(messageResId!!)
            }

        }

        return null
    }


    fun getLocalizedMessage(): String? {
        return cause?.localizedMessage
    }

}

fun Throwable.parseException() = when (this) {
    is ErrorCause -> this
    is SocketTimeoutException -> ErrorCause(this).also {
        it.messageResId = R.string.error_socket_timeout_exception
        it.isConnectException = true
    }
    is UnknownHostException -> ErrorCause(this).also {
        it.messageResId = R.string.error_network_exception
        it.isConnectException = true
    }
    is ConnectException -> ErrorCause(this).also {
        it.messageResId = R.string.error_network_exception
        it.isConnectException = true
    }
    else -> ErrorCause(this)
}


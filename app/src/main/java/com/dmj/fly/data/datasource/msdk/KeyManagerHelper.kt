package com.dmj.fly.data.datasource.msdk

import dji.sdk.keyvalue.key.KeyTools
import dji.sdk.keyvalue.key.DJIKey
import dji.sdk.keyvalue.value.base.DJIValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object KeyManagerHelper {

    fun <T : DJIValue> listenKey(key: DJIKey<T>): Flow<T> = callbackFlow {
        val listener = dji.sdk.keyvalue.key.listener.DJIKeyDataCallBack { _, newValue, _ ->
            newValue?.let { value ->
                trySend(value as T)
            }
        }

        val result = dji.sdk.keyvalue.key.KeyManager.getInstance().addListener(key, listener)
        if (!result) {
            Timber.e("Failed to add listener for key: $key")
            close()
        }

        awaitClose {
            dji.sdk.keyvalue.key.KeyManager.getInstance().removeListener(key, listener)
        }
    }

    suspend fun <T : DJIValue> getKey(key: DJIKey<T>): T? = suspendCancellableCoroutine { continuation ->
        val result = dji.sdk.keyvalue.key.KeyManager.getInstance().getValue(key) { errorCode, value ->
            if (errorCode == 0 && value != null) {
                continuation.resume(value as T)
            } else {
                Timber.e("getKey failed: errorCode=$errorCode")
                continuation.resume(null)
            }
        }
        if (!result) {
            continuation.resume(null)
        }
    }

    suspend fun <T : DJIValue> setKey(key: DJIKey<T>, value: T): Result<Unit> = suspendCancellableCoroutine { continuation ->
        val result = dji.sdk.keyvalue.key.KeyManager.getInstance().setValue(key, value) { errorCode ->
            if (errorCode == 0) {
                continuation.resume(Result.success(Unit))
            } else {
                Timber.e("setKey failed: errorCode=$errorCode")
                continuation.resume(Result.failure(Exception("Failed to set key, error: $errorCode")))
            }
        }
        if (!result) {
            continuation.resume(Result.failure(Exception("Failed to set key")))
        }
    }

    suspend fun <T : DJIValue> actionKey(key: DJIKey<T>): Result<Unit> = suspendCancellableCoroutine { continuation ->
        val result = dji.sdk.keyvalue.key.KeyManager.getInstance().performAction(key) { errorCode ->
            if (errorCode == 0) {
                continuation.resume(Result.success(Unit))
            } else {
                Timber.e("actionKey failed: errorCode=$errorCode")
                continuation.resume(Result.failure(Exception("Failed to perform action, error: $errorCode")))
            }
        }
        if (!result) {
            continuation.resume(Result.failure(Exception("Failed to perform action")))
        }
    }
}
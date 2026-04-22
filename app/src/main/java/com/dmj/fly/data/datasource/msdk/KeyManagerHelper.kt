package com.dmj.fly.data.datasource.msdk

import dji.sdk.keyvalue.key.Key
import dji.v5.common.callback.CommonCallbacks
import dji.v5.common.error.IDJIError
import dji.v5.manager.KeyManager
import com.dmj.fly.domain.model.Result
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KeyManagerHelper @Inject constructor() {

    private val keyManager = KeyManager.getInstance()

    fun <T> listenKey(key: Key<T>): Flow<T> = callbackFlow {
        val listener = CommonCallbacks.KeyListener<T> { _, newValue ->
            newValue?.let { trySend(it) }
        }
        keyManager.listen(key, this@KeyManagerHelper, listener)
        awaitClose { keyManager.cancelListen(key, listener) }
    }

    suspend fun <T> getKey(key: Key<T>): T? = suspendCoroutine { continuation ->
        keyManager.getValue(key, object : CommonCallbacks.CompletionCallback<T> {
            override fun onSuccess(value: T) {
                continuation.resume(value)
            }
            override fun onFailure(error: IDJIError) {
                continuation.resume(null)
            }
        })
    }

    suspend fun <T> setKey(key: Key<T>, value: T): Result<Unit> = suspendCoroutine { continuation ->
        keyManager.setValue(key, value, object : CommonCallbacks.CompletionCallback<Void> {
            override fun onSuccess(unused: Void?) {
                continuation.resume(Result.Success(Unit))
            }
            override fun onFailure(error: IDJIError) {
                continuation.resume(Result.Error(error.description()))
            }
        })
    }
}
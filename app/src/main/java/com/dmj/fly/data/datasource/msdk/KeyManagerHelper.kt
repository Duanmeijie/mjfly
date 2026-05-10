package com.dmj.fly.data.datasource.msdk

import com.dmj.fly.sdk.DjiSdkManager
import com.dmj.fly.domain.model.Result
import dji.sdk.keyvalue.key.DJIKey
import dji.v5.common.callback.CommonCallbacks
import dji.v5.common.error.IDJIError
import dji.v5.manager.KeyManager
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KeyManagerHelper @Inject constructor() {

    suspend fun <T> getKey(key: DJIKey<T>): T? = suspendCoroutine { continuation ->
        KeyManager.getInstance().getValue(key, object : CommonCallbacks.CompletionCallbackWithParam<T> {
            override fun onSuccess(value: T) {
                continuation.resume(value)
            }
            override fun onFailure(error: IDJIError) {
                continuation.resume(null)
            }
        })
    }

    suspend fun <T> setKey(key: DJIKey<T>, value: T): Result<Unit> = suspendCoroutine { continuation ->
        KeyManager.getInstance().setValue(key, value, object : CommonCallbacks.CompletionCallback {
            override fun onSuccess() {
                continuation.resume(Result.Success(Unit))
            }
            override fun onFailure(error: IDJIError) {
                continuation.resume(Result.Error(error.description()))
            }
        })
    }
}
package com.dmj.fly.data.datasource.msdk

import com.dmj.fly.sdk.DjiSdkManager
import com.dmj.fly.domain.model.Result
import dji.sdk.keyvalue.key.DJIKey
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * KeyManager 帮助类
 * 封装 DJI SDK 5.17.0 的 Key 访问方式
 *
 * ⚠️ 真机测试警告:以下功能需在连接真实 DJI 设备、SDK 注册成功后测试
 */
@Singleton
class KeyManagerHelper @Inject constructor() {

    /**
     * 监听 Key 变化
     * @param key DJIKey 实例
     * @return Flow<T> 实时数据流
     */
    fun <T> listenKey(key: DJIKey<T>): Flow<T> {
        return DjiSdkManager.listenKey(key)
    }

    /**
     * 获取 Key 当前值
     * @param key DJIKey 实例
     * @return T? 当前值，失败返回 null
     */
    suspend fun <T> getKey(key: DJIKey<T>): T? {
        return DjiSdkManager.getKey(key)
    }

    /**
     * 设置 Key 值
     * @param key DJIKey 实例
     * @param value 要设置的值
     * @return Result<Unit> 操作结果
     *
     * ⚠️ 真机测试警告:涉及飞控、相机等操作必须在连接真实 DJI 设备且环境安全条件下测试
     */
    suspend fun <T> setKey(key: DJIKey<T>, value: T): Result<Unit> {
        return DjiSdkManager.setKey(key, value)
    }
}

package com.dmj.fly.data.datasource.msdk;

import com.dmj.fly.sdk.DjiSdkManager;
import com.dmj.fly.domain.model.Result;
import dji.sdk.keyvalue.key.DJIKey;
import dji.v5.common.callback.CommonCallbacks;
import dji.v5.common.error.IDJIError;
import dji.v5.manager.KeyManager;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u0004\u0018\u0001H\u0004\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0007J0\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00062\u0006\u0010\u000b\u001a\u0002H\u0004H\u0086@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/dmj/fly/data/datasource/msdk/KeyManagerHelper;", "", "()V", "getKey", "T", "key", "Ldji/sdk/keyvalue/key/DJIKey;", "(Ldji/sdk/keyvalue/key/DJIKey;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setKey", "Lcom/dmj/fly/domain/model/Result;", "", "value", "(Ldji/sdk/keyvalue/key/DJIKey;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class KeyManagerHelper {
    
    @javax.inject.Inject()
    public KeyManagerHelper() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final <T extends java.lang.Object>java.lang.Object getKey(@org.jetbrains.annotations.NotNull()
    dji.sdk.keyvalue.key.DJIKey<T> key, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super T> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final <T extends java.lang.Object>java.lang.Object setKey(@org.jetbrains.annotations.NotNull()
    dji.sdk.keyvalue.key.DJIKey<T> key, T value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
}
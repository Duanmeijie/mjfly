package com.dmj.fly.data.datasource.msdk;

import dji.sdk.keyvalue.key.KeyTools;
import dji.sdk.keyvalue.key.DJIKey;
import dji.sdk.keyvalue.value.base.DJIValue;
import kotlinx.coroutines.flow.Flow;
import timber.log.Timber;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ(\u0010\f\u001a\u0004\u0018\u0001H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0086@\u00a2\u0006\u0002\u0010\u000bJ$\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00060\u000e\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tJ<\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\t2\u0006\u0010\u0010\u001a\u0002H\u0006H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0011\u0010\u0012\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0013"}, d2 = {"Lcom/dmj/fly/data/datasource/msdk/KeyManagerHelper;", "", "()V", "actionKey", "Lkotlin/Result;", "", "T", "Ldji/sdk/keyvalue/value/base/DJIValue;", "key", "Ldji/sdk/keyvalue/key/DJIKey;", "actionKey-gIAlu-s", "(Ldji/sdk/keyvalue/key/DJIKey;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getKey", "listenKey", "Lkotlinx/coroutines/flow/Flow;", "setKey", "value", "setKey-0E7RQCE", "(Ldji/sdk/keyvalue/key/DJIKey;Ldji/sdk/keyvalue/value/base/DJIValue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class KeyManagerHelper {
    @org.jetbrains.annotations.NotNull
    public static final com.dmj.fly.data.datasource.msdk.KeyManagerHelper INSTANCE = null;
    
    private KeyManagerHelper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final <T extends dji.sdk.keyvalue.value.base.DJIValue>kotlinx.coroutines.flow.Flow<T> listenKey(@org.jetbrains.annotations.NotNull
    dji.sdk.keyvalue.key.DJIKey<T> key) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final <T extends dji.sdk.keyvalue.value.base.DJIValue>java.lang.Object getKey(@org.jetbrains.annotations.NotNull
    dji.sdk.keyvalue.key.DJIKey<T> key, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super T> $completion) {
        return null;
    }
}
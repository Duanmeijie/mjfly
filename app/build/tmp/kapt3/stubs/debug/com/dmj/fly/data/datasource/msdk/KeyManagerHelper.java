package com.dmj.fly.data.datasource.msdk;

import dji.sdk.keyvalue.key.Key;
import dji.v5.common.callback.CommonCallbacks;
import dji.v5.common.error.IDJIError;
import dji.v5.manager.KeyManager;
import com.dmj.fly.domain.model.Result;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0006\u001a\u0004\u0018\u0001H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u001f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\f\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\rJ*\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0011\u001a\u0002H\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0012R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/dmj/fly/data/datasource/msdk/KeyManagerHelper;", "", "()V", "keyManager", "Ldji/v5/manager/KeyManager;", "kotlin.jvm.PlatformType", "getKey", "T", "key", "error/NonExistentClass", "(Lerror/NonExistentClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listenKey", "Lkotlinx/coroutines/flow/Flow;", "(Lerror/NonExistentClass;)Lkotlinx/coroutines/flow/Flow;", "setKey", "Lcom/dmj/fly/domain/model/Result;", "", "value", "(Lerror/NonExistentClass;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class KeyManagerHelper {
    private final dji.v5.manager.KeyManager keyManager = null;
    
    @javax.inject.Inject
    public KeyManagerHelper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final <T extends java.lang.Object>kotlinx.coroutines.flow.Flow<T> listenKey(@org.jetbrains.annotations.NotNull
    Key<T> key) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final <T extends java.lang.Object>java.lang.Object getKey(@org.jetbrains.annotations.NotNull
    Key<T> key, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super T> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final <T extends java.lang.Object>java.lang.Object setKey(@org.jetbrains.annotations.NotNull
    Key<T> key, T value, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.dmj.fly.domain.model.Result<kotlin.Unit>> $completion) {
        return null;
    }
}
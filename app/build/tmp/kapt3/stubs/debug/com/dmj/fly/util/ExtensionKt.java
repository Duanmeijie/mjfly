package com.dmj.fly.util;

import android.content.Context;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001\u001aQ\u0010\u0004\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\"\u0010\f\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\r\u00a2\u0006\u0002\u0010\u0010\u001a\u0014\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u001a\u0014\u0010\u0016\u001a\u00020\u0012*\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0012\u001a\n\u0010\u0019\u001a\u00020\u0012*\u00020\u0017\u001a\u001c\u0010\u001a\u001a\u00020\u0005*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00122\b\b\u0002\u0010\u001d\u001a\u00020\u0015\u001a\u001c\u0010\u001a\u001a\u00020\u0005*\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\u00122\b\b\u0002\u0010\u001d\u001a\u00020\u0015\u00a8\u0006\u001f"}, d2 = {"coerceInRange", "", "min", "max", "collectFlow", "", "T", "Landroidx/lifecycle/LifecycleOwner;", "flow", "Lkotlinx/coroutines/flow/Flow;", "state", "Landroidx/lifecycle/Lifecycle$State;", "collector", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Landroidx/lifecycle/LifecycleOwner;Lkotlinx/coroutines/flow/Flow;Landroidx/lifecycle/Lifecycle$State;Lkotlin/jvm/functions/Function2;)V", "format", "", "", "digits", "", "formatDate", "", "pattern", "formatFileSize", "showToast", "Landroid/content/Context;", "message", "duration", "Landroidx/fragment/app/Fragment;", "app_debug"})
public final class ExtensionKt {
    
    public static final void showToast(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$showToast, @org.jetbrains.annotations.NotNull()
    java.lang.String message, int duration) {
    }
    
    public static final void showToast(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$showToast, @org.jetbrains.annotations.NotNull()
    java.lang.String message, int duration) {
    }
    
    public static final <T extends java.lang.Object>void collectFlow(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner $this$collectFlow, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.Flow<? extends T> flow, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.Lifecycle.State state, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> collector) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatFileSize(long $this$formatFileSize) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatDate(long $this$formatDate, @org.jetbrains.annotations.NotNull()
    java.lang.String pattern) {
        return null;
    }
    
    public static final float coerceInRange(float $this$coerceInRange, float min, float max) {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String format(double $this$format, int digits) {
        return null;
    }
}
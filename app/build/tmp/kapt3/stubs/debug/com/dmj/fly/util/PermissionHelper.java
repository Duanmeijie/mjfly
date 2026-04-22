package com.dmj.fly.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.activity.result.ActivityResultLauncher;
import androidx.core.content.ContextCompat;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0012J\u0016\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0005R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0017"}, d2 = {"Lcom/dmj/fly/util/PermissionHelper;", "", "()V", "REQUIRED_PERMISSIONS", "", "", "getREQUIRED_PERMISSIONS", "()[Ljava/lang/String;", "[Ljava/lang/String;", "getMissingPermissions", "", "context", "Landroid/content/Context;", "hasAllPermissions", "", "requestPermissions", "", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "shouldShowRationale", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "permission", "app_debug"})
public final class PermissionHelper {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String[] REQUIRED_PERMISSIONS = null;
    @org.jetbrains.annotations.NotNull
    public static final com.dmj.fly.util.PermissionHelper INSTANCE = null;
    
    private PermissionHelper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String[] getREQUIRED_PERMISSIONS() {
        return null;
    }
    
    public final boolean hasAllPermissions(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getMissingPermissions(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    public final void requestPermissions(@org.jetbrains.annotations.NotNull
    androidx.activity.result.ActivityResultLauncher<java.lang.String[]> launcher) {
    }
    
    public final boolean shouldShowRationale(@org.jetbrains.annotations.NotNull
    androidx.appcompat.app.AppCompatActivity activity, @org.jetbrains.annotations.NotNull
    java.lang.String permission) {
        return false;
    }
}
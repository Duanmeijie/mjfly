package com.dmj.fly.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/dmj/fly/util/Constants;", "", "()V", "DEFAULT_AGORA_APP_ID", "", "DEFAULT_ALTITUDE", "", "DEFAULT_GB28181_URL", "DEFAULT_HEADING", "DEFAULT_RTMP_URL", "DEFAULT_RTSP_URL", "DEFAULT_SPEED", "DJI_APP_KEY", "STICK_FREQUENCY_HZ", "", "VIDEO_BITRATE", "VIDEO_FRAME_RATE", "VIDEO_HEIGHT", "VIDEO_WIDTH", "app_debug"})
public final class Constants {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DJI_APP_KEY = "EE45A36E38A16E49C8CF38A8";
    public static final int STICK_FREQUENCY_HZ = 20;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DEFAULT_RTMP_URL = "rtmp://your-server/live/stream";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DEFAULT_RTSP_URL = "rtsp://your-server/live/stream";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DEFAULT_GB28181_URL = "your-gb28181-server";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DEFAULT_AGORA_APP_ID = "your-agora-app-id";
    public static final int VIDEO_WIDTH = 1920;
    public static final int VIDEO_HEIGHT = 1080;
    public static final int VIDEO_BITRATE = 4000;
    public static final int VIDEO_FRAME_RATE = 30;
    public static final float DEFAULT_ALTITUDE = 100.0F;
    public static final float DEFAULT_SPEED = 10.0F;
    public static final float DEFAULT_HEADING = 0.0F;
    @org.jetbrains.annotations.NotNull()
    public static final com.dmj.fly.util.Constants INSTANCE = null;
    
    private Constants() {
        super();
    }
}
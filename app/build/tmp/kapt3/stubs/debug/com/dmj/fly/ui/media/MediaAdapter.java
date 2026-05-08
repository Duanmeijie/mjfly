package com.dmj.fly.ui.media;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.dmj.fly.databinding.ItemMediaBinding;
import com.dmj.fly.domain.model.MediaFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0016\u0017B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\r\u001a\u00020\u00062\n\u0010\u000e\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\fH\u0016J\u001c\u0010\u0010\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0016J\u001a\u0010\u0014\u001a\u00020\u00062\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nR\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/dmj/fly/ui/media/MediaAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/dmj/fly/domain/model/MediaFile;", "Lcom/dmj/fly/ui/media/MediaAdapter$MediaViewHolder;", "onDownloadClick", "Lkotlin/Function1;", "", "onDeleteClick", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "downloadProgress", "", "", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setDownloadProgress", "progress", "MediaDiffCallback", "MediaViewHolder", "app_debug"})
public final class MediaAdapter extends androidx.recyclerview.widget.ListAdapter<com.dmj.fly.domain.model.MediaFile, com.dmj.fly.ui.media.MediaAdapter.MediaViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.dmj.fly.domain.model.MediaFile, kotlin.Unit> onDownloadClick = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.dmj.fly.domain.model.MediaFile, kotlin.Unit> onDeleteClick = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.Map<java.lang.String, java.lang.Integer> downloadProgress;
    
    public MediaAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.dmj.fly.domain.model.MediaFile, kotlin.Unit> onDownloadClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.dmj.fly.domain.model.MediaFile, kotlin.Unit> onDeleteClick) {
        super(null);
    }
    
    public final void setDownloadProgress(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Integer> progress) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.dmj.fly.ui.media.MediaAdapter.MediaViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.dmj.fly.ui.media.MediaAdapter.MediaViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/dmj/fly/ui/media/MediaAdapter$MediaDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/dmj/fly/domain/model/MediaFile;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class MediaDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.dmj.fly.domain.model.MediaFile> {
        
        public MediaDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.dmj.fly.domain.model.MediaFile oldItem, @org.jetbrains.annotations.NotNull()
        com.dmj.fly.domain.model.MediaFile newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.dmj.fly.domain.model.MediaFile oldItem, @org.jetbrains.annotations.NotNull()
        com.dmj.fly.domain.model.MediaFile newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/dmj/fly/ui/media/MediaAdapter$MediaViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/dmj/fly/databinding/ItemMediaBinding;", "(Lcom/dmj/fly/ui/media/MediaAdapter;Lcom/dmj/fly/databinding/ItemMediaBinding;)V", "bind", "", "file", "Lcom/dmj/fly/domain/model/MediaFile;", "formatDate", "", "timestamp", "", "formatFileSize", "size", "app_debug"})
    public final class MediaViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.dmj.fly.databinding.ItemMediaBinding binding = null;
        
        public MediaViewHolder(@org.jetbrains.annotations.NotNull()
        com.dmj.fly.databinding.ItemMediaBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.dmj.fly.domain.model.MediaFile file) {
        }
        
        private final java.lang.String formatFileSize(long size) {
            return null;
        }
        
        private final java.lang.String formatDate(long timestamp) {
            return null;
        }
    }
}
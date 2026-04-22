package com.dmj.fly.util

import timber.log.Timber

object Logger {

    private const val DEFAULT_TAG = "DMJ_FLY"

    fun d(message: String, tag: String = DEFAULT_TAG) {
        Timber.tag(tag).d(message)
    }

    fun i(message: String, tag: String = DEFAULT_TAG) {
        Timber.tag(tag).i(message)
    }

    fun w(message: String, tag: String = DEFAULT_TAG) {
        Timber.tag(tag).w(message)
    }

    fun e(message: String, throwable: Throwable? = null, tag: String = DEFAULT_TAG) {
        if (throwable != null) {
            Timber.tag(tag).e(throwable, message)
        } else {
            Timber.tag(tag).e(message)
        }
    }

    fun v(message: String, tag: String = DEFAULT_TAG) {
        Timber.tag(tag).v(message)
    }

    fun wtf(message: String, throwable: Throwable? = null, tag: String = DEFAULT_TAG) {
        if (throwable != null) {
            Timber.tag(tag).wtf(throwable, message)
        } else {
            Timber.tag(tag).wtf(message)
        }
    }
}
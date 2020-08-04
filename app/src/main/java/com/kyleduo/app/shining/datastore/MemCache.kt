package com.kyleduo.app.shining.datastore

import android.os.SystemClock
import android.util.LruCache

class MemCache {

    private val cache = LruCache<String, CacheItem?>(1024 * 1024)

    fun <T> get(key: String): T? {
        val item = cache.get(key) ?: return null
        if (item.expiredTime != CacheItem.INFINITE && item.expiredTime < SystemClock.elapsedRealtime()) {
            remove(key)
            return null
        }
        @Suppress("UNCHECKED_CAST")
        return item.value as T
    }

    fun setValue(key: String, value: Any, ttl: Long = CacheItem.INFINITE) {
        val item = CacheItem(
            value,
            if (ttl == CacheItem.INFINITE) CacheItem.INFINITE else SystemClock.elapsedRealtime() + ttl
        )
        cache.put(key, item)
    }

    fun remove(key: String) {
        cache.remove(key)
    }

    data class CacheItem(
        val value: Any,
        val expiredTime: Long
    ) {
        companion object {
            const val INFINITE = -1L
        }
    }
}
package com.kyleduo.app.shining.utils

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken

class StringNullToEmptyAdapterFactory : TypeAdapterFactory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T>? {
        val rawType = type!!.rawType as Class<T>
        return if (rawType != String::class.java) {
            null
        } else (StringNullAdapter() as TypeAdapter<T>)
    }
}
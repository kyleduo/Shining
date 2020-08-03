package com.kyleduo.app.shining.datastore

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SPDataStore {

    fun getString(context: Context, name: String, key: String, fallback: String?): String? {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
            .getString(key, fallback)
    }

    @SuppressLint("ApplySharedPref")
    fun saveString(
        context: Context,
        name: String,
        key: String,
        value: String,
        commit: Boolean = true
    ): SharedPreferences.Editor {
        val editor = context.getSharedPreferences(name, Context.MODE_PRIVATE).edit()
        editor.putString(key, value)
        if (commit) {
            editor.commit()
        }
        return editor
    }
}
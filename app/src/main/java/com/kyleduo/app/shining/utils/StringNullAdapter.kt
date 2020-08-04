package com.kyleduo.app.shining.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

class StringNullAdapter : TypeAdapter<String>() {
    override fun write(out: JsonWriter?, value: String?) {
        if (out == null) {
            return
        }
        if (value == null) {
            out.nullValue()
            return
        }
        out.value(value)
    }

    override fun read(reader: JsonReader?): String {
        if (reader == null) {
            return ""
        }
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return ""
        }
        return reader.nextString();
    }
}
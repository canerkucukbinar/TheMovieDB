package com.ckucukbinar.themoviedb.extension

import com.samskivert.mustache.Mustache

fun String.withMustache(key: String, value: String): String {
    val map: MutableMap<String, String> = HashMap()
    map[key] = value
    return Mustache.compiler().compile(this).execute(map)?.let { text ->
        text
    } ?: run {
        ""
    }
}
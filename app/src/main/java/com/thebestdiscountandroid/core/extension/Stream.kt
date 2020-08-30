package com.thebestdiscountandroid.core.extension

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

@Throws(IOException::class)
fun InputStream.string(): String {
    val reader = BufferedReader(InputStreamReader(this))
    val total = StringBuilder()
    var line: String? = reader.readLine()
    while (line != null) {
        total.append(line).append('\n')
        line = reader.readLine()
    }
    return total.toString()
}
package cn.kaito.kkhl.utils

import java.io.ByteArrayOutputStream
import java.util.zip.Deflater
import java.util.zip.Inflater


fun String.zlibCompress(): ByteArray {
    val input = this.toByteArray(charset("UTF-8"))

    val output = ByteArray(input.size * 4)
    val compressor = Deflater().apply {
        setInput(input)
        finish()
    }
    val compressedDataLength: Int = compressor.deflate(output)
    return output.copyOfRange(0, compressedDataLength)
}

fun ByteArray.zlibDecompress(): String {
    val inflater = Inflater()
    val outputStream = ByteArrayOutputStream()

    return outputStream.use {
        val buffer = ByteArray(1024)

        inflater.setInput(this)

        var count = -1
        while (count != 0) {
            count = inflater.inflate(buffer)
            outputStream.write(buffer, 0, count)
        }

        inflater.end()
        outputStream.toString("UTF-8")
    }
}
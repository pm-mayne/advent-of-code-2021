package util

import java.io.File

object Util {

    fun getInstance(file: String): List<String> {
        return File(Thread.currentThread().contextClassLoader.getResource(file).file).readLines()
    }
}
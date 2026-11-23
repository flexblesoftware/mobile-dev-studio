package com.mobiledevstudio.app.data

import java.io.File

data class FileNode(
    val file: File,
    val name: String = file.name,
    val isDirectory: Boolean = file.isDirectory,
    val size: Long = if (file.isFile) file.length() else 0,
    val extension: String = file.extension.lowercase(),
    val children: List<FileNode> = emptyList()
)

class FileManager {
    fun getFileTree(rootDir: File): FileNode {
        return buildNode(rootDir)
    }

    private fun buildNode(file: File): FileNode {
        if (!file.isDirectory) {
            return FileNode(file)
        }
        val childNodes = file.listFiles()
            ?.sortedWith(compareBy({ !it.isDirectory }, { it.name.lowercase() }))
            ?.map { buildNode(it) } ?: emptyList()

        return FileNode(
            file = file,
            children = childNodes
        )
    }

    fun readFile(file: File): String {
        return if (file.exists() && file.isFile) file.readText() else ""
    }

    fun writeFile(file: File, content: String): Boolean {
        return try {
            file.parentFile?.mkdirs()
            file.writeText(content)
            true
        } catch (e: Exception) {
            false
        }
    }
}

package com.mobiledevstudio.app.engine

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File

data class ApkItem(
    val file: File,
    val name: String = file.name,
    val size: Long = file.length(),
    val lastModified: Long = file.lastModified()
)

class ApkManager(private val context: Context) {

    fun getGeneratedApks(): List<ApkItem> {
        val list = mutableListOf<ApkItem>()
        val projectsDir = File(Environment.getExternalStorageDirectory(), "Projects")
        if (projectsDir.exists()) {
            projectsDir.walkTopDown().filter { it.isFile && it.extension.lowercase() == "apk" }.forEach {
                list.add(ApkItem(it))
            }
        }
        return list.sortedByDescending { it.lastModified }
    }

    fun installApk(file: File) {
        val uri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(
                context,
                "com.mobiledevstudio.app.fileprovider",
                file
            )
        } else {
            Uri.fromFile(file)
        }

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/vnd.android.package-archive")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.startActivity(intent)
    }

    fun shareApk(file: File) {
        val uri = FileProvider.getUriForFile(
            context,
            "com.mobiledevstudio.app.fileprovider",
            file
        )
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/vnd.android.package-archive"
            putExtra(Intent.EXTRA_STREAM, uri)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.startActivity(Intent.createChooser(intent, "Share APK via").apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}

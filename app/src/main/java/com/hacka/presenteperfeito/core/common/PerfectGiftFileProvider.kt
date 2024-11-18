package com.hacka.presenteperfeito.core.common

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.hacka.presenteperfeito.R
import java.io.File

class PerfectGiftFileProvider : FileProvider(R.xml.file_paths) {
    companion object{
        fun getImageUri(context: Context): Uri {
            val tempFile = File.createTempFile("profile_image", ".jpg", context.cacheDir)

            val authority = context.packageName + ".fileprovider"

            return getUriForFile(context, authority, tempFile)
        }
    }
}
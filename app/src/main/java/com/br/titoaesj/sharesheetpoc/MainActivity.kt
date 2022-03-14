package com.br.titoaesj.sharesheetpoc

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import com.br.titoaesj.sharesheetpoc.helper.AlbumStorageDirFactory
import com.br.titoaesj.sharesheetpoc.helper.BaseAlbumDirFactory
import com.br.titoaesj.sharesheetpoc.helper.ImageUtils
import com.br.titoaesj.sharesheetpoc.ui.theme.SharesheetPOCTheme
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : ComponentActivity() {

    private val TAG = MainActivity::class.simpleName

    private var jetCaptureView: MutableState<ContentCardShared>? = null
    private var mCurrentPhotoUri: Uri? = null
    private var mAlbumStorageDirFactory: AlbumStorageDirFactory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val localView = LocalView.current
            SharesheetPOCTheme {
                MainScreen(
                    shareContent = {
                        shareContent(view =  (jetCaptureView?.value as ContentCardShared) )
                    },
                    contentCardShared = {
                        ContentCardSharedBuild()
                    }
                )
            }
        }

        mAlbumStorageDirFactory = BaseAlbumDirFactory()

    }

    private fun bitmapToFile(screenShot: Bitmap): File? {
        createImageFile()?.let { outFile ->
            mCurrentPhotoUri = Uri.fromFile(outFile)
            val stream: OutputStream = FileOutputStream(outFile)
            screenShot.compress(Bitmap.CompressFormat.JPEG, 25, stream)
            stream.flush()
            stream.close()
            return outFile
        }
        return null
    }

    private fun shareContent(view: ContentCardShared) {
        val bitmap = ImageUtils.generateBitmap(view)
        bitmapToFile(bitmap)?.let { mFile ->
            val mPath = FileProvider.getUriForFile(
                this,
                BuildConfig.APPLICATION_ID.plus(".provider"),
                mFile
            )
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            shareIntent.type = "image/*"
            shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.shared_content_label))
            shareIntent.putExtra(Intent.EXTRA_STREAM, mPath)
            startActivity(Intent.createChooser(shareIntent, getString(R.string.shared_title_label)))
        }
    }

    private fun createImageFile(): File? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val imageFileName: String = "IMG_".plus(timeStamp).plus("_")
        getAlbumDir()?.let { albumF ->
            return File.createTempFile(imageFileName, ".jpg", albumF)
        }
        return null
    }

    private fun getAlbumDir(): File? {
        var storageDir: File? = null
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            storageDir = mAlbumStorageDirFactory!!.getAlbumStorageDir(getAlbumName())
            if (!storageDir.mkdirs()) {
                if (!storageDir.exists()) {
                    Log.d(TAG, "failed to create directory")
                    return null
                }
            }
        } else {
            Log.v(TAG, "External storage is not mounted READ/WRITE.")
        }
        return storageDir
    }

    private fun getAlbumName(): String {
        return getString(R.string.app_name)
    }

    @Composable
    private fun ContentCardSharedBuild() {
        jetCaptureView = remember { mutableStateOf(ContentCardShared(this@MainActivity)) }
        AndroidView(modifier = Modifier.wrapContentSize(),
            factory = {
                ContentCardShared(it).apply {
                    post {
                        jetCaptureView?.value = this
                    }
                }
            }
        )
    }

}
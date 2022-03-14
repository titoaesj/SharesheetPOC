package com.br.titoaesj.sharesheetpoc.helper

import android.os.Environment
import java.io.File

/**
 * Projeto SharesheetPOC
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 24 de fevereiro de 2022.
 */
class BaseAlbumDirFactory : AlbumStorageDirFactory() {

    override fun getAlbumStorageDir(albumName: String): File {
        return File(
            Environment.getExternalStorageDirectory().toString()
                    + CAMERA_DIR
                    + albumName
        )
    }

    companion object {
        // Standard storage location for digital camera files
        private val CAMERA_DIR = "/dcim/"
    }
}

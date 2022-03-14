package com.br.titoaesj.sharesheetpoc.helper

import java.io.File

/**
 * Projeto SharesheetPOC
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 24 de fevereiro de 2022.
 */
abstract class AlbumStorageDirFactory {
    abstract fun getAlbumStorageDir(albumName: String): File

}
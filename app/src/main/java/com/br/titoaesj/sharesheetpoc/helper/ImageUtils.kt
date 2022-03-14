package com.br.titoaesj.sharesheetpoc.helper

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View

/**
 * Projeto SharesheetPOC
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 14 de março de 2022.
 */

class ImageUtils {

    companion object{
        fun generateBitmap(view: View): Bitmap {
            val bitmap = Bitmap.createBitmap(
                view.width,
                view.height,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            view.layout(
                view.left,
                view.top,
                view.right,
                view.bottom
            )
            view.draw(canvas)
            return bitmap
        }
    }
}
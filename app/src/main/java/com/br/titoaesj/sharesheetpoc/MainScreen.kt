package com.br.titoaesj.sharesheetpoc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Projeto SharesheetPOC
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 14 de março de 2022.
 */
@Composable
fun MainScreen(
    shareContent: () -> Unit,
    contentCardShared: @Composable () -> Unit

) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Color.DarkGray
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            contentCardShared()
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = shareContent) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Compartilhar")
                }
            }
        }
    }
}
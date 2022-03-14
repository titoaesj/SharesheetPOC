package com.br.titoaesj.sharesheetpoc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Projeto SharesheetPOC
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 14 de março de 2022.
 */
@Composable
fun ContentCardSharedUI() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .layoutId(245)
            .background(Color.White)
    ) {
        Text(
            text = stringResource(id = R.string.jetpack_compose_label),
            fontFamily = FontFamily.Cursive,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(id = R.drawable.jetpack_compose), contentDescription = null)
    }
}

@Preview
@Composable
fun ContentCardSharedUIPreview() = ContentCardSharedUI()

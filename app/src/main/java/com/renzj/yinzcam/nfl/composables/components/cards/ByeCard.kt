package com.renzj.yinzcam.nfl.composables.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.renzj.yinzcam.R
import com.renzj.yinzcam.nfl.composables.NflColor

@Composable
fun ByeCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(id = R.string.bye).uppercase(),
            color = NflColor.Gray,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
    }
}

@Preview
@Composable
fun ByeCardPreview(){
    ByeCard()
}
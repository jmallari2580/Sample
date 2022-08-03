package com.renzj.yinzcam.nfl.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.renzj.yinzcam.R

@Composable
fun ScreenMessage(
    type: MessageType,
    isErrorMessage: Boolean
) {
    val messageSub: String = when (type) {
        MessageType.Empty -> {
            stringResource(id = R.string.empty_message_sub)
        }
        MessageType.Error -> {
            stringResource(id = R.string.error_message)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if(type == MessageType.Empty) stringResource(id = R.string.empty_message)
            else stringResource(id = R.string.error_message_sorry ),
            color = NflColor.Black,
            fontSize = 32.sp
        )

        Text(
            text = messageSub,
            color = NflColor.Gray,
            fontSize = 16.sp
        )

        if (isErrorMessage) {
            Button(onClick = { /*TODO*/ },
            modifier = Modifier.padding(16.dp)
                ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                Text(text = stringResource(id = R.string.try_again))
            }
        }


    }

}

@Preview
@Composable
fun ScreenMessagePreviewEmpty() {
    ScreenMessage(
        type = MessageType.Empty,
        isErrorMessage = false
    )
}

@Preview
@Composable
fun ScreenMessagePreviewError() {
    ScreenMessage(
        type = MessageType.Error,
        isErrorMessage = true
    )
}

enum class MessageType {
    Error,
    Empty
}

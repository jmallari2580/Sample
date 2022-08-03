package com.renzj.yinzcam.nfl.composables.components.cards

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.renzj.yinzcam.nfl.composables.NflColor
import com.skydoves.landscapist.glide.GlideImage

private const val defaultImageUrl = "https://www.pngfind.com/pngs/m/20-203409_logo-nfl-png-nfl-logo-png-transparent-png.png"

@SuppressLint("CheckResult")
@Composable
fun LogoRow(
    modifier: Modifier = Modifier,
    homeUrl: String,
    awayUrl: String
) {

    Row(
        modifier = modifier.wrapContentWidth()
    ) {

        GlideImage(
            modifier = Modifier.requiredSize(40.dp),
            imageModel = awayUrl,
            contentScale = ContentScale.Crop,
            loading = {
                CircularProgressIndicator()
            },
            failure = {
                GlideImage(
                    modifier = Modifier.requiredSize(40.dp),
                    imageModel = defaultImageUrl,
                    contentScale = ContentScale.Crop,
                )
            }
        )

        Text(
            text = "@",
            color = NflColor.Gray,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        GlideImage(
            modifier = Modifier.requiredSize(40.dp),
            imageModel = homeUrl,
            contentScale = ContentScale.Crop,
            loading = {
                CircularProgressIndicator()
            },
            failure = {
                GlideImage(
                    modifier = Modifier.requiredSize(40.dp),
                    imageModel = defaultImageUrl,
                    contentScale = ContentScale.Crop,
                )
            }
        )
    }
}


@Preview
@Composable
fun LogoRowPreview() {
    LogoRow(
        homeUrl = defaultImageUrl,
        awayUrl = defaultImageUrl
    )
}
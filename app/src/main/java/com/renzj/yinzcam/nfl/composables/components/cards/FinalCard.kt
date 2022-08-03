package com.renzj.yinzcam.nfl.composables.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.renzj.yinzcam.nfl.composables.components.cards.models.FinalCardModel
import com.renzj.yinzcam.nfl.composables.NflColor

@Composable
fun FinalCard(model: FinalCardModel) {

    val constraintSet = ConstraintSet {
        val homeTeamName = createRefFor("homeTeamName")
        val awayTeamName = createRefFor("awayTeamName")
        val homeTeamScore = createRefFor("homeTeamScore")
        val awayTeamScore = createRefFor("awayTeamScore")
        val logoRow = createRefFor("logoRow")
        val date = createRefFor("date")
        val preseasonWeekNumber = createRefFor("preseasonWeekNumber")
        val gameState = createRefFor("gameState")

        constrain(homeTeamName) {
            end.linkTo(parent.end, margin = 4.dp)
            top.linkTo(parent.top, margin = 4.dp)
            bottom.linkTo(homeTeamScore.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(awayTeamName) {
            start.linkTo(parent.start, margin = 4.dp)
            top.linkTo(parent.top, margin = 4.dp)
            bottom.linkTo(awayTeamScore.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(awayTeamScore) {
            start.linkTo(parent.start)
            top.linkTo(awayTeamName.bottom, margin = 4.dp)
            bottom.linkTo(date.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(logoRow) {
            start.linkTo(awayTeamScore.end)
            end.linkTo(homeTeamScore.start)
            bottom.linkTo(preseasonWeekNumber.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(homeTeamScore) {
            end.linkTo(parent.end, margin = 4.dp)
            top.linkTo(homeTeamName.bottom, margin = 4.dp)
            bottom.linkTo(gameState.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }


        constrain(date) {
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
        constrain(preseasonWeekNumber) {
            start.linkTo(logoRow.start)
            end.linkTo(logoRow.end)
            bottom.linkTo(parent.bottom, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(gameState) {
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }


    }
    ConstraintLayout(
        constraintSet = constraintSet,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Text(
            text = model.awayName,
            modifier = Modifier.layoutId("awayTeamName"),
            color = NflColor.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = model.homeName,
            modifier = Modifier.layoutId("homeTeamName"),
            color = NflColor.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = model.awayScore,
            modifier = Modifier.layoutId("awayTeamScore"),
            color = NflColor.Black,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        LogoRow(
            modifier = Modifier.layoutId("logoRow"),
            homeUrl = model.homeImageUrl,
            awayUrl = model.awayImageUrl
        )

        Text(
            text = model.homeScore,
            modifier = Modifier.layoutId("homeTeamScore"),
            color = NflColor.Black,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = model.gameDate,
            modifier = Modifier.layoutId("date"),
            color = NflColor.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Preseason ${model.preSeasonNumber}",
            modifier = Modifier.layoutId("preseasonWeekNumber"),
            color = NflColor.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = model.gameState,
            modifier = Modifier.layoutId("gameState"),
            color = NflColor.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

    }
}

@Preview
@Composable
fun FinalCardPreview() {
    FinalCard(
        model = FinalCardModel(
            itemId = 12345678,
            homeName = "Falcons",
            awayName = "Eagles",
            homeScore = "28",
            awayScore = " 34",
            homeImageUrl = "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_phi_light.png",
            awayImageUrl = "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_gb_light.png",
            gameDate = "Fri, Aug 8",
            preSeasonNumber = "1",
            gameState = "Final",
        )
    )
}
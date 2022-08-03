package com.renzj.yinzcam.nfl.composables.components.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.renzj.yinzcam.nfl.composables.NflColor
import com.renzj.yinzcam.nfl.composables.components.cards.models.FinalCardModel
import com.renzj.yinzcam.nfl.composables.components.cards.models.ScheduleCardModel


@Composable
fun ScheduledCard(model: ScheduleCardModel) {

    val constraintSet = ConstraintSet {
        val homeTeamName = createRefFor("homeTeamName")
        val awayTeamName = createRefFor("awayTeamName")
        val homeTeamStanding = createRefFor("homeTeamStanding")
        val awayTeamStanding = createRefFor("awayTeamStanding")
        val logoRow = createRefFor("logoRow")
        val date = createRefFor("date")
        val weekNumber = createRefFor("weekNumber")
        val gameTime = createRefFor("gameTime")
        val channel = createRefFor("channel")

        constrain(homeTeamName) {
            end.linkTo(parent.end, margin = 4.dp)
            top.linkTo(parent.top, margin = 4.dp)
            bottom.linkTo(homeTeamStanding.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(awayTeamName) {
            start.linkTo(parent.start, margin = 4.dp)
            top.linkTo(parent.top, margin = 4.dp)
            bottom.linkTo(awayTeamStanding.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(awayTeamStanding) {
            start.linkTo(parent.start)
            top.linkTo(awayTeamName.bottom, margin = 4.dp)
            bottom.linkTo(date.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(logoRow) {
            start.linkTo(awayTeamStanding.end)
            end.linkTo(homeTeamStanding.start)
            top.linkTo(homeTeamName.bottom, margin = 4.dp)
            bottom.linkTo(weekNumber.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(homeTeamStanding) {
            end.linkTo(parent.end, margin = 4.dp)
            top.linkTo(homeTeamName.bottom, margin = 4.dp)
            bottom.linkTo(gameTime.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(date) {
            start.linkTo(parent.start)
            bottom.linkTo(channel.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
        constrain(weekNumber) {
            start.linkTo(date.end)
            end.linkTo(gameTime.start)
            bottom.linkTo(channel.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(gameTime) {
            end.linkTo(parent.end)
            bottom.linkTo(channel.top, margin = 4.dp)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(channel) {
            start.linkTo(parent.start)
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
            text = model.awayName.uppercase(),
            modifier = Modifier.layoutId("awayTeamName"),
            color = NflColor.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = model.homeName.uppercase(),
            modifier = Modifier.layoutId("homeTeamName"),
            color = NflColor.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = model.awayStanding,
            modifier = Modifier.layoutId("awayTeamStanding"),
            color = NflColor.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        LogoRow(
            modifier = Modifier.layoutId("logoRow"),
            homeUrl = model.homeImageUrl,
            awayUrl = model.awayImageUrl
        )

        Text(
            text = model.homeStanding,
            modifier = Modifier.layoutId("homeTeamStanding"),
            color = NflColor.Black,
            fontSize = 14.sp,
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
            text = "Week ${model.weekNumber}",
            modifier = Modifier.layoutId("weekNumber"),
            color = NflColor.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = model.gameTime,
            modifier = Modifier.layoutId("gameTime"),
            color = NflColor.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = model.venue.uppercase(),
            modifier = Modifier.layoutId("channel"),
            color = NflColor.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

    }
}


@Preview
@Composable
fun ScheduledCardPreview() {
    ScheduledCard(
        model = ScheduleCardModel(
            itemId = 123,
            homeName = "Falcons",
            awayName = "Eagles",
            homeStanding = "2 - 8",
            awayStanding = "3 - 4",
            homeImageUrl = "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_phi_light.png",
            awayImageUrl = "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_gb_light.png",
            gameDate = "Fri, Aug 8",
            weekNumber = "1",
            gameTime = "7:10 PM",
            venue = "espn",
        )
    )
}
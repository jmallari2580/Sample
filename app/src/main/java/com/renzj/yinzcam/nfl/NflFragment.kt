package com.renzj.yinzcam.nfl

import android.annotation.SuppressLint
import android.icu.text.MessageFormat.format
import android.os.Bundle
import android.text.format.DateFormat
import android.text.format.DateFormat.format
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.gson.internal.bind.util.ISO8601Utils.format
import com.renzj.yinzcam.databinding.MainFragmentBinding
import com.renzj.yinzcam.nfl.composables.*
import com.renzj.yinzcam.nfl.composables.components.cards.ByeCard
import com.renzj.yinzcam.nfl.composables.components.cards.FinalCard
import com.renzj.yinzcam.nfl.composables.components.cards.models.FinalCardModel
import com.renzj.yinzcam.nfl.composables.components.cards.ScheduledCard
import com.renzj.yinzcam.nfl.composables.components.cards.models.ScheduleCardModel
import com.renzj.yinzcam.nfl.model.GameSection
import com.renzj.yinzcam.nfl.utils.ScreenUiState
import com.renzj.yinzcam.nfl.utils.toImageUrl
import dagger.hilt.android.AndroidEntryPoint
import java.lang.String.format
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class NflFragment : Fragment() {

    companion object {
        fun newInstance() = NflFragment()
    }

    private lateinit var viewModel: NflViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.setContent {
            CompositionLocalProvider {
                MainLayout()
            }
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NflViewModel::class.java)
        // TODO: Use the ViewModel
    }

    @Composable
    fun MainLayout() {
        Column(modifier = Modifier.fillMaxSize()) {
            NflTopAppBar(title = "schedule", icon = Icons.Default.Menu) {}
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(NflColor.GrayBackground)
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "2022 Regular Season".uppercase(),
                    color = NflColor.Gray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            ScreenUiStateHandler(
                uiState = viewModel.uiState,
                loading = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                },
                error = { ScreenMessage(type = MessageType.Error, isErrorMessage = true) },
                empty = { ScreenMessage(type = MessageType.Empty, isErrorMessage = false) },
                completed = {
                    NflInformationList(uiState = viewModel.uiState)
                }
            )
        }
    }

}

@Composable
fun NflInformationList(uiState: NflViewModel.NflUiState) {
    val dataList by derivedStateOf { uiState.gameDataList }

    Log.d("nfldata", "nfldata fargment $uiState.gameDataList")
    LazyColumn() {
        items(dataList) { item ->
            when (item.itemType) {
                "F" -> {
                    FinalCard(model = FinalCardModel(
                        itemId = item.itemId!!,
                        homeName = item.homeName,
                        awayName = item.awayName,
                        homeScore = item.homeScore,
                        awayScore = item.awayScore,
                        homeImageUrl = item.triCodeHomeImageUrl,
                        awayImageUrl = item.triCodeAwayImageUrl,
                        gameDate = item.gameDayDate,
                        preSeasonNumber = item.seasonWeekNumber,
                        gameState = item.gameState

                    ))
                }
                "S" -> {
                    ScheduledCard(model = ScheduleCardModel(
                        itemId = item.itemId!!,
                        homeName = item.homeName,
                        awayName = item.awayName,
                        homeStanding = item.homeStanding,
                        awayStanding = item.awayStanding,
                        homeImageUrl = item.triCodeHomeImageUrl,
                        awayImageUrl = item.triCodeAwayImageUrl,
                        gameDate = item.gameDayDate,
                        weekNumber = item.seasonWeekNumber,
                        gameTime = item.gameTime,
                        venue = item.venue
                    ))
                }
                "B" -> {
                    ByeCard()
                }
            }
            Divider()
        }
    }


}


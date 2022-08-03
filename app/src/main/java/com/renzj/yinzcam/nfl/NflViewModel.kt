package com.renzj.yinzcam.nfl

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renzj.yinzcam.nfl.composables.components.cards.models.CurrentTeamInfoModel
import com.renzj.yinzcam.nfl.composables.components.cards.models.GameInfoModel
import com.renzj.yinzcam.nfl.model.Game
import com.renzj.yinzcam.nfl.model.GameSection
import com.renzj.yinzcam.nfl.utils.DataState
import com.renzj.yinzcam.nfl.utils.FormattedDate
import com.renzj.yinzcam.nfl.utils.ScreenUiState
import com.renzj.yinzcam.nfl.utils.toImageUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NflViewModel @Inject constructor(private val nflFragmentRepository: NflFragmentRepository) :
    ViewModel() {

    var uiState by mutableStateOf(NflUiState())

    private var gameList = mutableListOf<GameInfoModel>()
    lateinit var currentTeam: CurrentTeamInfoModel

    init {
        getNflData()
    }

    private fun getNflData() = viewModelScope.launch {
        Log.d("nfldata", "nfldata viewModel ${gameList}")
        nflFragmentRepository.getNflResponseData()
            .catch { e ->
                updateLoadingState(DataState.Failed(e))
            }.collect { nflResponseData ->
                Log.d("nfldata", "nfldata viewModel collect ${gameList}")
                if (nflResponseData == null) {
                    updateLoadingState(DataState.Empty())
                } else {

                    Log.d("nfldata", "nfldata viewModel collect else ${gameList}")
                    nflResponseData.let { data ->
                        data.Team.let { team ->
                            currentTeam = CurrentTeamInfoModel(
                                name = team.Name,
                                record = team.Record,
                                triCode = team.TriCode,
                            )
                        }
                        data.GameSection.forEach {
                            it.Game.forEach { game ->
                                gameList.add(
                                    GameInfoModel(
                                        itemId = game.Id,
                                        itemType = game.Type ?: "",
                                        homeName = if (game.IsHome) (currentTeam.name
                                            ?: "") else (game.Opponent?.Name ?: ""),
                                        homeStanding = if (game.IsHome) (currentTeam.record
                                            ?: "") else (game.Opponent?.Record ?: ""),
                                        triCodeHomeImageUrl = if (game.IsHome) (currentTeam.triCode?.toImageUrl()
                                            ?: "") else (game.Opponent?.TriCode?.toImageUrl()
                                            ?: ""),
                                        homeScore = game.HomeScore ?: "",
                                        awayName = if (game.IsHome.not()) (currentTeam.name
                                            ?: "") else (game.Opponent?.Name ?: ""),
                                        awayStanding = if (game.IsHome.not()) (currentTeam.record
                                            ?: "") else (game.Opponent?.Record ?: ""),
                                        triCodeAwayImageUrl = if (game.IsHome.not()) (currentTeam.triCode?.toImageUrl()
                                            ?: "") else (game.Opponent?.TriCode?.toImageUrl()
                                            ?: ""),
                                        awayScore = game.AwayScore ?: "",
                                        gameDayDate = game.Date?.Timestamp?.FormattedDate()
                                            ?: "",
                                        gameTime = game.Date?.Time ?: "",
                                        seasonType = it.Heading,
                                        seasonWeekNumber = game.Label ?: "",
                                        venue = game.Venue ?: "",
                                        gameState = game.GameState ?: ""
                                    )
                                )
                            }
                        }
                        updateLoadingState(DataState.Success(gameList))
                        Log.d("nfldata", "nfldata viewModel data  $data")
                        Log.d("nfldata", "nfldata viewModel data  $gameList")
                    }

                }
            }
    }


    private fun updateLoadingState(nflData: DataState<List<GameInfoModel>>) = when (nflData) {
        is DataState.Empty -> {
            uiState = uiState.copy(
                loadingState = ScreenUiState.LoadingState.Empty
            )
        }
        is DataState.Failed -> {
            uiState = uiState.copy(
                loadingState = ScreenUiState.LoadingState.Error
            )
        }
        is DataState.Success -> {
            uiState = uiState.copy(
                loadingState = ScreenUiState.LoadingState.Completed,
                gameDataList = nflData.data
            )
        }

    }


    data class NflUiState(
        override val loadingState: ScreenUiState.LoadingState = ScreenUiState.LoadingState.Loading,
        val gameDataList: List<GameInfoModel> = emptyList()
    ) : ScreenUiState
}
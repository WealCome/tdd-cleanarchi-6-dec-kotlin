package com.wealcome.wealtennis.hexagon.usecases

import com.wealcome.wealtennis.hexagon.gateways.MatchScoreRepository
import com.wealcome.wealtennis.hexagon.models.MatchScore

class MatchArbitrator constructor(
    private val matchScoreRepository: MatchScoreRepository
) {

    private var matchScore = Pair("0", "0")
    private var winningPlayer: Int? = null

    fun computeNextScore(scoringPlayer: Int) {
        matchScore = nextScore(scoringPlayer)
        if(!isMatchOver(scoringPlayer))
            saveScore()
        if (isMatchOver(scoringPlayer)) {
            winningPlayer = scoringPlayer
            resetMatch()
            saveScore()
        }
    }

    fun hasWon(player: Int): Boolean {
        return winningPlayer != null && winningPlayer == player
    }

    private fun nextScore(scoringPlayer: Int) =
        MatchScore().computeNextScore(matchScore, scoringPlayer)

    private fun isMatchOver(scoringPlayer: Int): Boolean =
        scoringPlayer == 1 && matchScore.first == "GAME"
                || scoringPlayer == 2 && matchScore.second == "GAME"

    private fun resetMatch() {
        matchScore = Pair("0", "0")
    }

    private fun saveScore() {
        matchScoreRepository.save(matchScore)
    }

}

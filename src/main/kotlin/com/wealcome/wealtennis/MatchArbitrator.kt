package com.wealcome.wealtennis

class MatchArbitrator {

    private var matchScore = Pair("0", "0")
    private var winningPlayer: Int? = null

    fun computeNextScore(scoringPlayer: Int) {
        matchScore = nextScore(scoringPlayer)
        if (isMatchOver(scoringPlayer)) {
            winningPlayer = scoringPlayer
            resetMatch()
        }
    }

    fun currentScore(): Pair<String, String> {
        return matchScore
    }

    fun hasWon(player: Int): Boolean {
        return winningPlayer != null && winningPlayer == player
    }

    private fun nextScore(scoringPlayer: Int) =
        ScoreComputation().computeNextScore(matchScore, scoringPlayer)

    private fun isMatchOver(scoringPlayer: Int): Boolean =
        scoringPlayer == 1 && matchScore.first == "GAME"
                || scoringPlayer == 2 && matchScore.second == "GAME"

    private fun resetMatch() {
        matchScore = Pair("0", "0")
    }

}

package com.wealcome.wealtennis.hexagon.usecases

import com.wealcome.wealtennis.hexagon.gateways.MatchScoreRepository
import com.wealcome.wealtennis.hexagon.models.MatchScore

class MatchArbitrator constructor(
    private val matchScoreRepository: MatchScoreRepository
) {

    private var matchScore = MatchScore()

    fun computeNextScore(scoringPlayer: Int) {
        matchScore = nextScore(scoringPlayer)
        saveScore()
    }

    private fun nextScore(scoringPlayer: Int) =
        matchScore.computeNextScore(matchScore, scoringPlayer)

    private fun saveScore() {
        matchScoreRepository.save(matchScore)
    }


}

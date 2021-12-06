package com.wealcome.wealtennis.adapters.secondary.gateways

import com.wealcome.wealtennis.hexagon.gateways.MatchScoreRepository
import com.wealcome.wealtennis.hexagon.models.MatchScore

class InMemoryMatchScoreRepository : MatchScoreRepository {

    private var matchScore: MatchScore = MatchScore()

    override fun save(matchScore: MatchScore) {
        this.matchScore = matchScore
    }

    fun currentScore(): MatchScore {
        return matchScore
    }

}

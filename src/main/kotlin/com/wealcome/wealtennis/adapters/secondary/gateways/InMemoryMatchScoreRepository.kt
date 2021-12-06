package com.wealcome.wealtennis.adapters.secondary.gateways

import com.wealcome.wealtennis.hexagon.gateways.MatchScoreRepository

class InMemoryMatchScoreRepository : MatchScoreRepository {

    private var matchScore: Pair<String, String> = Pair("0", "0")

    override fun save(matchScore: Pair<String, String>) {
        this.matchScore = matchScore
    }

    fun currentScore(): Pair<String, String> {
        return matchScore
    }

}

package com.wealcome.wealtennis.hexagon.gateways

import com.wealcome.wealtennis.hexagon.models.MatchScore

interface MatchScoreRepository {

    fun save(matchScore: MatchScore)

}

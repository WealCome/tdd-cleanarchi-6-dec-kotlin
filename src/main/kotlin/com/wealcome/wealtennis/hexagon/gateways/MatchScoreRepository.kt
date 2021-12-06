package com.wealcome.wealtennis.hexagon.gateways

interface MatchScoreRepository {

    fun save(matchScore: Pair<String, String>)

}

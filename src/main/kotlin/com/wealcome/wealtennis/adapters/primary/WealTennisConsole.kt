package com.wealcome.wealtennis.adapters.primary

import com.wealcome.wealtennis.adapters.secondary.gateways.InMemoryMatchScoreRepository
import com.wealcome.wealtennis.hexagon.usecases.MatchArbitrator


fun main(args: Array<String>) {
    val matchScoreRepository = InMemoryMatchScoreRepository()
    val matchArbitrator = MatchArbitrator(matchScoreRepository)
    matchArbitrator.computeNextScore(1)
    matchArbitrator.computeNextScore(1)
    matchArbitrator.computeNextScore(1)
    matchArbitrator.computeNextScore(2)
    matchArbitrator.computeNextScore(2)
    matchArbitrator.computeNextScore(2)
    matchArbitrator.computeNextScore(1)
    println(matchScoreRepository.currentScore())
}

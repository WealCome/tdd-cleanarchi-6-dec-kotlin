package com.wealcome.wealtennis

import com.wealcome.wealtennis.adapters.secondary.gateways.InMemoryMatchScoreRepository
import com.wealcome.wealtennis.hexagon.usecases.MatchArbitrator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MatchArbitratorTest {

    private val matchScoreRepository: InMemoryMatchScoreRepository =
        InMemoryMatchScoreRepository()
    private val computeMatchScore = MatchArbitrator(matchScoreRepository)

    @Test
    fun `Any match should start with no point`() {
        assertCurrentScore(Pair("0", "0"))
    }

    @Test
    fun `The first player has scored the first point`() {
        playerHavingScored(1)
        assertCurrentScore(Pair("15", "0"))
    }

    @Test
    fun `The second player has scored the first point`() {
        playerHavingScored(2)
        assertCurrentScore(Pair("0", "15"))
    }

    @Test
    fun `Only one of both players can score a point`() {
        playerHavingScored(3)
        assertCurrentScore(Pair("0", "0"))
    }

    @Test
    fun `The first player has scored twice in a row`() {
        playerHavingScored(1, 1)
        assertCurrentScore(Pair("30", "0"))
    }

    @Test
    fun `The second player has scored twice in a row`() {
        playerHavingScored(2, 2)
        assertCurrentScore(Pair("0", "30"))
    }

    @Test
    fun `The players have just reached DEUCE`() {
        playerHavingScored(1, 1, 1, 2, 2, 2)
        assertCurrentScore(Pair("40", "40"))
    }

    @Test
    fun `The first player has just reached ADVANTAGE`() {
        playerHavingScored(1, 1, 1, 2, 2, 2, 1)
        assertCurrentScore(Pair("ADVANTAGE", "40"))
    }

    @Test
    fun `The second player has just reached ADVANTAGE`() {
        playerHavingScored(1, 1, 1, 2, 2, 2, 2)
        assertCurrentScore(Pair("40", "ADVANTAGE"))
    }

    @Test
    fun `The second player has just reached ADVANTAGE but the first player has equalized`() {
        playerHavingScored(1, 1, 1, 2, 2, 2, 2, 1)
        assertCurrentScore(Pair("40", "40"))
    }

    @Test
    fun `The first player has just reached ADVANTAGE but the second player has equalized`() {
        playerHavingScored(1, 1, 1, 2, 2, 2, 1, 2)
        assertCurrentScore(Pair("40", "40"))
    }

    @Test
    fun `The first player has won a blank game`() {
        playerHavingScored(1, 1, 1, 1)
        assertPlayerWon(1)
        assertCurrentScore(Pair("0", "0"))
    }

    @Test
    fun `The second player has won a blank game`() {
        playerHavingScored(2, 2, 2, 2)
        assertPlayerWon(2)
        assertCurrentScore(Pair("0", "0"))
    }

    private fun playerHavingScored(vararg player: Int) {
        for (p in player)
            computeMatchScore.computeNextScore(p)
    }

    private fun assertCurrentScore(expectedScore: Pair<String, String>) {
        assertThat(matchScoreRepository.currentScore()).isEqualTo(expectedScore)
    }

    private fun assertPlayerWon(player: Int) {
        assertThat(computeMatchScore.hasWon(player)).isTrue
    }

}

package com.wealcome.wealtennis.hexagon.models

class MatchScore constructor(
    private val score: Pair<String, String> = Pair("0", "0"),
    private val winningPlayer: Int? = null
) {

    fun computeNextScore(matchScore: MatchScore, scoringPlayer: Int): MatchScore {
        if (scoringPlayer == 1) {
            if (isDeuce(matchScore.score))
                return MatchScore(Pair("ADVANTAGE", matchScore.score.second))
            if (matchScore.score.second == "ADVANTAGE")
                return MatchScore(Pair("40", "40"))
            val nextScore = nextScore(matchScore.score.first)
            return MatchScore(
                Pair(nextScore, matchScore.score.second),
                when (nextScore) {
                    "GAME" -> 1
                    else -> null
                }
            )
        }
        if (scoringPlayer == 2) {
            if (isDeuce(matchScore.score))
                return MatchScore(Pair(matchScore.score.first, "ADVANTAGE"))
            if (matchScore.score.first == "ADVANTAGE")
                return MatchScore(Pair("40", "40"))
            val nextScore = nextScore(matchScore.score.second)
            return MatchScore(
                Pair(matchScore.score.first, nextScore),
                when (nextScore) {
                    "GAME" -> 2
                    else -> null
                }
            )
        }
        return MatchScore(Pair("0", "0"))
    }

    private fun nextScore(fromScore: String): String {
        return possibleScores[possibleScores.indexOf(fromScore) + 1]
    }

    private fun isDeuce(matchScore: Pair<String, String>): Boolean {
        return matchScore.first == "40" && matchScore.second == "40"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatchScore

        if (score != other.score) return false
        if (winningPlayer != other.winningPlayer) return false

        return true
    }

    companion object MatchScoreCompanion {
        private val possibleScores: List<String> = listOf("0", "15", "30", "40", "GAME")
    }

}

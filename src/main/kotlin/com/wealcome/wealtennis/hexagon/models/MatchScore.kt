package com.wealcome.wealtennis.hexagon.models

class MatchScore {

    fun computeNextScore(matchScore: Pair<String, String>, scoringPlayer: Int): Pair<String, String> {
        if (scoringPlayer == 1) {
            if (isDeuce(matchScore))
                return Pair("ADVANTAGE", matchScore.second)
            if (matchScore.second == "ADVANTAGE")
                return Pair("40", "40")
            return Pair(nextScore(matchScore.first), matchScore.second)
        }
        if (scoringPlayer == 2) {
            if (isDeuce(matchScore))
                return Pair(matchScore.first, "ADVANTAGE")
            if (matchScore.first == "ADVANTAGE")
                return Pair("40", "40")
            return Pair(matchScore.first, nextScore(matchScore.second))
        }
        return Pair("0", "0")
    }

    private fun nextScore(fromScore: String): String {
        return possibleScores[possibleScores.indexOf(fromScore) + 1]
    }

    private fun isDeuce(matchScore: Pair<String, String>): Boolean {
        return matchScore.first == "40" && matchScore.second == "40"
    }

    companion object MatchScore {
        private val possibleScores: List<String> = listOf("0", "15", "30", "40", "GAME")
    }

}

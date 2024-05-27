package com.example.calculadoradeintereses

import kotlin.math.log10
import kotlin.math.pow

class CompoundInterestCalculator {

    fun calculateFinalAmount(principal: Double, rate: Double, time: Double): Double {
        return principal * (1 + rate).pow(time)
    }

    fun calculateCompoundInterest(principal: Double, rate: Double, time: Double): Double {
        val finalAmount = calculateFinalAmount(principal, rate, time)
        return finalAmount - principal
    }

    fun calculatePrincipal(finalAmount: Double, rate: Double, time: Double): Double {
        return finalAmount / (1 + rate).pow(time)
    }

    fun calculateRate(finalAmount: Double, principal: Double, time: Double): Double {
        return (finalAmount / principal).pow(1 / time) - 1
    }

    fun calculateTime(finalAmount: Double, principal: Double, rate: Double): Double {
        return log10(finalAmount / principal) / log10(1 + rate)
    }
}

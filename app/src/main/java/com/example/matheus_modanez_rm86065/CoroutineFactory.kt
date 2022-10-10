package com.example.matheus_modanez_rm86065

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

object CoroutineFactory {

    private val delay: Long = 500L

    suspend fun getDelay(): Long {
        return withContext(Dispatchers.Default) {
            this.async {
                delay
            }
        }.await()
    }

    suspend fun calculateProgress(value: Int): Int {
        return withContext(Dispatchers.Default) {
            this.async {
                value + 5
            }
        }.await()
    }
}
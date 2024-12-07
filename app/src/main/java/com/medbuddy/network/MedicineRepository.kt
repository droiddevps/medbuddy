package com.medbuddy.network

import com.medbuddy.model.MainModelResponse

interface MedicineRepository {

    suspend fun getMedicines():MainModelResponse?
}
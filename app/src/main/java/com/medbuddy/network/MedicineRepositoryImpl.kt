package com.medbuddy.network

import com.medbuddy.model.MainModelResponse
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MedicineRepository {

    override suspend fun getMedicines(): MainModelResponse? {
        return apiService.getMedicines().body()
    }
}
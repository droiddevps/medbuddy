package com.medbuddy.network.usecase

import com.medbuddy.common.UiState
import com.medbuddy.model.MainModelResponse
import com.medbuddy.network.MedicineRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMedicineListUseCase @Inject constructor(private val repoImpl: MedicineRepositoryImpl) {

    operator fun invoke(): Flow<UiState<MainModelResponse>> = flow {
        emit(UiState.Loading())
        try {
            emit(UiState.Success(data = repoImpl.getMedicines()))
        } catch (e: Exception) {
            emit(UiState.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}
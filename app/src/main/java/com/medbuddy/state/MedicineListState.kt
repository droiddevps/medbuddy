package com.medbuddy.state

import com.medbuddy.model.MainModelResponse

data class MedicineListState(
    val isLoading: Boolean = false,
    val data: MainModelResponse? = null,
    val error: String = ""

)
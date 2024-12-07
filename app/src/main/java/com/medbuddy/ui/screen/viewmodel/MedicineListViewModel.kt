package com.medbuddy.ui.screen.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medbuddy.common.UiState
import com.medbuddy.model.AssociatedDrugItem
import com.medbuddy.network.usecase.GetMedicineListUseCase
import com.medbuddy.state.MedicineListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MedicineListViewModel @Inject constructor(private val getMedicineListUseCase: GetMedicineListUseCase) :
    ViewModel() {

    private val _medicineListState = mutableStateOf(MedicineListState())
    val medicineList: State<MedicineListState> get() = _medicineListState

    private val _selectedMedicine = mutableStateOf<AssociatedDrugItem?>(null)
    val selectedMedicine: State<AssociatedDrugItem?> get() = _selectedMedicine


    init {
        getMedicineListUseCase.invoke().onEach {
            when (it) {
                is UiState.Loading -> {
                    _medicineListState.value = MedicineListState(isLoading = true)
                }

                is UiState.Success -> {
                    _medicineListState.value = MedicineListState(data = it.data)
                }

                is UiState.Error -> {
                    _medicineListState.value = MedicineListState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun selectedMedicine(item: AssociatedDrugItem) {
        _selectedMedicine.value = item
    }

}
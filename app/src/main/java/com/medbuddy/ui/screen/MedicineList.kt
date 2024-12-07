package com.medbuddy.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.medbuddy.model.AssociatedDrugItem
import com.medbuddy.model.MainModelResponse
import com.medbuddy.ui.component.DetailBottomSheet
import com.medbuddy.ui.component.MedicineListItem
import com.medbuddy.ui.screen.viewmodel.MedicineListViewModel
import com.medbuddy.ui.theme.GradientBox
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Calendar

@Composable
fun Medicines(navController: NavController, userName: String) {
    var showDialog by remember { mutableStateOf(false) }
    val viewModel: MedicineListViewModel = hiltViewModel()
    val result = viewModel.medicineList.value

    GradientBox(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "${getGreetingMessage()}, $userName",
                    fontSize = 25.sp, color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

            result.data?.let {
                Timber.i("Medicines: $it")
                val drugs = filterMedicines(it)

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                ) {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(drugs.size) { i ->
                            MedicineListItem(drugs[i]) { medicine ->
                                Timber.d("Medicine clicked: ${medicine.name}")
                                viewModel.selectedMedicine(medicine)
                                showDialog = true
                            }
                        }
                    }
                }
            }
        }


        if (result.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }

        }

        if (result.error.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = result.error.toString())
            }
        }

    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Medicine: ${viewModel.selectedMedicine.value?.name}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Dose: ${viewModel.selectedMedicine.value?.dose ?: "N/A"} Strength: ${viewModel.selectedMedicine.value?.strength}",
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Cancel")
                        }
                        TextButton(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                }
            }
        }
    }

}

fun filterMedicines(mainData: MainModelResponse): List<AssociatedDrugItem> {
    val drugs = mutableListOf<AssociatedDrugItem>()
    mainData.problems.forEach { problem ->
        problem.diabetes.forEach { condition ->
            condition.medications.forEach { medication ->
                medication.medicationsClasses.forEach { medicationClass ->
                    // Iterate through all fields in the medication class
                    val drugClasses = listOfNotNull(
                        medicationClass.className,
                        medicationClass.className2
                    ).flatten()

                    drugClasses.forEach { drugClass ->
                        val combinedDrugs = listOfNotNull(
                            drugClass.associatedDrug,
                            drugClass.associatedDrug2
                        ).flatten()
                        drugs.addAll(combinedDrugs)
                    }
                }
            }
        }
    }
    return drugs
}

fun getGreetingMessage(): String {
    val c = Calendar.getInstance()
    val timeOfDay = c.get(Calendar.HOUR_OF_DAY)

    return when (timeOfDay) {
        in 4..11 -> "Good Morning"
        in 12..15 -> "Good Afternoon"
        in 16..23 -> "Good Evening"
        else -> "Hello"
    }
}
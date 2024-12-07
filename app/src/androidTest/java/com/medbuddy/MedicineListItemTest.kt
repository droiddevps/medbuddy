package com.medbuddy

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.medbuddy.model.AssociatedDrugItem
import com.medbuddy.ui.component.MedicineListItem
import org.junit.Rule
import org.junit.Test

class MedicineListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun medicineListItem_displaysMedicineName() {
        val medicine = AssociatedDrugItem(
            name = "Paracetamol",
            dose = "500mg",
            strength = "Tablet"
        )

        composeTestRule.setContent {
            MedicineListItem(medicine = medicine, onMedicineClick = {})
        }

        composeTestRule.onNodeWithText("Paracetamol").assertIsDisplayed()
    }

    @Test
    fun medicineListItem_displaysMedicineDoseAndStrength() {
        val medicine = AssociatedDrugItem(
            name = "Ibuprofen",
            dose = "200mg",
            strength = "Capsule"
        )

        composeTestRule.setContent {
            MedicineListItem(medicine = medicine, onMedicineClick = {})
        }

        composeTestRule.onNodeWithText("Dose: 200mg").assertIsDisplayed()
        composeTestRule.onNodeWithText("Strength: Capsule").assertIsDisplayed()
    }
}
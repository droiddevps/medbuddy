package com.medbuddy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medbuddy.R
import com.medbuddy.model.AssociatedDrugItem
import com.medbuddy.ui.theme.MedBuddyTheme

@Composable
fun MedicineListItem(medicine: AssociatedDrugItem, onMedicineClick: (AssociatedDrugItem) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .border(
                1.dp,
                Color.White,
                RoundedCornerShape(8.dp)
            )
            .clickable(enabled = true) {
                onMedicineClick(medicine)
            }, colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            // Medicine Image
            Image(
                painterResource(R.drawable.ic_pills),
                contentDescription = "Medicine Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 10.dp)
                    .clip(CircleShape)
            )

            // Medicine Details
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = medicine.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Dose: ${medicine.dose}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Strength: ${medicine.strength}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

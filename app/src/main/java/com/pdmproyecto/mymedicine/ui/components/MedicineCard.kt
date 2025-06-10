package com.pdmproyecto.mymedicine.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pdmproyecto.mymedicine.R
import com.pdmproyecto.mymedicine.data.Medicine

@Composable
fun MedicineCard(medicine: Medicine, deleteButtonAction: () -> Unit = {}){

    val cardShape = RoundedCornerShape(20.dp)

    Row (modifier = Modifier
        .fillMaxWidth()
        .height(65.dp)
        .background(color = Color.White, shape = cardShape)
        .border(width = 0.dp, color = Color.Black, shape = cardShape)
        .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){

        Image(
            painter = painterResource(id = R.drawable.pill_icon),
            contentDescription = "pill icon",
            modifier = Modifier.weight(15f).fillMaxHeight(0.7f))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(70f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly

        ){
            Text(text = medicine.name, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = medicine.lastDate, color = Color.Black)
        }



        IconButton(
            modifier = Modifier.weight(15f),
            onClick = deleteButtonAction,
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete from list"
            )
        }
    }
}
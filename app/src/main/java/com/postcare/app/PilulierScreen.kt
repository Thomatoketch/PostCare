package com.postcare.app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PilulierScreen(onBack: () -> Unit = {}, selectedTab: Int = 0, onSelectTab: (Int) -> Unit = {}) {
    Scaffold(
        topBar = { Header(onBack) },
        bottomBar = { BottomNavBar(selectedTab, onSelectTab) }
    ) { padding ->
        PilulierContent(Modifier.padding(padding))
    }
}

@Composable
private fun PilulierContent(modifier: Modifier = Modifier) {
    var medicament by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }
    var indication by remember { mutableStateOf("") }
    var selectedDays by remember { mutableStateOf(setOf<Int>()) }
    val days = listOf("L", "M", "M", "J", "V", "S", "D")

    Column(modifier = modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = medicament,
            onValueChange = { medicament = it },
            label = { Text("Médicament") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = dosage,
            onValueChange = { dosage = it },
            label = { Text("Dosage") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = indication,
            onValueChange = { indication = it },
            label = { Text("Indications spécifiques") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            days.forEachIndexed { index, day ->
                val selected = selectedDays.contains(index)
                Button(
                    onClick = {
                        selectedDays = if (selected) selectedDays - index else selectedDays + index
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = if (selected) colorResource(R.color.postcare_blue) else Color.LightGray),
                    shape = CircleShape,
                    modifier = Modifier.size(40.dp)
                ) {
                    Text(day, color = Color.White, textAlign = TextAlign.Center)
                }
            }
        }

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.postcare_blue)),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Valider", color = Color.White)
        }
    }
}

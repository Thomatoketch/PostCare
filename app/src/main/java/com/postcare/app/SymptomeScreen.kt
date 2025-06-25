package com.postcare.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SymptomeScreen(onBack: () -> Unit = {}, selectedTab: Int = 0, onSelectTab: (Int) -> Unit = {}) {
    Scaffold(
        topBar = { Header(onBack) },
        bottomBar = { BottomNavBar(selectedTab, onSelectTab) }
    ) { padding ->
        SymptomeContent(Modifier.padding(padding))
    }
}

@Composable
private fun SymptomeContent(modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("") }
    var response by remember { mutableStateOf("") }
    var selectedPain by remember { mutableStateOf(-1) }

    Column(modifier = modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Message du patient") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Échelle de douleur",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            itemsIndexed((0..10).toList()) { index, _ ->
                val color = lerp(Color(0xFF00B565), Color.Red, index / 10f)
                Button(
                    onClick = { selectedPain = index },
                    colors = ButtonDefaults.buttonColors(containerColor = color),
                    shape = CircleShape,
                    modifier = Modifier.size(40.dp)
                ) {
                    Text(text = "$index", color = Color.White)
                }
            }
        }

        OutlinedTextField(
            value = response,
            onValueChange = { response = it },
            label = { Text("Réponse") },
            modifier = Modifier.fillMaxWidth().heightIn(min = 120.dp),
            maxLines = 5
        )

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.postcare_blue)),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("SOUMETTRE", color = Color.White)
        }
    }
}

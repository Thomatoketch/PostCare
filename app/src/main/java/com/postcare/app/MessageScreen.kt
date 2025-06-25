package com.postcare.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun MessageScreen(onBack: () -> Unit = {}, selectedTab: Int = 0, onSelectTab: (Int) -> Unit = {}) {
    Scaffold(
        topBar = { Header(onBack) },
        bottomBar = { BottomNavBar(selectedTab, onSelectTab) }
    ) { padding ->
        MessageContent(Modifier.padding(padding))
    }
}

@Composable
private fun MessageContent(modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("") }
    var response by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Message du patient") },
            modifier = Modifier.fillMaxWidth()
        )

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
            Text("ENVOYER", color = Color.White)
        }
    }
}

package com.postcare.app

import android.app.Activity
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

class ImagePredictionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PredictionScreen()
        }
    }
}

@Composable
fun PredictionScreen() {
    val context = LocalContext.current
    val classifier = remember { ImageClassifier(context) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var result by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        selectedImageUri = uri
        uri?.let {
            context.contentResolver.openInputStream(it)?.use { stream ->
                val bitmap = BitmapFactory.decodeStream(stream)
                val outputs = classifier.classify(bitmap)
                result = outputs.joinToString(prefix = "Result: ")
            }
        }
    }

    Scaffold(
        topBar = { Header({ (context as? Activity)?.finish() }) },
        bottomBar = { BottomNavBar(0) {} }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            selectedImageUri?.let { uri ->
                context.contentResolver.openInputStream(uri)?.use { stream ->
                    val bitmap = BitmapFactory.decodeStream(stream)
                    Image(bitmap.asImageBitmap(), contentDescription = null, modifier = Modifier.size(200.dp))
                }
            }

            Button(
                onClick = { launcher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.postcare_blue))
            ) {
                Text("Choisir une image", color = androidx.compose.ui.graphics.Color.White)
            }

            if (result.isNotEmpty()) {
                Text(result)
            }
        }
    }
}

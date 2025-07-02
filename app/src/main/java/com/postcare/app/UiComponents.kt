package com.postcare.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Header(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(colorResource(id = R.color.postcare_blue))
    ) {
        IconButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.Center)
        )
    }
}

data class NavItem(val label: String, val icon: Int)

@Composable
fun BottomNavBar(selected: Int, onSelect: (Int) -> Unit) {
    val items = listOf(
        NavItem("Accueil", R.drawable.ic_home),
        NavItem("Rendez-vous", R.drawable.ic_calendrier),
        NavItem("Questions", R.drawable.ic_faq),
        NavItem("Suivi", R.drawable.ic_suivie),
        NavItem("Compte", R.drawable.ic_compte)
    )
    NavigationBar(containerColor = colorResource(id = R.color.postcare_blue)) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selected == index,
                onClick = { onSelect(index) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label,
                        tint = Color.Black
                    )
                },
                label = { Text(item.label, color = Color.Black) }
            )
        }
    }
}

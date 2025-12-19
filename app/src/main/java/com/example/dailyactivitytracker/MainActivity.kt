package com.example.dailyactivitytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityApp()
        }
    }
}

@Composable
fun ActivityApp() {
    var text by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Office") }
    val activities = remember { mutableStateListOf<Pair<String, Boolean>>() }

    Column(Modifier.padding(16.dp)) {
        Text("Daily Activity Tracker", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(8.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Activity name") }
        )

        Spacer(Modifier.height(8.dp))

        Row {
            listOf("Office", "Workout", "Custom").forEach {
                Button(
                    onClick = { category = it },
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Text(it)
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (text.isNotBlank()) {
                activities.add("$text ($category)" to false)
                text = ""
            }
        }) {
            Text("Add Activity")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(activities) { item ->
                Row(
                    Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Checkbox(
                        checked = item.second,
                        onCheckedChange = {
                            val index = activities.indexOf(item)
                            activities[index] = item.first to !item.second
                        }
                    )
                    Text(item.first)
                }
            }
        }
    }
}


package com.example.decision_making_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.decision_making_app.ui.theme.Decision_making_appTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val appState = AppState()
        setContent {
            Decision_making_appTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DecisionScreen(
                        decision = appState.decision,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Decision_making_appTheme {
        Greeting("Android")
    }
}

class AppState {
    val decision = listOf("Should we go?", "No", "Yes")
}

fun decideDecision(probability: Float): Int {
    val decision = Random.nextFloat()
    if (decision >= probability) {
        return 1
    }
    return 2
}

@Composable
fun DecisionScreen(
    decision: List<String>,
    modifier: Modifier = Modifier
) {
    var decide by remember { mutableIntStateOf(0) }
    var clickNum by remember { mutableIntStateOf(0) }

    // TODO: Make sure logic works
    // TODO: Make sure everything is aligned well
    // TODO: Add_click will probably not add to the counter. Make sure it's added properly

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = decision[decide],
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.width(24.dp))

        Row(modifier = Modifier.padding(all = 16.dp)) {
            Button(
                onClick = {
                    decide = decideDecision(0.5f)
                    clickNum += 1
                }
            ) {
                Text("Ok!")
            }

            Button(
                onClick = {
                    decide = decideDecision(0.25f)
                    clickNum += 1
                }
            ) {
                Text("Meh")
            }

            Button(
                onClick = {
                    decide = decideDecision(0.1f)
                    clickNum += 1
                }
            ) {
                Text("Nah")
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(clickNum.toString())
    }
}
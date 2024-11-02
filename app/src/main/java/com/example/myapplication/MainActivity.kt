package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.screens.QuoteListScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            delay(10000)
            DataManager.loadAssetsFromFile(applicationContext)
        }

        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    if (DataManager.isDataLoaded.value) {
        if(DataManager.currectPage.value == Pages.LISTING) {
            QuoteListScreen(data = DataManager.data) {
            }
        }
        else {

        }
    }
    else {
        Box(
            modifier = Modifier
                .fillMaxSize(1f)
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.headlineMedium)

        }
    }
}

@Composable
fun Recomposable() {
    val state = remember {
        mutableStateOf(0.0)
    }
    Log.d("TAGGED",  "Logged during Initial Composition")
    Button(onClick = {
        state.value = Math.random()
    }) {
        Log.d("TAGGED", "Logged during both Composition & Recompostion")
        Text(text = state.value.toString())
    }
}

@Preview
@Composable
fun setupText(name: String = "Crazzy", color: Color = Color.Red) {
    Text(text = "Hello $name", color = color)
}

@Preview
@Composable
fun setupImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Dummy Image",
        colorFilter = ColorFilter.tint(Color.Red),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun setupButton() {
    Button(
        onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
        containerColor = Color.Red,
        contentColor = Color.White,
            disabledContainerColor = Color.Gray, disabledContentColor = Color.Black
        ),
        enabled = false
    ) {
        Text(text = "Next")
        Image(painter = painterResource(id = androidx.core.R.drawable.ic_call_decline_low), contentDescription = "image color")
    }

}

@Composable
fun setBox() {
    Box(contentAlignment = Alignment.BottomEnd) {
        Image(painter = painterResource(id = androidx.core.R.drawable.ic_call_answer), contentDescription = "")
        Image(painter = painterResource(id = androidx.core.R.drawable.ic_call_answer_video), contentDescription = "")
    }
}

@Composable
fun listViewItem() {
    Row(Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
            contentDescription = "",
            Modifier.size(40.dp)
        )
        Column {
            Text(text = "jons Doe", fontWeight = FontWeight.Bold)
            Text(text = "Software Developer",  fontWeight = FontWeight.Thin, fontSize = 12.sp)

        }
    }
}

@Composable
fun PreviewFunction() {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "A", fontSize = 24.sp)
        Text(text = "B", fontSize = 24.sp)
    }
}

@Composable
fun PreviewModifier() {
    Text(
        text = "Hello",
        color = Color.White,
        modifier = Modifier
            .background(color = Color.Blue)
            .size(200.dp)
            .padding(36.dp)
            .border(4.dp, Color.Red)
            .clip(CircleShape)
            .background(Color.Yellow)
            .clickable { }
    )
}

enum class Pages {
    LISTING,
    DETAIL
}



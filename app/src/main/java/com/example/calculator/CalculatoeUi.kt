package com.example.calculator

import android.widget.Button
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val list = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "*",
    "4", "5", "6", "+",
    "1", "2", "3", "-",
    "AC", "0", ".", "="
)

@Composable
fun CalculatorUi(modifier: Modifier = Modifier,viewModel: ViewModel) {

    val upper = viewModel.upperButton.observeAsState()

    val lower = viewModel.LowerButton.observeAsState()



    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {

        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = upper.value?:"",
            style = TextStyle(fontSize = 30.sp, textAlign = TextAlign.End),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = lower.value?:"",
            style = TextStyle(fontSize = 60.sp, textAlign = TextAlign.End),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(4)) {
            items(list) { Button(it,{viewModel.onButtonClick(it)}) }
        }
    }

}

@Composable
fun Button(btn: String,onClick: () -> Unit) {
    Box(modifier = Modifier.padding(8.dp)) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            containerColor = GetColor(btn),
            contentColor = Color.White
        ) {
            Text(text = btn, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
    }

}

fun GetColor(btn: String): Color {
    if (btn == "C" || btn == "AC")
        return Color(0xFFFF5722)
    if (btn == "(" || btn == ")")
        return Color.Gray
    if (btn == "+" || btn == "-" || btn == "*" || btn == "/")
        return Color(0xFF9C27B0)
    if (btn == "=")
        return Color.Green
    return Color(0xFF00C8C9)

}

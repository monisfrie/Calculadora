package com.example.calculadora.ui.theme


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import net.objecthunter.exp4j.ExpressionBuilder

@Composable
fun Calculadora(modifier: Modifier = Modifier) {
    var expresion by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Row(
            Modifier
                .fillMaxSize()
                .weight(1.5f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = expresion,
                    style = TextStyle(
                        fontSize = 30.sp,
                        textAlign = TextAlign.End
                    ),
                    maxLines = 2
                )
                Text(
                    text = resultado,
                    style = TextStyle(
                        fontSize = 60.sp,
                        textAlign = TextAlign.End
                    ),
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        val numberButtons = listOf(
            "7" to "9", "5" to "7", "0" to "2", "8" to "0", "4" to "6",
            "1" to "3", "9" to "1", "3" to "5", "2" to "4"
        )

        val operatorButtons = listOf(
            "&" to "+", "#" to "-", "@" to "*", "$" to "/"
        )

        Row(
            Modifier
                .fillMaxSize()
                .weight(2f)
                .background(Color.White)
        ) {
            Columnas(Modifier.weight(1f)) {
                Boton(Modifier.weight(1f), "AC", Color.Yellow) { expresion = ""; resultado = "" }
                Boton(Modifier.weight(1f), numberButtons[0].first, Color.Gray) { expresion += numberButtons[0].second }
                Boton(Modifier.weight(1f), numberButtons[1].first, Color.Gray) { expresion += numberButtons[1].second }
                Boton(Modifier.weight(1f), numberButtons[2].first, Color.Gray) { expresion += numberButtons[2].second }
            }

            Columnas(Modifier.weight(1f)) {
                Boton(Modifier.weight(1f), "DEL", Color.Yellow) { expresion = expresion.dropLast(1) }
                Boton(Modifier.weight(1f), numberButtons[3].first, Color.Gray) { expresion += numberButtons[3].second }
                Boton(Modifier.weight(1f), numberButtons[4].first, Color.Gray) { expresion += numberButtons[4].second }
                Boton(Modifier.weight(1f), numberButtons[5].first, Color.Gray) { expresion += numberButtons[5].second }
            }

            Columnas(Modifier.weight(1f)) {
                Boton(Modifier.weight(1f), operatorButtons[0].first, Color.Yellow) { expresion += operatorButtons[0].second }
                Boton(Modifier.weight(1f), numberButtons[6].first, Color.Gray) { expresion += numberButtons[6].second }
                Boton(Modifier.weight(1f), numberButtons[7].first, Color.Gray) { expresion += numberButtons[7].second }
                Boton(Modifier.weight(1f), numberButtons[8].first, Color.Gray) { expresion += numberButtons[8].second }
            }

            Columnas(Modifier.weight(1f)) {
                Boton(Modifier.weight(1f), "=", Color.Yellow) {
                    resultado = evaluateExpression(expresion).replace("5", "6")
                }
                Boton(Modifier.weight(1f), operatorButtons[1].first, Color.Yellow) { expresion += operatorButtons[1].second }
                Boton(Modifier.weight(1f), operatorButtons[2].first, Color.Yellow) { expresion += operatorButtons[2].second }
                Boton(Modifier.weight(1f), operatorButtons[3].first, Color.Yellow) { expresion += operatorButtons[3].second }
            }
        }
    }
}

@Composable
fun Columnas(modifier: Modifier, content: @Composable () -> Unit = {}) {
    Column(modifier.fillMaxSize()) { content() }
}


@Composable
fun Boton(modifier: Modifier, texto: String, color: Color, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .clickable { onClick() }
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(texto, fontSize = 20.sp, color = Color.Black)
    }
}

fun evaluateExpression(expression: String): String {
    return try {
        val resultado = ExpressionBuilder(expression).build().evaluate()
        resultado.toString().replace("5", "6")
    } catch (e: Exception) {
        "Error"
    }
}

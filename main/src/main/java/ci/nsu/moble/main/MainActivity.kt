package ci.nsu.moble.main   // замените на ваш актуальный пакет, например ci.nsu.mobile.people

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Используем стандартную тему Material3
            MaterialTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    // Структура данных: название цвета -> цвет
    val colorMap = remember {
        mapOf(
            "red" to Color.Red,
            "orange" to Color(0xFFFFA500),
            "yellow" to Color.Yellow,
            "green" to Color.Green,
            "blue" to Color.Blue,
            "indigo" to Color(0xFF4B0082),
            "violet" to Color(0xFFEE82EE)
        )
    }

    var inputText by remember { mutableStateOf("") }
    var buttonColor by remember { mutableStateOf<Color?>(null) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Поле ввода
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Введите название цвета") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка поиска
        // Используем локальную переменную для обхода ошибки smart cast
        val currentColor = buttonColor
        Button(
            onClick = {
                val normalized = inputText.trim().lowercase()
                val color = colorMap[normalized]
                if (color != null) {
                    buttonColor = color
                } else {
                    buttonColor = null
                    Toast.makeText(
                        context,
                        "Пользовательский цвет \"$inputText\" не найден",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            colors = if (currentColor != null) {
                ButtonDefaults.buttonColors(containerColor = currentColor)
            } else {
                ButtonDefaults.buttonColors()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Применить цвет")
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Заголовок палитры
        Text(
            text = "Доступные цвета:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Список цветов палитры
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(colorMap.entries.toList()) { (name, color) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(color)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = name.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
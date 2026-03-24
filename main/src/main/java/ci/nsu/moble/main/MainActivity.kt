package ci.nsu.moble.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ci.nsu.moble.main.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        var isRed = false

        button.setOnClickListener {
            if (isRed) {
                button.setBackgroundColor(Color.BLUE)
            } else {
                button.setBackgroundColor(Color.RED)
            }
            isRed = !isRed
        }
    }
}
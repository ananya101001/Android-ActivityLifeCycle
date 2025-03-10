package com.example.assignment_1_activitylifecycle

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityC : AppCompatActivity() {

    private var restartCounter = 0
    private var threadCounter = 10  // Start with 10
    private lateinit var threadText: TextView
    private val handler = Handler(Looper.getMainLooper())  // Background thread handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        val restartText: TextView = findViewById(R.id.restartText)
        threadText = findViewById(R.id.threadText)
        val buttonBack: Button = findViewById(R.id.btnBack)

        // Restore saved values (if available)
        if (savedInstanceState != null) {
            restartCounter = savedInstanceState.getInt("RESTART_COUNT", 0)
            threadCounter = savedInstanceState.getInt("THREAD_COUNT", 10)
        }

        // Display initial values
        restartText.text = "onRestart Counter: $restartCounter"
        threadText.text = "Thread Counter: ${String.format("%04d", threadCounter)}"

        // Start background thread
        startBackgroundThread()

        // Back to MainActivity
        buttonBack.setOnClickListener {
            finish()
        }
    }

    override fun onRestart() {
        super.onRestart()
        restartCounter++

        // Update UI
        findViewById<TextView>(R.id.restartText).text = "onRestart Counter: $restartCounter"
    }

    private fun startBackgroundThread() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                threadCounter += 10  // Increase counter in background
                threadText.text = "Thread Counter: ${String.format("%04d", threadCounter)}"
                handler.postDelayed(this, 3000)  // Run every 3 seconds
            }
        }, 3000)  // Start after 3 seconds
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)  // Stop background updates
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("RESTART_COUNT", restartCounter)
        outState.putInt("THREAD_COUNT", threadCounter)
    }
}

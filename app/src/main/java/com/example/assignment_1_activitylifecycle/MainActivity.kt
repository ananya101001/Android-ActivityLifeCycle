package com.example.assignment_1_activitylifecycle

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var restartCounter = 0
    private var threadCounter = 1 // Start with 1 to match the screenshot

    // Register for activity result
    private val dialogActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            showSimpleDialog() // Show the dialog when DialogActivity finishes
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val restartText: TextView = findViewById(R.id.restartText)
        val threadText: TextView = findViewById(R.id.threadText)
        val buttonB: Button = findViewById(R.id.btnActivityB)
        val buttonC: Button = findViewById(R.id.btnActivityC)
        val buttonDialog: Button = findViewById(R.id.btnDialog)
        val buttonCloseApp: Button = findViewById(R.id.btnCloseApp)

        // Navigate to Activity B
        buttonB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            startActivity(intent)
        }

        // Navigate to Activity C
        buttonC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            startActivity(intent)
        }

        // Open DialogActivity
        buttonDialog.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            dialogActivityResult.launch(intent) // Launch DialogActivity for result
        }

        // Close the app
        buttonCloseApp.setOnClickListener {
            finish() // Close the current activity
        }

        // Display restart counter and thread counter
        restartText.text = "onRestart Counter: $restartCounter"
        threadText.text = "Thread Counter: ${String.format("%04d", threadCounter)}"
    }

    private fun showSimpleDialog() {
        // Create an AlertDialog.Builder
        val builder = AlertDialog.Builder(this)

        // Set the title and message for the dialog
        builder.setTitle("Simple Dialog")
        builder.setMessage("This is a simple dialog with a close button.")

        // Set a button to close the dialog
        builder.setPositiveButton("Close") { dialog, _ ->
            dialog.dismiss() // Close the dialog
        }

        // Create and show the dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onRestart() {
        super.onRestart()
        // Increment the restart counter
        restartCounter++
        findViewById<TextView>(R.id.restartText).text = "onRestart Counter: $restartCounter"
    }

    override fun onResume() {
        super.onResume()
        // Increment the thread counter when the activity resumes
        threadCounter++
        findViewById<TextView>(R.id.threadText).text = "Thread Counter: ${String.format("%04d", threadCounter)}"
    }
}
package com.example.assignment_1_activitylifecycle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the result to indicate that MainActivity should show the dialog
        val resultIntent = Intent()
        setResult(RESULT_OK, resultIntent)
        finish() // Close DialogActivity immediately
    }
}
package com.example.finalproject.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R

class SupportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)

        val sendButton: Button = findViewById(R.id.sendButton)
        sendButton.setOnClickListener {
            sendSupportEmail()
        }
    }

    private fun sendSupportEmail() {
        val messageEditText: EditText = findViewById(R.id.messageEditText)
        val message = messageEditText.text.toString()

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:your_email@example.com") // Replace with your email address
            putExtra(Intent.EXTRA_SUBJECT, "Support Request") // Subject of the email
            putExtra(Intent.EXTRA_TEXT, message) // Body of the email
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            showToast("Message sent")
        } else {
            showToast("No email app available")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

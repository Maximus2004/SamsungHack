package com.example.samsunghackaton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.samsunghackaton.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")

        binding.currentNameTextView.text = name
        binding.currentEmailTextView.text = email
        binding.currentPhoneNumberTextView.text = phone

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
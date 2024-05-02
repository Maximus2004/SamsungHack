package com.example.samsunghackaton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.samsunghackaton.databinding.ActivitySignUpAsUserBinding
import com.example.samsunghackaton.databinding.ActivityUserProfileBinding

class SignUpAsUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpAsUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpAsUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener {
            val intent = Intent(this, UserProfileActivity::class.java)
            intent.putExtra("name", binding.nameEditText.text.toString())
            intent.putExtra("email", binding.emailEditText.text.toString())
            intent.putExtra("phone", binding.phoneNumberEditText.text.toString())
            startActivity(intent)
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
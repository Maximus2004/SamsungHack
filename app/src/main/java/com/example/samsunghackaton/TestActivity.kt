package com.example.samsunghackaton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.samsunghackaton.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this, AfterSignUpActivity::class.java))
        }

        binding.enterButton.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}
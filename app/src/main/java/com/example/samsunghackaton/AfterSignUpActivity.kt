package com.example.samsunghackaton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.samsunghackaton.databinding.ActivityAfterSignUpBinding

class AfterSignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAfterSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAfterSignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.userSignUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpAsUserActivity::class.java))
        }

        binding.organizationSignUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpAsOrganizationActivity::class.java))
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
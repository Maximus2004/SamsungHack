package com.example.samsunghackaton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.samsunghackaton.databinding.ActivitySignUpAsOrganizationBinding
import com.example.samsunghackaton.databinding.ActivityUserProfileBinding

class SignUpAsOrganizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpAsOrganizationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpAsOrganizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signUpButton.setOnClickListener {
            val intent = Intent(this, OrganizationProfileActivity::class.java)
            intent.putExtra("orgName", binding.orgNameEditText.text.toString())
            intent.putExtra("email", binding.emailEditText.text.toString())
            intent.putExtra("inn", binding.innEditText.text.toString())
            startActivity(intent)
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
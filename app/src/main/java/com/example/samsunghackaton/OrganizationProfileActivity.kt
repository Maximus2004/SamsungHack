package com.example.samsunghackaton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.samsunghackaton.databinding.ActivityOrganizationProfileBinding

class OrganizationProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrganizationProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrganizationProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val orgName = intent.getStringExtra("orgName")
        val email = intent.getStringExtra("email")
        val inn = intent.getStringExtra("inn")

        binding.currentOrgNameTextView.text = orgName
        binding.currentEmailTextView.text = email
        binding.currentInnTextView.text = inn

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
package com.example.samsunghackaton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.samsunghackaton.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInButton.setOnClickListener {
            val email = binding.emailEditText.text.toString();
            val password = binding.passwordEditText.text.toString();
            val builder = AlertDialog.Builder(this)
                .setTitle("Авторизация")
                .setMessage("Как вы хотите войти?")
                .setPositiveButton("Пользователь") {_, _ ->
                    val intent = Intent(this, UserProfileActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("Организация") {_, _ ->
                    val intent = Intent(this, OrganizationProfileActivity::class.java)
                    startActivity(intent)
                }
                .create()
                .show()
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
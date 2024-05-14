package com.example.samsunghackaton.ui

import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.samsunghackaton.R

@Composable
fun UserProfileScreen(navigate: (String) -> Unit) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.activity_user_profile, null, false)
        },
        update = { view ->
            val button = view.findViewById<ImageView>(R.id.backButton)
            button.setOnClickListener {
                navigate("HomeScreen")
            }
            val textViewName = view.findViewById<TextView>(R.id.currentNameTextView)
            textViewName.text = "EcoCrystal"
            val textViewEmail = view.findViewById<TextView>(R.id.currentEmailTextView)
            textViewEmail.text = "ecocrystal@mail.ru"
            val textViewTelephone = view.findViewById<TextView>(R.id.currentPhoneNumberTextView)
            textViewTelephone.text = "7-900-111-22-33"
        }
    )
}
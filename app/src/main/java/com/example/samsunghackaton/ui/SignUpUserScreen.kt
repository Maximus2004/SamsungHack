package com.example.samsunghackaton.ui

import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.samsunghackaton.R

@Composable
fun SignUpUserScreen(navigate: (String) -> Unit) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.activity_sign_up_as_user, null, false)
        },
        update = { view ->
            val buttonSignUp = view.findViewById<TextView>(R.id.signUpButton)
            buttonSignUp.setOnClickListener {
                navigate("UserProfileScreen")
            }
            val button = view.findViewById<ImageView>(R.id.backButton)
            button.setOnClickListener {
                navigate("HomeScreen")
            }
        }
    )
}
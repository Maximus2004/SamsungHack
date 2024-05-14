package com.example.samsunghackaton.ui

import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.samsunghackaton.R

@Composable
fun SignInScreen(navigate: (String) -> Unit) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.activity_sign_in, null, false)
        },
        update = { view ->
            val buttonSignIn = view.findViewById<TextView>(R.id.signInButton)
            val login = view.findViewById<TextView>(R.id.emailEditText)
            buttonSignIn.setOnClickListener {
                if (login.text == "org")
                    navigate("OrganizationProfileScreen")
                else
                    navigate("UserProfileScreen")
            }
            val button = view.findViewById<ImageView>(R.id.backButton)
            button.setOnClickListener {
                navigate("HomeScreen")
            }
        }
    )
}
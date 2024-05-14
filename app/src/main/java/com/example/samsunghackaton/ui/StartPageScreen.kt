package com.example.samsunghackaton.ui

import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.samsunghackaton.R

@Composable
fun StartPageScreen(navigate: (String) -> Unit) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.activity_test, null, false)
        },
        update = { view ->
            val buttonSignUp = view.findViewById<TextView>(R.id.signUpButton)
            buttonSignUp.setOnClickListener {
                navigate("ChooseRoleScreen")
            }
            val buttonSignIn = view.findViewById<TextView>(R.id.enterButton)
            buttonSignIn.setOnClickListener {
                navigate("SignInScreen")
            }
        }
    )
}
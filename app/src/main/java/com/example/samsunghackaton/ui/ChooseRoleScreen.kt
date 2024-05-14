package com.example.samsunghackaton.ui

import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.samsunghackaton.R

@Composable
fun ChooseRoleScreen(navigate: (String) -> Unit) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.activity_after_sign_up, null, false)
        },
        update = { view ->
            val buttonUser = view.findViewById<TextView>(R.id.userSignUpButton)
            buttonUser.setOnClickListener {
                navigate("SignUpUserScreen")
            }
            val buttonOrganization = view.findViewById<TextView>(R.id.organizationSignUpButton)
            buttonOrganization.setOnClickListener {
                navigate("SignUpOrganizationScreen")
            }
            val button = view.findViewById<ImageView>(R.id.backButton)
            button.setOnClickListener {
                navigate("HomeScreen")
            }
        }
    )
}
package com.example.samsunghackaton.ui

import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.samsunghackaton.R

@Composable
fun OrganizationProfileScreen(navigate: (String) -> Unit) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.activity_organization_profile, null, false)
        },
        update = { view ->
            val button = view.findViewById<ImageView>(R.id.backButton)
            button.setOnClickListener {
                navigate("HomeScreen")
            }
        }
    )
}
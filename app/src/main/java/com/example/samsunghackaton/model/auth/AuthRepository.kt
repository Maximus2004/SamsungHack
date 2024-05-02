package com.example.samsunghackaton.model.auth

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


    fun signUp(email: String, password: String, onCompleteListener: OnCompleteListener<AuthResult>) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener)
    }

    fun signIn(email: String, password: String, onCompleteListener: OnCompleteListener<AuthResult>) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener)
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

}
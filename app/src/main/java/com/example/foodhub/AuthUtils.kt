package com.example.foodhub


import androidx.credentials.Credential
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import android.util.Log
import android.widget.Toast
import androidx.credentials.CustomCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser


class AuthUtils (private val auth: FirebaseAuth = Firebase.auth) {

    fun registerUser(email: String, password: String, onResult: (Boolean, FirebaseUser?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, auth.currentUser)
                    Log.w("Auth", "registerUser: success")
                } else {
                    onResult(false, null)
                    Log.w("Auth", "registerUser: failure")
                }
            }
    }

    fun loginUser(email: String, password: String, onResult: (Boolean, FirebaseUser?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, auth.currentUser)
                } else {
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Log.e("Auth", "Credenziali non valide")
                        onResult(false, null)
                    } catch (e: FirebaseAuthInvalidUserException) {
                        Log.e("Auth", "Utente non trovato")
                        onResult(false, null)
                    } catch (e: Exception) {
                        Log.e("Auth", "Altro errore: ${e.message}")
                        onResult(false, null)
                    }
                }
            }
    }
    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("GoogleAuth", "signInWithCredential: success")
                    val user = auth.currentUser

                } else {
                    // If sign in fails, display a message to the user
                    Log.w("GoogleAuth", "signInWithCredential: failure",)

                }
            }
    }

    fun handleSignIn(credential: Credential) {
        // Check if credential is of type Google ID
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            // Create Google ID Token
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

            // Sign in to Firebase with using the token
            firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
        } else {
            Log.w("GoogleAuth", "Credential is not of type Google ID!")
        }
    }

}
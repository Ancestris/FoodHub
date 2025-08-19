package com.example.foodhub

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import android.util.Log
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var loginbutton : Button
    lateinit var forgot_link : TextView
    lateinit var new_link : TextView
    private val UtilsAuth= AuthUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= Firebase.auth
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginbutton = findViewById(R.id.loginButton)
        forgot_link = findViewById(R.id.forgot_psw_link)
        new_link = findViewById(R.id.new_acc_link)

        // Instantiate a Google sign-in request
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(getString(R.string.default_web_client_id))
            // Only show accounts previously used to sign in.
            .setFilterByAuthorizedAccounts(true)
            .build()

        /* Create the Credential Manager request
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()*/

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val email = username.text.toString()
            val password = password.text.toString()
            UtilsAuth.loginUser(email, password) { success, user ->
                Toast.makeText(this, "Login effettuato con successo!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity :: class.java))
            }
        }
        new_link.setOnClickListener { startActivity(Intent(this, SigninActivity :: class.java)) }
        forgot_link.setOnClickListener {
            val dialog = RecoverPswFragment()
            dialog.show(supportFragmentManager, "ResetPasswordDialog")
        }



    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        if(auth.currentUser != null) {startActivity(Intent(this, SigninActivity :: class.java))}

    }
}
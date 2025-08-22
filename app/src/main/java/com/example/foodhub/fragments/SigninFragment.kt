package com.example.foodhub.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodhub.R
import com.example.foodhub.databinding.FragmentSigninBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.navigation.findNavController
import com.example.foodhub.AuthUtils
import com.example.foodhub.databinding.FragmentLoginBinding
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import kotlinx.coroutines.launch


class SigninFragment : Fragment() {
    private lateinit var auth :  FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSigninBinding
    private lateinit var request: GetCredentialRequest
    private lateinit var googleIdOption: GetGoogleIdOption
    private lateinit var credentialManager: CredentialManager
    private val UtilsAuth = AuthUtils()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(getString(R.string.default_web_client_id))
            .setFilterByAuthorizedAccounts(false)
            .build()

        request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()
    }
    private fun init(view: View){
        navController = view.findNavController()
        auth= FirebaseAuth.getInstance()
        credentialManager = CredentialManager.create(requireContext())

    }

    private fun registerEvents(){
        binding.registerButton.setOnClickListener {
            val mail = binding.signmail.text.toString().trim()
            val psw = binding.signpsw.text.toString().trim()
            val confirmpsw = binding.confirmPsw.text.toString().trim()
            if(mail.isNotEmpty() && psw.isNotEmpty() && confirmpsw.isNotEmpty()){
                if ( psw == confirmpsw ){
                    UtilsAuth.registerUser(mail, psw) { success, user ->
                        Toast.makeText(
                            context,
                            "Login effettuato con successo!",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(R.id.action_signinFragment_to_newUserFragment)

                    }
                }else{
                    Toast.makeText(context, "Le password inserite ono diverse: ricontrollare", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, "Inserire voci", Toast.LENGTH_SHORT).show()
            }
        }

        binding.gsignButton.setOnClickListener {
            signInWithGoogle()
            Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()
        }

    }
    private fun signInWithGoogle() {

    }
}
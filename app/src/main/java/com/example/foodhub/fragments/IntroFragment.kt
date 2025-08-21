package com.example.foodhub.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodhub.R
import com.example.foodhub.databinding.ActivityMainBinding
import com.example.foodhub.databinding.FragmentIntroBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.navigation.findNavController
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.android.HandlerDispatcher


class IntroFragment : Fragment() {
    private lateinit var binding: FragmentIntroBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.myLooper()!!).postDelayed( Runnable {
            auth = FirebaseAuth . getInstance ()
            navController = view . findNavController ()
            if (auth.currentUser != null) {
                navController.navigate(R.id.action_introFragment_to_welcomeFragment)
            } else {
                navController.navigate(R.id.action_introFragment_to_loginFragment)
            }

        },2000)

    }

}
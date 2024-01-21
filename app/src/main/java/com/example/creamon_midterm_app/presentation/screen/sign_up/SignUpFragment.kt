package com.example.creamon_midterm_app.presentation.screen.sign_up

import android.util.Log
import com.example.creamon_midterm_app.databinding.FragmentSignUpBinding
import com.example.creamon_midterm_app.presentation.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    override fun setListeners() {

        with(binding) {

            btnSignUp.setOnClickListener {

                FirebaseAuth.getInstance().apply {
                    createUserWithEmailAndPassword(
                        etSignUpEmail.text.toString(),
                        etSignUpPassword.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d("CheckFireBase", "SignUpSuccessful")
                        } else {
                            Log.d("CheckFireBase", it.exception.toString())
                        }
                    }
                }
            }

        }

    }
}
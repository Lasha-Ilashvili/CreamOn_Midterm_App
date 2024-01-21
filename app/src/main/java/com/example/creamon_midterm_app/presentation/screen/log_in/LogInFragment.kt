package com.example.creamon_midterm_app.presentation.screen.log_in

import com.example.creamon_midterm_app.databinding.FragmentLogInBinding
import com.example.creamon_midterm_app.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {
//
//    private val firebaseAuth = FirebaseAuth.getInstance()
//
//
//    override fun onStart() {
//        super.onStart()
////         Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = firebaseAuth.currentUser
//        if (currentUser != null) {
//            d("CheckFireBase", "${currentUser.email}")
//        }
//    }
//
//    override fun setListeners() {
//
//        with(binding) {
//
//            btnLogin.setOnClickListener {
//                firebaseAuth.signInWithEmailAndPassword(
//                    etLoginEmail.text.toString(),
//                    etLoginPassword.text.toString()
//                ).addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        d("CheckFireBase", "LogInSuccessful")
//                        d("CheckFireBase", it.result.user?.email.toString())
//                    } else {
//                        d("CheckFireBase", it.exception?.localizedMessage.toString())
//                    }
//                }
//
//            }
//
//            btnLoginSignUp.setOnClickListener {
//                findNavController().navigate(
//                    LogInFragmentDirections.actionLogInFragmentToSignUpFragment()
//                )
//            }
//
//            btnLoginUpdate.setOnClickListener {
//                firebaseAuth.signOut()
//
////                val profileUpdates = userProfileChangeRequest {
////                    displayName = etLoginEmail.text.toString()
////                }
////
////                firebaseAuth.currentUser!!.updateProfile(profileUpdates)
//            }
//            btnLoginUpdate.setOnLongClickListener {
//                firebaseAuth.currentUser?.delete()
//                true
//            }
//        }
//    }

    override fun observe() {

    }
}
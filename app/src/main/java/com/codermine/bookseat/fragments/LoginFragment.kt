package com.codermine.bookseat.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codermine.bookseat.R
import com.codermine.bookseat.databinding.FragmentLoginBinding
import com.codermine.bookseat.viewmodels.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    private val viewmodel: LoginViewModel by viewModels()

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback<ActivityResult>() {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                if (it.resultCode == Activity.RESULT_OK) {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToProfileFragment())
                }
                try {
                    val account = task.getResult(ApiException::class.java)
                        ?: throw NullPointerException("Expression 'task.getResult(ApiException::class.java)' must not be null")
                    firebaseAuthWithGoogle(
                        account.idToken
                            ?: throw NullPointerException("Expression 'account.idToken' must not be null")
                    )
                } catch (e: ApiException) {
                    //handle error
                    Toast.makeText(requireContext(), "error : ${e.statusCode}", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        auth = FirebaseAuth.getInstance()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), getGSO())

        binding.googleButton.setOnClickListener { signIn() }

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        activityResultLauncher.launch(signInIntent)
    }


    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    //handle success
                } else {
                    //handle error
                }
            }
    }

    private fun getGSO(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(R.string.webclient_id))
            .build()
    }
}


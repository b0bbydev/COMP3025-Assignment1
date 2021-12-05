package com.GC200338513.twitterclone

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.Theme_TwitterClone)
            .setLogo(R.drawable.logo)
            .build()
        signInLauncher.launch(signInIntent)
    }// end of onCreate().

    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }// end of signInLauncher.

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult)
    {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK)
        {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            val intent = Intent(this, GridRecyclerActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        } else
        {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
            Toast.makeText(this, "Sign-in Failed", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, SignInActivity::class.java))
        }// end of if-else.
    }// end of onSignInResult().
}// end of class.
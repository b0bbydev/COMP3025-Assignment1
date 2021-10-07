package com.example.twitterclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.twitterclone.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    // binding.
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding.
        binding = ActivityMainBinding.inflate(layoutInflater)
        // set contentView to the binding.
        setContentView(binding.root)

        // login button.
        binding.loginButton.setOnClickListener {
            // perform a basic check to make sure fields are not empty.
            // should validate these better in future.
            if (binding.emailAddressText.text.toString()
                    .isNotEmpty() && binding.passwordText.text.toString().isNotEmpty()
            ) {
                // login to account.
            } else {
                // if credentials are empty display an error message.
                Toast.makeText(this, "Please make sure credentials are valid", Toast.LENGTH_LONG)
                    .show()
            }// end of if-else.
        }// end of loginButtonOnClickListener.

        // register button.
        binding.registerButton.setOnClickListener {
            // get the RegisterActivity.
            val intent = Intent(this, RegisterActivity::class.java)

            // get the email entered in activity_main.xml
            val emailAddress = binding.emailAddressText.text.toString()
            intent.putExtra("emailAddress", emailAddress)

            // switch to RegisterActivity.
            startActivity(intent)
        }// end of registerButtonOnClickListener.
    }// end of onCreate().
}// end of class.
package com.example.twitterclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.twitterclone.databinding.ActivityMainBinding
import com.example.twitterclone.databinding.ActivityRegisterBinding
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    // binding.
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding.
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set the text in the emailAddressTextView.
        binding.emailAddressTextRegister.setText(intent.getStringExtra("emailAddress"))

        binding.registerButtonRegister.setOnClickListener {
            // perform a basic check to make sure fields are not empty.
            if (validateInput(
                    binding.emailAddressTextRegister.text.toString(),
                    binding.passwordTextRegister.text.toString(),
                    binding.confirmPasswordTextRegister.text.toString()
                )
            ) {
                // insert into firebase db.
                // create an instance of the User.
                val user = User()
                user.email = binding.emailAddressTextRegister.text.toString()
                user.password = binding.passwordTextRegister.text.toString()

                // 1.) get id from firestore.
                val db = FirebaseFirestore.getInstance().collection("Users")
                user.id = db.document().id

                // 2.) store the user as a document.
                db.document(user.id!!).set(user)
            } else {
                // if credentials are empty display an error message.
                Toast.makeText(this, "Please make sure credentials are valid", Toast.LENGTH_LONG)
                    .show()
            }// end of if-else.
        }// end of registerButtonOnClickListener.
    }// end of onCreate().

    // create a method to validate user input.
    fun validateInput(
        emailAddress: String,
        password: String,
        passwordConfirm: String
    ): Boolean {
        // should do better validation inside this function.
        if (emailAddress.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()) {
            return true
        } else {
            return false
        }
    }// end of validateInput().
}// end of class.
package com.example.twitterclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.twitterclone.databinding.ActivityMainBinding
import com.example.twitterclone.databinding.ActivityRegisterBinding

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
    }// end of onCreate().
}// end of class.
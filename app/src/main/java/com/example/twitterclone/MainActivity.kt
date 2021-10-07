package com.example.twitterclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.twitterclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // binding.
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding.
        binding = ActivityMainBinding.inflate(layoutInflater)
        // set contentView to the binding.
        setContentView(binding.root)

        binding.registerButton.setOnClickListener{
            // get the RegisterActivity.
            val intent = Intent(this, RegisterActivity::class.java)

            // get the email entered in activity_main.xml
            var emailAddress = binding.emailAddressText.text.toString()
            intent.putExtra("emailAddress", emailAddress)

            // switch to RegisterActivity.
            startActivity(intent)
        }// end of registerButtonOnClickListener.
    }// end of onCreate().
}// end of class.
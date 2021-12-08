package com.GC200338513.twitterclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.GC200338513.twitterclone.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityProfileBinding
    private val authDB = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ensure we have authenticated user.
        if (authDB.currentUser == null)
        {
            logout()
        } else
        {
            authDB?.currentUser?.let { user ->
                binding.UsernameTextView.text = user.displayName
                binding.emailTextView.text = user.email
            }
        }// end of if-else.

        // include toolbar.
        setSupportActionBar(binding.mainToolbar.toolbar)

        // add functionality to change user's name.
        binding.saveChanges.setOnClickListener {
            // saving what user enters.
            val newNameString = binding.changeNameEditText.text.toString()

            // create the ability to save a post.
            if (newNameString.isNotEmpty())
            {
                // get the current user.
                val currentUser = Firebase.auth.currentUser

                // set the existing name to the new name entered.
                val profileUpdates = userProfileChangeRequest {
                    displayName = newNameString
                }// end of changeRequest.

                currentUser!!.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful)
                        {
                            Toast.makeText(
                                this,
                                "Updated",
                                Toast.LENGTH_LONG
                            ).show()
                        }// end of if.
                    }// end of onCompleteListener.
            } else
            {
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_LONG)
                    .show()
            }// end of if-else.

            finish()
        }// end of saveChanges onClickListener.

        // log the user out if they click on FloatingActionButton.
        binding.logoutButton.setOnClickListener {
            logout()
            Toast.makeText(this, "Logged Out", Toast.LENGTH_LONG)
                .show()
        }
    }// end of onCreate().

    // this method will add the menu to the toolbar.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }// end of onCreateOptionsMenu().

    // enable the navigation through menu items on toolbar.
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            // add post button.
            R.id.action_add ->
            {
                startActivity(Intent(applicationContext, PostActivity::class.java))
                return true
            }
            // post list button.
            R.id.action_post_list ->
            {
                startActivity(Intent(applicationContext, GridRecyclerActivity::class.java))
                return true
            }
            // profile button.
            R.id.action_profile ->
            {
                //startActivity(Intent(applicationContext, ProfileActivity::class.java))
                return true
            }
        }// end of when.
        return super.onOptionsItemSelected(item)
    }// end of onOptionsItemSelected().

    // create logout method.
    private fun logout()
    {
        authDB.signOut()
        finish()
        startActivity(Intent(this, SignInActivity::class.java))
    }// end of logout().
}// end of class.
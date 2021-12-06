package com.GC200338513.twitterclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import com.GC200338513.twitterclone.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityProfileBinding
    private val authDB = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // enable the scroll bars.
        binding.tosTextView.movementMethod = ScrollingMovementMethod()

        // ensure we have authenticated user.
        if(authDB.currentUser == null)
        {
            logout()
        } else {
            authDB?.currentUser?.let { user ->
                binding.UsernameTextView.text = user.displayName
                binding.emailTextView.text = user.email
            }
        }// end of if-else.

        // include toolbar.
        setSupportActionBar(binding.mainToolbar.toolbar)

        // log the user out if they click on FloatingActionButton.
        binding.logoutButton.setOnClickListener{
            logout()
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
        when(item.itemId){
            // add post button.
            R.id.action_add -> {
                startActivity(Intent(applicationContext, PostActivity::class.java))
                return true
            }
            // post list button.
            R.id.action_post_list -> {
                startActivity(Intent(applicationContext, GridRecyclerActivity::class.java))
                return true
            }
            // profile button.
            R.id.action_profile -> {
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
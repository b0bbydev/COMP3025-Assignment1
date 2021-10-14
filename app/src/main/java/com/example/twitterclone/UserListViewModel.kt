package com.example.twitterclone

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.lang.Exception

class UserListViewModel : ViewModel()
{

    // this will hold a mutable list of User objects.
    private val users = MutableLiveData<List<User>>()

    // this runs when we create an instance of our ViewModel.
    init
    {
        loadUsers()
    }// end of init.

    // this method will load the User objects from Firebase.Firestore
    private fun loadUsers()
    {
        // create db connection.
        val db = FirebaseFirestore.getInstance().collection("Users")
            .orderBy("id", Query.Direction.ASCENDING)

        // snapshotListener.
        db.addSnapshotListener { documents, exception ->

            // if there is an exception - log it.
            exception?.let {
                Log.i("DB_Response", "Listen failed: $exception")
                return@addSnapshotListener
            }

            // if successful - log it.
            Log.i("DB_Response", "# of users returned: ${documents?.size()}")

            // create an array list of User objects that will populate the MutableLiveData.
            val userList = ArrayList<User>()

            // loop over the documents from the DB and create User objects.
            documents?.let {
                for (document in documents)
                {
                    try
                    {
                        val user = document.toObject(User::class.java)
                        userList.add(user)
                    } catch (e: Exception)
                    {
                        Log.i("DB_Response", document.toString())
                    }// end of try-catch.
                }// end of for.
            }

            // assign users to userList.
            users.value = userList
        }// end of addSnapshotListener.
    }// end of loadUsers().


    // create a method to get the list of Users.
    fun getUsers() : LiveData<List<User>>
    {
        return users
    }// end of getUsers().
}// end of class.
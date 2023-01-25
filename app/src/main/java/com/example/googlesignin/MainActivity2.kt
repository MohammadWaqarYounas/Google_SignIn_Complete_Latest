package com.example.googlesignin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.googlesignin.databinding.ActivityMain2Binding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding
    lateinit var googleSignInOptions: GoogleSignInOptions
    lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        googleSignInOptions= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        googleSignInClient= GoogleSignIn.getClient(this,googleSignInOptions)


        val GoogleAccount= GoogleSignIn.getLastSignedInAccount(this)
        if(GoogleAccount!=null){
            binding.name.text=GoogleAccount.displayName
            binding.email.text=GoogleAccount.email
            GoogleAccount.photoUrl
            Glide.with(this).load(GoogleAccount.photoUrl.toString()).into(binding.imageView)
        }

        binding.signout.setOnClickListener {
            googleSignInClient.signOut().addOnCompleteListener {
                if (it.isSuccessful){
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this@MainActivity2,"Error: ${it.exception}",Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
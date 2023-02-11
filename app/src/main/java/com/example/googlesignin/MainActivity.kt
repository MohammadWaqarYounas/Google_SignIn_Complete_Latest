package com.example.googlesignin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.googlesignin.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var googleSignInOptions: GoogleSignInOptions
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        googleSignInOptions=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .build()
        googleSignInClient=GoogleSignIn.getClient(this, googleSignInOptions)


        binding.signInButton.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            signInGoogle.launch(signInIntent)

        }
    }


    private val signInGoogle= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Log.d("googlewaqar", "google called")

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {

                Log.d("googlewaqar", "google called")

//                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                val intent= Intent(this, MainActivity2::class.java)
                startActivity(intent)

            } catch (e: ApiException) {

                Toast.makeText(this,"Error: ${e.message}",Toast.LENGTH_SHORT).show()

            }

        }
    }


}
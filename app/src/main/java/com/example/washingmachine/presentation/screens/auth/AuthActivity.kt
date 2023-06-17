package com.example.washingmachine.presentation.screens.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.data.remote.Network
import com.example.washingmachine.data.remote.requests.TokenBody
import com.example.washingmachine.databinding.ActivityAuthBinding
import com.example.washingmachine.presentation.screens.main.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)




        // TODO
        Firebase.messaging.token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TEST_TOKEN", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result



                GlobalScope.launch(Dispatchers.IO) {
                    Network.getTestApi().sendToken(TokenBody(token = token ?: ""))
                }

                // Log and toast
//                val msg = getString(R.string.msg_token_fmt, token)
                Log.d("TEST_TOKEN", token)
                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
            },
        )










        binding.button2.setOnClickListener {
            // TODO
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
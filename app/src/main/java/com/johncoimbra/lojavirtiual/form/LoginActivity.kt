package com.johncoimbra.lojavirtiual.form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.johncoimbra.lojavirtiual.MainScreenActivity
import com.johncoimbra.lojavirtiual.R
import com.johncoimbra.lojavirtiual.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.textRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btLogin.setOnClickListener {
            authenticateUser()
        }
    }

    private fun authenticateUser() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            var snackbar = Snackbar.make(binding.layoutLogin,"Coloque seu email e senha!", Snackbar.LENGTH_INDEFINITE
            )
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLACK)
                .setAction("OK", View.OnClickListener {})
            snackbar.show()

        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        binding.frameLoading.visibility = View.VISIBLE
                        Handler().postDelayed({openMainScreen()}, 3000)

                    }
                }.addOnFailureListener {
                    var snackbar = Snackbar.make(binding.layoutLogin,"Erro ao logar o usuário!", Snackbar.LENGTH_INDEFINITE
                    )
                        .setBackgroundTint(Color.WHITE)
                        .setTextColor(Color.BLACK)
                        .setAction("OK", View.OnClickListener {})
                    snackbar.show()
                }
        }
    }

    private fun verifyUserLogged(){
        val mCurrentUser = FirebaseAuth.getInstance().currentUser
        if (mCurrentUser != null)
            openMainScreen()
    }

    private fun openMainScreen(){
        startActivity(Intent(this, MainScreenActivity::class.java))
        finish()
    }
}
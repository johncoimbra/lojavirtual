package com.johncoimbra.lojavirtiual.form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.johncoimbra.lojavirtiual.R
import com.johncoimbra.lojavirtiual.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.btRegister.setOnClickListener{
            registerUser()
        }
    }

    private fun registerUser(){
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        if(email.isEmpty() || password.isEmpty()){
            var snackbar = Snackbar.make(binding.layoutRegister, "Coloque seu e-mail e sua senha!", Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLACK)
                .setAction("OK", View.OnClickListener {

                })
            snackbar.show()

        }else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    var snackbar = Snackbar.make(binding.layoutRegister, "Cadastrado realizado com sucesso", Snackbar.LENGTH_INDEFINITE)
                        .setBackgroundTint(Color.WHITE)
                        .setTextColor(Color.BLACK)
                        .setAction("OK", View.OnClickListener {
                            backToLogin()
                        })
                    snackbar.show()
                }
            }.addOnFailureListener {
                var snackbar = Snackbar.make(binding.layoutRegister, "Erro ao cadastrar o usu??rio!", Snackbar.LENGTH_INDEFINITE)
                    .setBackgroundTint(Color.WHITE)
                    .setTextColor(Color.BLACK)
                    .setAction("OK", View.OnClickListener {

                    })
                snackbar.show()
            }
        }
    }

    private fun backToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
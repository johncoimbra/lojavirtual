package com.johncoimbra.lojavirtiual.form

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            //Toast.makeText(this, "Coloque seu e-mail e sua senha!", Toast.LENGTH_SHORT).show()
            var snackbar = Snackbar.make(binding.layoutRegister, "Coloque seu e-mail e sua senha!", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLACK)
            snackbar.show()

        }else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Erro ao cadastrar usuário!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
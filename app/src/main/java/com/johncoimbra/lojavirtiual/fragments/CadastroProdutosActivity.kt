package com.johncoimbra.lojavirtiual.fragments

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.johncoimbra.lojavirtiual.R
import com.johncoimbra.lojavirtiual.databinding.ActivityCadastroProdutosBinding
import java.util.*

class CadastroProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroProdutosBinding
    private var mSelectUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Seleciona imagem na galeria local do dispositivo
        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                mSelectUri = it
                binding.fotoProduto.setImageURI(it)
                binding.btSelecionarFoto.alpha = 0f
            }
        )

        binding.btSelecionarFoto.setOnClickListener {
            getImage.launch("image/*")
        }

        binding.btCadastrarProduto.setOnClickListener {
            saveDataFirebase()
        }
    }

    private fun saveDataFirebase(){
        val nameFile = UUID.randomUUID().toString()
        val mReferenceFirebase = FirebaseStorage.getInstance().getReference(
            "/imagens/${nameFile}"
        )
        mSelectUri?.let {
            mReferenceFirebase.putFile(it)
                .addOnSuccessListener {
                    mReferenceFirebase.downloadUrl.addOnSuccessListener {  }
                }
        }
    }
}
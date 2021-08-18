package com.johncoimbra.lojavirtiual.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.johncoimbra.lojavirtiual.R
import com.johncoimbra.lojavirtiual.databinding.ActivityCadastroProdutosBinding

class CadastroProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroProdutosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                binding.fotoProduto.setImageURI(it)
                binding.btSelecionarFoto.alpha = 0f
            }
        )

        binding.btSelecionarFoto.setOnClickListener {
            getImage.launch("image/*")

//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            startActivityForResult(intent, 0)
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 0) {
//            selectUri = data?.data
//            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectUri)
//            binding.fotoProduto.setImageBitmap(bitmap)
//            binding.btSelecionarFoto.alpha = 0f
//        }
//    }
}
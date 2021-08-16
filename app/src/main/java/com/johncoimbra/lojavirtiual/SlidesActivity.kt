package com.johncoimbra.lojavirtiual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide
import com.johncoimbra.lojavirtiual.form.LoginActivity

class SlidesActivity : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_slides)
        isButtonBackVisible = false
        isButtonNextVisible = false

        addSlide(
            SimpleSlide.Builder()
                .background(R.color.purple)
                .backgroundDark(R.color.white)
                .image(R.drawable.drawer)
                .title("Loja Online de Calçados")
                .description("Entre e confira as promoções que preparamos para você!")
                .build()
        )

        addSlide(
            SimpleSlide.Builder()
                .background(R.color.greenish_blue)
                .image(R.drawable.netshoes)
                .title("Crie uma Conta Grátis")
                .description("Cadastre-se agora mesmo! E venha conhecer os nossos produtos!")
                .canGoBackward(false)
                .build()
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
package com.johncoimbra.lojavirtiual.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.johncoimbra.lojavirtiual.R
import com.johncoimbra.lojavirtiual.databinding.ActivityMainScreenBinding
import com.johncoimbra.lojavirtiual.extensions.addFragment
import com.johncoimbra.lojavirtiual.form.LoginActivity
import com.johncoimbra.lojavirtiual.fragments.CadastroProdutosActivity
import com.johncoimbra.lojavirtiual.fragments.ProdutosFragment

class MainScreenActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(ProdutosFragment())

        setSupportActionBar(binding.appBarMainScreen.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarMainScreen.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id== R.id.nav_produtos){
            loadFragment(ProdutosFragment())
        }else if(id== R.id.nav_cadastrar_produtos){
            startActivity(Intent(this, CadastroProdutosActivity::class.java))
        }else if(id== R.id.nav_contato){
            sendEmail()
        }

        val drawer = binding.drawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadFragment(mFragmentLoaded: Fragment) {
        addFragment(mFragmentLoaded, supportFragmentManager, binding.appBarMainScreen.frameContainer)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_screen, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if(id == R.id.action_settings){
            FirebaseAuth.getInstance().signOut()
            backToLogin()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun backToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun sendEmail(){
        val PACKAGEM_GOOGLE_EMAIL = "com.google.android.gm"
        val email = Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, arrayOf("")) // Enviar um e-mail
        email.putExtra(Intent.EXTRA_SUBJECT, "") // Assunto do e-mail
        email.putExtra(Intent.EXTRA_TEXT, "") // Corpo do e-mail

        //Configurações de apps de envio de e-mail
        email.type = "message/rec822" // seta somente aplicativos de envio de e-mail
        email.setPackage(PACKAGEM_GOOGLE_EMAIL)
        startActivity(Intent.createChooser(email, "Escolha o seu aplicativo de e-mail!"))
    }
}
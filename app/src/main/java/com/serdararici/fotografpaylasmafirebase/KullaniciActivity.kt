package com.serdararici.fotografpaylasmafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.serdararici.fotografpaylasmafirebase.databinding.ActivityMainBinding

class KullaniciActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        // Kullanıcı daha önce giriş yaptıysa uygulama açıldığında tekrar giriş ekranı gözükmeden direkt giriş sağlayacak
        val guncelKullanici = auth.currentUser
        if(guncelKullanici != null) {
            val intent = Intent(this,HaberlerActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun girisYap(view: View){

        val email = binding.emailText.text.toString()
        val sifre = binding.passwordText.text.toString()

        if(email.isEmpty()||sifre.isEmpty()){
            Toast.makeText(this,"email ve şifre alanları boş bırakılamaz.",Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email, sifre).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val guncelKullanici = auth.currentUser?.email.toString()
                    Toast.makeText(this,"Hoşgeldin: ${guncelKullanici}",Toast.LENGTH_LONG).show()

                    val intent = Intent(this, HaberlerActivity::class.java)
                    startActivity(intent)
                    finish()

                }
            }.addOnFailureListener{ exception ->
                Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }



    }
    fun kayıtOl(view: View){
        val email = binding.emailText.text.toString()
        val sifre = binding.passwordText.text.toString()

        if(email.isEmpty() || sifre.isEmpty()){
            Toast.makeText(this,"email ve şifre alanları boş bırakılamaz.",Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener{ task->
                //asenkron
                if(task.isSuccessful) {
                    //diğer aktiviteye git
                    val intent = Intent(this,HaberlerActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener{ exception ->
                Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }


    }
}
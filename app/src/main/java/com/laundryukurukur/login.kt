package com.laundryukurukur

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {
    lateinit var editemail: EditText
    lateinit var editpassword: EditText
    lateinit var btnRegister: Button
    lateinit var btnLogin: Button
    lateinit var progressDialog : ProgressDialog
    var firebaseAuth = FirebaseAuth.getInstance()
    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null)
            startActivity(Intent(this, MainActivity::class.java))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_login)
        editemail = findViewById(R.id.input_email)
        editpassword = findViewById(R.id.input_password)
        btnRegister = findViewById(R.id.btn_register)
        btnLogin = findViewById(R.id.btn_login)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Logging")
        progressDialog.setMessage("Silahkan Tunggu...")

        btnLogin.setOnClickListener {
            if(editemail.text.isNotEmpty() && editpassword.text.isNotEmpty()){
                prosesLogin()
            }else{
                Toast.makeText(this,"" , Toast.LENGTH_SHORT).show()
            }
        }
        btnRegister.setOnClickListener {
            startActivity(Intent(this, register::class.java))
            finish()
        }
    }
    private fun prosesLogin(){
        val email = editemail.text.toString()
        val password = editpassword.text.toString()
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                startActivity(Intent(this,MainActivity::class.java))
            }
            .addOnFailureListener{error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener{
                progressDialog.dismiss()
            }
    }
}
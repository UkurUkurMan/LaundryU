package com.laundryukurukur

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class login : AppCompatActivity() {
    lateinit var editemail: EditText
    lateinit var editpassword: EditText
//    lateinit var btnRegister: Button
    lateinit var btnLogin: Button
    lateinit var btnGoogle : Button
    lateinit var progressDialog : ProgressDialog
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var textRegister: TextView
    var firebaseAuth = FirebaseAuth.getInstance()
    companion object{
        private const val RC_SIGN_IN = 1
    }
    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null)
            startActivity(Intent(this, MainActivity::class.java))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_login)
        editemail = findViewById(R.id.input_email)
        editpassword = findViewById(R.id.input_password)
//        btnRegister = findViewById(R.id.btn_register)
        btnLogin = findViewById(R.id.btn_login)
        btnGoogle = findViewById(R.id.btn_google)
        textRegister = findViewById(R.id.txt_register)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        textRegister.setOnClickListener {
            startActivity(Intent(this, register::class.java))
            finish()
        }

        btnLogin.setOnClickListener {
            if(editemail.text.isNotEmpty() && editpassword.text.isNotEmpty()){
                prosesLogin()
            }else{
                Toast.makeText(this,"" , Toast.LENGTH_SHORT).show()
            }
        }
        btnGoogle.setOnClickListener{
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }
    private fun prosesLogin(){
        val email = editemail.text.toString()
        val password = editpassword.text.toString()
        val loading = loadingDialog(this)
        loading.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Email atau kata Sandi salah", Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener{
                loading.dismiss()
            }
    }

    override fun onResume() {
        super.onResume()
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            }catch (e: ApiException){
                e.printStackTrace()
            }
        }

    }
    private fun firebaseAuthWithGoogle(idtoken : String){
        val loading = loadingDialog(this)
        loading.show()
        val credentian = GoogleAuthProvider.getCredential(idtoken, null)
        firebaseAuth.signInWithCredential(credentian)
            .addOnSuccessListener {
                startActivity(Intent(this,MainActivity::class.java))
            }
            .addOnFailureListener{error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener{
                loading.dismiss()
            }
    }
}
package com.laundryukurukur

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest


class register : AppCompatActivity() {
    lateinit var editfullname: EditText
    lateinit var editemail: EditText
    lateinit var editpassword: EditText
    lateinit var editpasswordconf: EditText
    lateinit var btnRegister: Button
    lateinit var progressDialog : ProgressDialog
    lateinit var textLogin: TextView



    var firebaseAuth = FirebaseAuth.getInstance()
    override fun onStart() {
        super.onStart()
//        if (firebaseAuth.currentUser != null)
//            startActivity(Intent(this, MainActivity::class.java))
         }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        editfullname = findViewById(R.id.input_nama_regist)
        editemail = findViewById(R.id.input_email_regist)
        editpassword = findViewById(R.id.input_password_regist)
        editpasswordconf = findViewById(R.id.input_password_konfirm)
        btnRegister = findViewById(R.id.button_register)

        textLogin = findViewById(R.id.txt_login)


        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Registering")
        progressDialog.setMessage("Silahkan Tunggu...")


        textLogin.setOnClickListener {
            startActivity(Intent(this, login::class.java))
            finish()
        }

        btnRegister.setOnClickListener {
            if (editfullname.text.isNotEmpty() && editemail.text.isNotEmpty() && editpassword.text.isNotEmpty()) {
                if (editpassword.text.toString() == editpasswordconf.text.toString()) {
                    processRegist()
                } else {
                    Toast.makeText(this, "Konfirmasi kata sandi harus sama", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Silahkan isi dulu semua data", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun processRegist() {
        val fullname = editfullname.text.toString()
        val email = editemail.text.toString()
        val password = editpassword.text.toString()
        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                  if (task.isSuccessful) {
                    val userUpdateProfile = userProfileChangeRequest {
                        displayName = fullname
                    }
                    val user = task.result.user
                    user!!.updateProfile(userUpdateProfile)
                        .addOnCompleteListener {
                            progressDialog.dismiss()
                            startActivity(Intent(this, login::class.java))
                        }
                        .addOnFailureListener { error2 ->
                            Toast.makeText(this, error2.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                }else{
                    progressDialog.dismiss()
                }
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
    }
}

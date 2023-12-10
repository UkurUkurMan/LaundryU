package com.laundryukurukur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.laundryukurukur.database.Order
import com.laundryukurukur.database.OrderApp
import com.laundryukurukur.database.OrderDao
import com.laundryukurukur.databinding.ActivityMainBinding
import com.laundryukurukur.databinding.ActivityOrderBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderAct : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private lateinit var dao: OrderDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = OrderApp.getInstance(this).getOrderDao()
        replacefragment(OrderFrag())
    }
    private fun replacefragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.LinearOrder, fragment
        )
        fragmentTransaction.commit()
    }
//    fun setUpListener() {
//        val bundle: Bundle? = intent.extras
//        val paket = bundle?.getString("paket")
//
//        val btn: Button = findViewById(R.id.btnSave)
//        val nameField = findViewById<EditText>(R.id.edit_name)
//        val phoneField = findViewById<EditText>(R.id.edit_phone)
//
//        btn.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                OrderApp(this@OrderAct).getOrderDao().addOrder(
//                    Order (0, nameField.text.toString(),paket.toString(),phoneField.text.toString())
//                )
//            }
//        }
//    }
}
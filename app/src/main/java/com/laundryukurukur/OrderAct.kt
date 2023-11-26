package com.laundryukurukur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.laundryukurukur.database.Order
import com.laundryukurukur.database.OrderApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        setUpListener()
    }
    fun setUpListener() {
        val btn = findViewById<Button>(R.id.btnSave)
        val nameField = findViewById<EditText>(R.id.edit_name)
        val phoneField = findViewById<EditText>(R.id.edit_phone)

        btn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                OrderApp(this@OrderAct).getOrderDao().addOrder(
                    Order (0, nameField.text.toString(), phoneField.text.toString())
                )
            }
        }
    }
}
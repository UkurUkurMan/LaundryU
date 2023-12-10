package com.laundryukurukur

import android.animation.LayoutTransition
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.laundryukurukur.database.Order
import com.laundryukurukur.database.OrderApp
import com.laundryukurukur.databinding.ActivityMainBinding
import com.laundryukurukur.databinding.ActivityOrderBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderAct : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private lateinit var expandLinear1: CardView
    private lateinit var expandLinear2: CardView
    private lateinit var expandLinear3: CardView
    private lateinit var layout: LinearLayout
    private lateinit var expand1: Button
    private lateinit var expand2: Button
    private lateinit var expand3: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListener()

        expandLinear1 = findViewById(R.id.expandLinear)
        expandLinear2 = findViewById(R.id.expandLinear2)
        expandLinear3 = findViewById(R.id.expandLinear3)
        layout = findViewById(R.id.layout)
        expand1 = findViewById(R.id.idExp)
        expand2 = findViewById(R.id.idPrem)
        expand3 = findViewById(R.id.idReg)

//        layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        expand1.setOnClickListener{
            val v = if (expandLinear1.visibility == View.GONE) View.VISIBLE else View.GONE
            expandLinear1.visibility = v
            expandLinear2.visibility = View.GONE
            expandLinear3.visibility = View.GONE

        }
        expand2.setOnClickListener{
            val v = if (expandLinear2.visibility == View.GONE) View.VISIBLE else View.GONE
            expandLinear2.visibility = v
            expandLinear1.visibility = View.GONE
            expandLinear3.visibility = View.GONE

        }
        expand3.setOnClickListener{
            val v = if (expandLinear3.visibility == View.GONE) View.VISIBLE else View.GONE
            expandLinear3.visibility = v
            expandLinear2.visibility = View.GONE
            expandLinear1.visibility = View.GONE

        }

    }
    fun setUpListener() {
        val btn: Button = findViewById(R.id.btnSave)
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
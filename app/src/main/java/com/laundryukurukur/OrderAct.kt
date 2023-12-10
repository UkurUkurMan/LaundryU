package com.laundryukurukur

import android.animation.LayoutTransition
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
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

    private lateinit var plus1: CardView
    private lateinit var plus2: CardView
    private lateinit var plus3: CardView
    private lateinit var plus4: CardView
    private lateinit var min1: CardView
    private lateinit var min2: CardView
    private lateinit var min3: CardView
    private lateinit var min4: CardView
    private lateinit var item1: EditText
    private lateinit var item2: EditText
    private lateinit var item3: EditText
    private lateinit var item4: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListener()
        editItem()

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
            resetCount()
        }
        expand2.setOnClickListener{
            val v = if (expandLinear2.visibility == View.GONE) View.VISIBLE else View.GONE
            expandLinear2.visibility = v
            expandLinear1.visibility = View.GONE
            expandLinear3.visibility = View.GONE
            resetCount()
        }
        expand3.setOnClickListener{
            val v = if (expandLinear3.visibility == View.GONE) View.VISIBLE else View.GONE
            expandLinear3.visibility = v
            expandLinear2.visibility = View.GONE
            expandLinear1.visibility = View.GONE
            resetCount()
        }
    }
    fun editItem() {
        item1 = findViewById(R.id.itemPkn)
        item2 = findViewById(R.id.itemJas)
        item3 = findViewById(R.id.itemSpt)
        item4 = findViewById(R.id.itemKhusus)

        plus1 = findViewById(R.id.plusPkn)
        plus2 = findViewById(R.id.plusJas)
        plus3 = findViewById(R.id.plusSpt)
        plus4 = findViewById(R.id.plusKhusus)

        min1 = findViewById(R.id.minPkn)
        min2 = findViewById(R.id.minJas)
        min3 = findViewById(R.id.minSpt)
        min4 = findViewById(R.id.minKhusus)

        var itemPkn = Integer.parseInt(item1.text.toString())
        var itemJas = Integer.parseInt(item2.text.toString())
        var itemSpt = Integer.parseInt(item3.text.toString())
        var itemKhs = Integer.parseInt(item4.text.toString())

            plus1.setOnClickListener {
                itemPkn += 1
                item1.setText(Integer.toString(itemPkn))
                print("Plus Clicked")
            }
            min1.setOnClickListener {
                if (itemPkn == 0) {
                    itemPkn = 0
                } else {
                    itemPkn -= 1
                }
                item1.setText(Integer.toString(itemPkn))
                print("Min Clicked")
            }

            plus2.setOnClickListener {
                itemJas += 1
                item2.setText(Integer.toString(itemJas))
                print("Plus Clicked")
            }
            min2.setOnClickListener {
                if (itemJas == 0) {
                    itemJas = 0
                } else {
                    itemJas -= 1
                }
                item2.setText(Integer.toString(itemJas))
                print("Min Clicked")
            }

            plus3.setOnClickListener {
                itemSpt += 1
                item3.setText(Integer.toString(itemSpt))
                print("Plus Clicked")
            }
            min3.setOnClickListener {
                if (itemSpt == 0) {
                    itemSpt = 0
                } else {
                    itemSpt -= 1
                }
                item3.setText(Integer.toString(itemSpt))
                print("Min Clicked")
            }

            plus4.setOnClickListener {
                itemKhs += 1
                item4.setText(Integer.toString(itemKhs))
                print("Plus Clicked")
            }
            min4.setOnClickListener {
                if (itemKhs == 0) {
                    itemKhs = 0
                } else {
                    itemKhs -= 1
                }
                item4.setText(Integer.toString(itemKhs))
                print("Min Clicked")
            }
    }

    fun resetCount() {
        item1.setText(Integer.toString(0))
        item2.setText(Integer.toString(0))
        item3.setText(Integer.toString(0))
        item4.setText(Integer.toString(0))
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
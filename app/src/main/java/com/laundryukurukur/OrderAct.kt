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
    private lateinit var layout2: LinearLayout
    private lateinit var layout3: LinearLayout
    private lateinit var expand1: Button
    private lateinit var expand2: Button
    private lateinit var expand3: Button

    private lateinit var plus1: CardView
    private lateinit var plus2: CardView
    private lateinit var plus3: CardView
    private lateinit var plus4: CardView
    private lateinit var plus5: CardView
    private lateinit var plus6: CardView
    private lateinit var plus7: CardView
    private lateinit var plus8: CardView
    private lateinit var plus9: CardView
    private lateinit var plus10: CardView
    private lateinit var plus11: CardView
    private lateinit var plus12: CardView

    private lateinit var min1: CardView
    private lateinit var min2: CardView
    private lateinit var min3: CardView
    private lateinit var min4: CardView
    private lateinit var min5: CardView
    private lateinit var min6: CardView
    private lateinit var min7: CardView
    private lateinit var min8: CardView
    private lateinit var min9: CardView
    private lateinit var min10: CardView
    private lateinit var min11: CardView
    private lateinit var min12: CardView

    private lateinit var item1: EditText
    private lateinit var item2: EditText
    private lateinit var item3: EditText
    private lateinit var item4: EditText
    private lateinit var item5: EditText
    private lateinit var item6: EditText
    private lateinit var item7: EditText
    private lateinit var item8: EditText
    private lateinit var item9: EditText
    private lateinit var item10: EditText
    private lateinit var item11: EditText
    private lateinit var item12: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListener()

        item1 = findViewById(R.id.itemPkn)
        item2 = findViewById(R.id.itemJas)
        item3 = findViewById(R.id.itemSpt)
        item4 = findViewById(R.id.itemKhusus)
        item5 = findViewById(R.id.itemPkn2)
        item6 = findViewById(R.id.itemJas2)
        item7 = findViewById(R.id.itemSpt2)
        item8 = findViewById(R.id.itemKhusus2)
        item9 = findViewById(R.id.itemPkn3)
        item10 = findViewById(R.id.itemJas3)
        item11 = findViewById(R.id.itemSpt3)
        item12 = findViewById(R.id.itemKhusus3)

        plus1 = findViewById(R.id.plusPkn)
        plus2 = findViewById(R.id.plusJas)
        plus3 = findViewById(R.id.plusSpt)
        plus4 = findViewById(R.id.plusKhusus)
        plus5 = findViewById(R.id.plusPkn2)
        plus6 = findViewById(R.id.plusJas2)
        plus7 = findViewById(R.id.plusSpt2)
        plus8 = findViewById(R.id.plusKhusus2)
        plus9 = findViewById(R.id.plusPkn3)
        plus10 = findViewById(R.id.plusJas3)
        plus11 = findViewById(R.id.plusSpt3)
        plus12 = findViewById(R.id.plusKhusus3)

        min1 = findViewById(R.id.minPkn)
        min2 = findViewById(R.id.minJas)
        min3 = findViewById(R.id.minSpt)
        min4 = findViewById(R.id.minKhusus)
        min5 = findViewById(R.id.minPkn2)
        min6 = findViewById(R.id.minJas2)
        min7 = findViewById(R.id.minSpt2)
        min8 = findViewById(R.id.minKhusus2)
        min9 = findViewById(R.id.minPkn3)
        min10 = findViewById(R.id.minJas3)
        min11 = findViewById(R.id.minSpt3)
        min12 = findViewById(R.id.minKhusus3)

        editItem()

        expandLinear1 = findViewById(R.id.expandLinear)
        expandLinear2 = findViewById(R.id.expandLinear2)
        expandLinear3 = findViewById(R.id.expandLinear3)
        layout = findViewById(R.id.layout)
        expand1 = findViewById(R.id.idExp)
        expand2 = findViewById(R.id.idPrem)
        expand3 = findViewById(R.id.idReg)

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
        var itemPkn = Integer.parseInt(item1.text.toString())
        var itemJas = Integer.parseInt(item2.text.toString())
        var itemSpt = Integer.parseInt(item3.text.toString())
        var itemKhs = Integer.parseInt(item4.text.toString())
        var itemPkn2 = Integer.parseInt(item5.text.toString())
        var itemJas2 = Integer.parseInt(item6.text.toString())
        var itemSpt2 = Integer.parseInt(item7.text.toString())
        var itemKhs2 = Integer.parseInt(item8.text.toString())
        var itemPkn3 = Integer.parseInt(item9.text.toString())
        var itemJas3 = Integer.parseInt(item10.text.toString())
        var itemSpt3 = Integer.parseInt(item11.text.toString())
        var itemKhs3 = Integer.parseInt(item12.text.toString())

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

        plus5.setOnClickListener {
            itemPkn2 += 1
            item5.setText(Integer.toString(itemPkn2))
            print("Plus Clicked")
        }
        min5.setOnClickListener {
            if (itemPkn2 == 0) {
                itemPkn2 = 0
            } else {
                itemPkn2 -= 1
            }
            item5.setText(Integer.toString(itemPkn2))
            print("Min Clicked")
        }
        plus6.setOnClickListener {
            itemJas2 += 1
            item6.setText(Integer.toString(itemJas2))
            print("Plus Clicked")
        }
        min6.setOnClickListener {
            if (itemJas2 == 0) {
                itemJas2 = 0
            } else {
                itemJas2 -= 1
            }
            item6.setText(Integer.toString(itemJas2))
            print("Min Clicked")
        }
        plus7.setOnClickListener {
            itemSpt2 += 1
            item7.setText(Integer.toString(itemSpt2))
            print("Plus Clicked")
        }
        min7.setOnClickListener {
            if (itemSpt2 == 0) {
                itemSpt2 = 0
            } else {
                itemSpt2 -= 1
            }
            item7.setText(Integer.toString(itemSpt2))
            print("Min Clicked")
        }
        plus8.setOnClickListener {
            itemKhs2 += 1
            item8.setText(Integer.toString(itemKhs2))
            print("Plus Clicked")
        }
        min8.setOnClickListener {
            if (itemKhs2 == 0) {
                itemKhs2 = 0
            } else {
                itemKhs2 -= 1
            }
            item8.setText(Integer.toString(itemKhs2))
            print("Min Clicked")
        }

        plus9.setOnClickListener {
            itemPkn3 += 1
            item9.setText(Integer.toString(itemPkn3))
            print("Plus Clicked")
        }
        min9.setOnClickListener {
            if (itemPkn3 == 0) {
                itemPkn3 = 0
            } else {
                itemPkn3 -= 1
            }
            item9.setText(Integer.toString(itemPkn3))
            print("Min Clicked")
        }
        plus10.setOnClickListener {
            itemJas3 += 1
            item10.setText(Integer.toString(itemJas3))
            print("Plus Clicked")
        }
        min10.setOnClickListener {
            if (itemJas3 == 0) {
                itemJas3 = 0
            } else {
                itemJas3 -= 1
            }
            item10.setText(Integer.toString(itemJas3))
            print("Min Clicked")
        }
        plus11.setOnClickListener {
            itemSpt3 += 1
            item11.setText(Integer.toString(itemSpt3))
            print("Plus Clicked")
        }
        min11.setOnClickListener {
            if (itemSpt3 == 0) {
                itemSpt3 = 0
            } else {
                itemSpt3 -= 1
            }
            item11.setText(Integer.toString(itemSpt3))
            print("Min Clicked")
        }
        plus12.setOnClickListener {
            itemKhs3 += 1
            item12.setText(Integer.toString(itemKhs3))
            print("Plus Clicked")
        }
        min12.setOnClickListener {
            if (itemKhs3 == 0) {
                itemKhs3 = 0
            } else {
                itemKhs3 -= 1
            }
            item12.setText(Integer.toString(itemKhs3))
            print("Min Clicked")
        }
    }

    fun resetCount() {
        item1.setText(Integer.toString(0))
        item2.setText(Integer.toString(0))
        item3.setText(Integer.toString(0))
        item4.setText(Integer.toString(0))
        item5.setText(Integer.toString(0))
        item6.setText(Integer.toString(0))
        item7.setText(Integer.toString(0))
        item8.setText(Integer.toString(0))
        item9.setText(Integer.toString(0))
        item10.setText(Integer.toString(0))
        item11.setText(Integer.toString(0))
        item12.setText(Integer.toString(0))
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
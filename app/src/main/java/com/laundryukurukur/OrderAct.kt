package com.laundryukurukur

import android.animation.LayoutTransition
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.laundryukurukur.adapter.ProsesAdapter
import com.laundryukurukur.database.Order
import com.laundryukurukur.database.OrderApp
import com.laundryukurukur.database.OrderDao
import com.laundryukurukur.databinding.ActivityMainBinding
import com.laundryukurukur.databinding.ActivityOrderBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderAct : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    private lateinit var expandLinear1: CardView
    private lateinit var expandLinear2: CardView
    private lateinit var expandLinear3: CardView

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

    var pakaian = 0
    var jas = 0
    var sepatu = 0
    var khusus = 0
    var harga = 0

    var biaya = 0
    var hargaPokok = 0
    var kategori = ""
    var kuantitas = 0
    var paket: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListener()

        val bundle = intent.extras
        if (bundle != null) {
            hargaPokok = bundle.getInt("harga")
            kategori = bundle.getString("kategori").toString()
        }

        expandLinear1 = findViewById(R.id.expandLinear)
        expandLinear2 = findViewById(R.id.expandLinear2)
        expandLinear3 = findViewById(R.id.expandLinear3)
        expand1 = findViewById(R.id.idExp)
        expand2 = findViewById(R.id.idPrem)
        expand3 = findViewById(R.id.idReg)

        setItem()

        expand1.setOnClickListener{
            paket = "Express"
            harga = 4000
            val v = if (expandLinear1.visibility == View.GONE) View.VISIBLE else View.GONE
            expandLinear1.visibility = v
            expandLinear2.visibility = View.GONE
            expandLinear3.visibility = View.GONE
            resetCount()
            setItem()
        }
        expand2.setOnClickListener{
            paket = "Premiun"
            harga = 2000
            val v = if (expandLinear2.visibility == View.GONE) View.VISIBLE else View.GONE
            expandLinear2.visibility = v
            expandLinear1.visibility = View.GONE
            expandLinear3.visibility = View.GONE
            resetCount()
            setItem()
        }
        expand3.setOnClickListener{
            paket = "Reguler"
            harga = 1000
            val v = if (expandLinear3.visibility == View.GONE) View.VISIBLE else View.GONE
            expandLinear3.visibility = v
            expandLinear2.visibility = View.GONE
            expandLinear1.visibility = View.GONE
            resetCount()
            setItem()
        }
    }
    fun editItem() {

        plus1.setOnClickListener {
            pakaian += 1
            item1.setText(Integer.toString(pakaian))
            print("Plus Clicked")
        }
        min1.setOnClickListener {
            if (pakaian == 0) {
                pakaian = 0
            } else {
                pakaian -= 1
            }
            item1.setText(Integer.toString(pakaian))
            print("Min Clicked")
        }
        plus2.setOnClickListener {
            jas += 1
            item2.setText(Integer.toString(jas))
            print("Plus Clicked")
        }
        min2.setOnClickListener {
            if (jas == 0) {
                jas = 0
            } else {
                jas -= 1
            }
            item2.setText(Integer.toString(jas))
            print("Min Clicked")
        }
        if(kategori != "Setrika Saja"){
            plus3.setOnClickListener {
                sepatu += 1
                item3.setText(Integer.toString(sepatu))
                print("Plus Clicked")
            }
            min3.setOnClickListener {
                if (sepatu == 0) {
                    sepatu = 0
                } else {
                    sepatu -= 1
                }
                item3.setText(Integer.toString(sepatu))
                print("Min Clicked")
            }
            plus4.setOnClickListener {
                khusus += 1
                item4.setText(Integer.toString(khusus))
                print("Plus Clicked")
            }
            min4.setOnClickListener {
                if (khusus == 0) {
                    khusus = 0
                } else {
                    khusus -= 1
                }
                item4.setText(Integer.toString(khusus))
                print("Min Clicked")
            }
        }

    }

    fun setItem() {
        pakaian = 0
        jas = 0
        sepatu = 0
        khusus = 0
        if (expandLinear1.visibility == View.VISIBLE){
            plus1 = findViewById(R.id.plusPkn)
            plus2 = findViewById(R.id.plusJas)
            plus3 = findViewById(R.id.plusSpt)
            plus4 = findViewById(R.id.plusKhusus)

            min1 = findViewById(R.id.minPkn)
            min2 = findViewById(R.id.minJas)
            min3 = findViewById(R.id.minSpt)
            min4 = findViewById(R.id.minKhusus)

            item1 = findViewById(R.id.itemPkn)
            item2 = findViewById(R.id.itemJas)
            item3 = findViewById(R.id.itemSpt)
            item4 = findViewById(R.id.itemKhusus)
        } else if (expandLinear2.visibility == View.VISIBLE) {
            plus1 = findViewById(R.id.plusPkn2)
            plus2 = findViewById(R.id.plusJas2)
            plus3 = findViewById(R.id.plusSpt2)
            plus4 = findViewById(R.id.plusKhusus2)

            min1 = findViewById(R.id.minPkn2)
            min2 = findViewById(R.id.minJas2)
            min3 = findViewById(R.id.minSpt2)
            min4 = findViewById(R.id.minKhusus2)

            item1 = findViewById(R.id.itemPkn2)
            item2 = findViewById(R.id.itemJas2)
            item3 = findViewById(R.id.itemSpt2)
            item4 = findViewById(R.id.itemKhusus2)
        } else {
            plus1 = findViewById(R.id.plusPkn3)
            plus2 = findViewById(R.id.plusJas3)
            plus3 = findViewById(R.id.plusSpt3)
            plus4 = findViewById(R.id.plusKhusus3)

            min1 = findViewById(R.id.minPkn3)
            min2 = findViewById(R.id.minJas3)
            min3 = findViewById(R.id.minSpt3)
            min4 = findViewById(R.id.minKhusus3)

            item1 = findViewById(R.id.itemPkn3)
            item2 = findViewById(R.id.itemJas3)
            item3 = findViewById(R.id.itemSpt3)
            item4 = findViewById(R.id.itemKhusus3)
        }
        editItem()
    }

    fun resetCount() {
        item1.setText(Integer.toString(0))
        item2.setText(Integer.toString(0))
        item3.setText(Integer.toString(0))
        item4.setText(Integer.toString(0))
    }
    fun setUpListener() {
        val btn: Button = findViewById(R.id.btnOrd)
        btn.setOnClickListener {
            countPrice()
            inputData()
            val showDialog = AlertDialog.Builder(this)
            showDialog.setMessage("Order Success!")
            showDialog.setPositiveButton("OK") { dialog, id ->
                val intent = Intent(this, NoteActivity::class.java)
                startActivity(intent)
            }
            showDialog.show()
        }

    }
    fun countPrice() {
        if (kategori == "Setrika Saja") {
            kuantitas = (pakaian + jas)
            biaya = (pakaian + jas) * (harga + hargaPokok)
        } else {
            kuantitas = (pakaian + jas + sepatu + khusus)
            biaya = (pakaian + jas + sepatu + khusus) * (harga + hargaPokok)
        }
    }
    fun inputData(){
        val nameField = findViewById<EditText>(R.id.edit_name)
        val phoneField = findViewById<EditText>(R.id.edit_phone)
        CoroutineScope(Dispatchers.IO).launch {
            OrderApp(this@OrderAct).getOrderDao().addOrder(
                Order (0, nameField.text.toString(), phoneField.text.toString(), kategori, paket, kuantitas, biaya)
            )
        }
    }
}
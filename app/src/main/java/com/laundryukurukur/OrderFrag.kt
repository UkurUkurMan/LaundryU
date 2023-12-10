package com.laundryukurukur

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.laundryukurukur.database.OrderApp
import com.laundryukurukur.database.OrderDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var dao: OrderDao
    private val hargaperKg = 5.000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =  inflater.inflate(R.layout.fragment_order, container, false)
        val intent = Intent()
        val bundle: Bundle? = intent.extras
        val paket = bundle?.getString("paket")
        val textAngka : TextView = view.findViewById(R.id.Textangka)
        val btnMines : FloatingActionButton = view.findViewById(R.id.buttonMines)
        val btnPlus : FloatingActionButton = view.findViewById((R.id.buttonPlus))
        val btn : Button = view.findViewById(R.id.buttonSave)
        val nameField : EditText = view.findViewById(R.id.edit_nama)
        val phoneField : EditText = view.findViewById(R.id.edit_nomor)

        btn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                OrderApp.getInstance(requireContext()).getOrderDao().addOrder(
                    com.laundryukurukur.database.Order(
                        0,
                        nameField.text.toString(),
                        paket.toString(),
                        phoneField.text.toString()
                    )
                )
            }
        }
        return view

    }
    fun setUpListener() {

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Order.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
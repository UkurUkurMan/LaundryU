package com.laundryukurukur

import android.content.Intent
import androidx.room.*
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.laundryukurukur.adapter.ProsesAdapter
import com.laundryukurukur.database.Order
import com.laundryukurukur.database.OrderApp
import com.laundryukurukur.database.OrderDao
import com.laundryukurukur.databinding.ActivityMainBinding
import com.laundryukurukur.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 */
class home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentHomeBinding

    lateinit var prosesAdapter: ProsesAdapter
    lateinit var list_proses: RecyclerView
    private lateinit var dao: OrderDao

    var harga = 0
    var title = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        dao = OrderApp.invoke(requireContext()).getOrderDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val proses = dao.getAllOrder()
            Log.d("home", "dbResponse: $proses")
            withContext(Dispatchers.IO) {
                prosesAdapter.setData(proses)
            }
        }
    }

    private fun setRecycler(){
        val list_proses: RecyclerView? = view?.findViewById(R.id.list_Proses)
        prosesAdapter = ProsesAdapter(arrayListOf())
        list_proses?.apply {
            layoutManager = LinearLayoutManager(requireContext().applicationContext)
            adapter = prosesAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_home, container, false)
        val textnama : TextView = view.findViewById(R.id.welcome)
        val btn1 : CardView = view.findViewById(R.id.idCuciKrg)
        val btn2 : CardView = view.findViewById(R.id.idCuciBsh)
        val btn3 : CardView = view.findViewById(R.id.idCuciStr)
        val btn4 : CardView = view.findViewById(R.id.idSetrika)
        val user = FirebaseAuth.getInstance().currentUser
        textnama.text = "Welcome, "+user!!.displayName
        val bundle = Bundle()
        btn1.setOnClickListener{
            harga = 3000
            title = "Cuci Kering"
            bundle.putInt("harga", harga)
            bundle.putString("kategori", title)
            val intent = Intent(activity, OrderAct::class.java)
            intent.putExtras(bundle)
            activity?.startActivity(intent)
        }
        btn2.setOnClickListener{
            harga = 2000
            title = "Cuci Basah"
            bundle.putInt("harga", harga)
            bundle.putString("kategori", title)
            val intent = Intent(activity, OrderAct::class.java)
            intent.putExtras(bundle)
            activity?.startActivity(intent)
        }
        btn3.setOnClickListener{
            harga = 4000
            title = "Cuci Setrika"
            bundle.putInt("harga", harga)
            bundle.putString("kategori", title)
            val intent = Intent(activity, OrderAct::class.java)
            intent.putExtras(bundle)
            activity?.startActivity(intent)
        }
        btn4.setOnClickListener{
            harga = 1000
            title = "Setrika Saja"
            bundle.putInt("harga", harga)
            bundle.putString("kategori", title)
            val intent = Intent(activity, OrderAct::class.java)
            intent.putExtras(bundle)
            activity?.startActivity(intent)
        }
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    setRecycler()
                }
            }
    }
}
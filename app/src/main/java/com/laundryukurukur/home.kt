package com.laundryukurukur

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.laundryukurukur.databinding.ActivityMainBinding
import com.laundryukurukur.databinding.FragmentHomeBinding

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
    val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val user = firebaseAuth.currentUser
        val view: View =  inflater.inflate(R.layout.fragment_home, container, false)
        var nama : TextView = view.findViewById(R.id.welcome)
        val cuciKering : CardView = view.findViewById(R.id.idCuciKrg)
        if(user!=null){
            nama.text = user.displayName
        }
        cuciKering.setOnClickListener{
            val paket = "Cuci Kering"
            val bundle = Bundle().apply {
                putString("paket",paket)
            }
            val Myintent = Intent(activity, OrderAct::class.java).apply { putExtras(bundle) }
            activity?.startActivity(Myintent)
        }
        val cuciBasah : CardView = view.findViewById(R.id.idCuciBsh)
        cuciBasah.setOnClickListener {
            val paket = "Cuci Basah"
            val bundle = Bundle().apply {
                putString("paket",paket)
            }
            val Myintent = Intent(activity, OrderAct::class.java).apply { putExtras(bundle) }
            activity?.startActivity(Myintent)
        }
        val cuciSetrika : CardView = view.findViewById(R.id.idCuciStr)
        cuciSetrika.setOnClickListener {
            val paket = "Cuci Setrika"
            val bundle = Bundle().apply {
                putString("paket",paket)
            }
            val Myintent = Intent(activity, OrderAct::class.java).apply { putExtras(bundle) }
            activity?.startActivity(Myintent)
        }
        val onlyGosok : CardView = view.findViewById(R.id.idSetrikaSaja)
        onlyGosok.setOnClickListener{
            val paket = "Setrika Saja"
            val bundle = Bundle().apply {
                putString("paket",paket)
            }
            val Myintent = Intent(activity, OrderAct::class.java).apply { putExtras(bundle) }
            activity?.startActivity(Myintent)
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
                }
            }
    }
}
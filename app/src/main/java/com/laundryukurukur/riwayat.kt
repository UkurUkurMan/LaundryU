package com.laundryukurukur

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.laundryukurukur.adapter.HistoryAdapter
import com.laundryukurukur.adapter.ProsesAdapter
import com.laundryukurukur.database.OrderApp
import com.laundryukurukur.database.OrderDao
import com.laundryukurukur.database.RiwayatApp
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
 * Use the [riwayat.newInstance] factory method to
 * create an instance of this fragment.
 */
class riwayat : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var historyAdapter: HistoryAdapter
    lateinit var list_history: RecyclerView
    private lateinit var dao: OrderDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        dao = RiwayatApp.invoke(requireContext()).getOrderDao()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val proses = dao.getAllOrder()
            Log.d("riwayat", "dbResponse: $proses")
            withContext(Dispatchers.IO) {
                historyAdapter.setData(proses)
            }
        }
    }

    private fun setRecycler(){
        val list_proses: RecyclerView? = view?.findViewById(R.id.historyRv)
        historyAdapter = HistoryAdapter(arrayListOf())
        list_proses?.apply {
            layoutManager = LinearLayoutManager(requireContext().applicationContext)
            adapter = historyAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_riwayat, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment riwayat.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            riwayat().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package com.laundryukurukur

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.laundryukurukur.databinding.FragmentHomeBinding
import com.laundryukurukur.databinding.FragmentProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class profile : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val firebaseAuth = FirebaseAuth.getInstance()
    lateinit var progressDialog : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val user = firebaseAuth.currentUser
        val Email = user?.email
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Logout")
        progressDialog.setMessage("Silahkan Tunggu...")
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_profile, container, false)
        var nama : TextView = view.findViewById(R.id.p_user)
        val logoutButton : Button = view.findViewById(R.id.button_logout)
        if(user!=null){
            nama.text = user.displayName
        }
        var email : TextView = view.findViewById(R.id.p_email)
        if(user!=null){
            email.text = Email
        }

        logoutButton.setOnClickListener {
            val loading = loadingDialog(requireContext())
            loading.show()

            // Lakukan logout
            firebaseAuth.signOut()

            // Pindah activity
            Handler(Looper.getMainLooper()).postDelayed({
                // Start the next activity
                val intent = Intent(requireContext(), login::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                loading.dismiss()
                requireActivity().supportFragmentManager.popBackStack()
                activity?.finish()
            }, 3000)
        }
        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

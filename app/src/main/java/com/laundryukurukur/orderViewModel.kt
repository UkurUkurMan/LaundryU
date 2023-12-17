package com.laundryukurukur

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class orderViewModel : ViewModel() {
    private val nama = MutableLiveData<String>()
    fun setPaket(nama: String) {
        this.nama.value = nama
    }
    fun getPaket(): LiveData<String> {
        return nama
    }

}
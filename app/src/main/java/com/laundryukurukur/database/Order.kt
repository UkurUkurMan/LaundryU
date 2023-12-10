package com.laundryukurukur.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    var name: String = "",
    @ColumnInfo
    var paket: String = "",
    @ColumnInfo
    var phone: String = "",
    @ColumnInfo
    var baju: Int = 0
)
package com.example.learninglanguage1

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity (tableName = "card_table")
data class Card(
    @ColumnInfo(name = "my_id") val myId: Int,
    val id: Int,
    val category: String,
    val input: String,
    val output: String,
)

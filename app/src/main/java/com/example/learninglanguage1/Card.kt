package com.example.learninglanguage1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "card_table")
data class Card(
    @PrimaryKey(autoGenerate = true) val myId: Int,
    val id: Int,
    val category: String,
    val input: String,
    val output: String,
)

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
){


    fun main() {
        test1(
            callback = {

            }
        )
        print("2")

        // 3 4 1 2
        // 3 4 2
    }


    fun test1(callback: (String) -> Unit) {
        val a = ""
        print("3")
        callback(a)
        print("4")
    }
}

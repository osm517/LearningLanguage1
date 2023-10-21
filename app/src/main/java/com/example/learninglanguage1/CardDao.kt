package com.example.learninglanguage1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CardDao {

    @Insert
    fun save(card: Card)

    @Delete
    fun delete(card: MutableList<Card>)

    @Query("select * from card_table")
    fun getAll(): List<Card>

    //@Query()
}
package com.example.learninglanguage1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewSavedCardsFragment : Fragment(R.layout.fragment_view_saved_cards){

    private lateinit var appDatabase: AppDatabase
    private lateinit var cardDao: CardDao
    // private lateinit var adapter: CardDao
    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        appDatabase = AppDatabase.getDatabase(this.requireContext())
        cardDao = appDatabase.cardDao()

        val btnBackViewSavedCards = requireView().findViewById<Button>(R.id.btnBackViewSavedCards)
        btnBackViewSavedCards.setOnClickListener {
            findNavController().navigate(R.id.action_viewSavedCardsFragment_to_testFragment)
        }
        val list: MutableList<Card> = cardDao.getAll() as MutableList
        Log.d("test", "$list")

        val adapter = CardAdapter(
            list, 
            onClickDelete = { Int
                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show()
                 //list.removeAt(0,)
               // adapter.delete(list)
                //delete = Unit
                //delete -> this.notifyItemRemoved(position)
                 //appDatabase.cardDao().delete(list[it])
            }, 
            onItemSelected = {
                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
            }
        )
        val recyclerView: RecyclerView = v.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

}
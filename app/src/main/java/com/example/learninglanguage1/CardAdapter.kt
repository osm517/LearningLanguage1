package com.example.learninglanguage1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter (
    private val cardList: List<Card>,
    private val onItemSelected: (Card) -> Unit,
    private val onClickDelete: (Int) -> Unit
):RecyclerView.Adapter<CardAdapter.CardViewHolder>(){
    class CardViewHolder (v: View): RecyclerView.ViewHolder(v) {

        private val input: TextView = v.findViewById(R.id.input)
        private val output: TextView = v.findViewById(R.id.output)
        private val btnDeleteSavedCards: Button = v.findViewById(R.id.btnDeleteSavedCards)

        fun bind(card: Card, onClickDelete: () -> Unit) {
            input.text = card.input
            output.text = card.output
            btnDeleteSavedCards.setOnClickListener {
                onClickDelete()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.each_item,
            parent,false)
        return CardViewHolder(viewLayout)
    }

    override fun getItemCount() : Int{
        return cardList.size-1
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentCard = cardList[position]
        holder.bind(
            card = currentCard,
            onClickDelete = {
                onClickDelete(position)


        }
        )

        holder.itemView.setOnClickListener {
            onItemSelected(currentCard)
        }
    }

    fun delete(index: Int) {
    notifyItemRemoved(index)
        notifyItemRangeChanged(index, cardList.size-index);
    }

}
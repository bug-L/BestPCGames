package com.example.bestgamespc

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class GameAdapter (
    private val context: Context,
    private val games: MutableList<Game>) : RecyclerView.Adapter<GameAdapter.ListItemHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        val currentItem = games[position]

        val context = holder.imageViewGame.context
        val img = context.resources.getIdentifier(currentItem.image, "drawable", context.packageName)
        holder.imageViewGame.setImageResource(img)
        holder.textViewTitle.text = currentItem.title
    }

    override fun getItemCount() = games.size

    inner class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        //ViewHolder contains one instance of items in recyclerview
        val imageViewGame: ImageView = itemView.imageViewGame
        val textViewTitle: TextView = itemView.textViewTitle

        private val title = itemView.findViewById<View>(R.id.textViewTitle) as TextView

        init {
            itemView.isClickable = true
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val myIntent = Intent(context, GameDetailsActivity::class.java)
            myIntent.putExtra("title", title.text.toString())
            context.startActivity(myIntent)

        }

    }
}
package com.puldroid.imdb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puldroid.imdb.R
import com.puldroid.imdb.modals.Movies
import java.util.*

class MoviesAdapter(private val viewType: Int = 1) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: List<Movies> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, _: Int): RecyclerView.ViewHolder {
        val inflate = { layout: Int ->
            LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
        }

        return when (viewType) {
            1 -> MovieBigViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_large, parent, false)
            )
            else -> MovieSmallViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_large, parent, false)
            )
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = data[position]
        when (viewType) {
            0 -> {
                (holder as MovieBigViewHolder).bind(movie)
            }
            1 -> {
                (holder as MovieSmallViewHolder).bind(movie)
            }
        }
    }

    fun swapData(data: List<Movies>) {
        this.data = data
        notifyDataSetChanged()
    }

    class MovieBigViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movies) {

        }

    }

    class MovieSmallViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movies) {

        }

    }

}
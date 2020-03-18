package com.puldroid.imdb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.puldroid.imdb.R
import com.puldroid.imdb.modals.Movies
import com.puldroid.imdb.utils.Constants
import kotlinx.android.synthetic.main.item_large.view.*
import java.util.*

class MoviesAdapter(private val viewType: Int = 1) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: List<Movies> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, ignore: Int): RecyclerView.ViewHolder {
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
                (holder as MovieSmallViewHolder).bind(movie)
            }
            1 -> {
                (holder as MovieBigViewHolder).bind(movie)
            }
        }
    }

    fun swapData(data: List<Movies>) {
        this.data = data
        notifyDataSetChanged()
    }

    class MovieBigViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movies) = with(itemView) {
            imageLayout.layoutParams.apply {
                width =
                    ((context.resources.displayMetrics.widthPixels * 0.9).toInt())
                height =
                    (((context.resources.displayMetrics.widthPixels * 0.9) / 1.77).toInt())
            }
            Glide.with(context).load(Constants.IMAGE_LOADING_BASE_URL_780 + movie.backdropPath)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(moviePosterImageView)
            movieTitleTextView.text = movie.title
            if (movie.voteAverage != null && movie.voteAverage > 0) {
                movieRatingTextView.apply {
                    isVisible = true
                    text = String.format("%.1f", movie.voteAverage) + Constants.RATING_SYMBOL
                }
            } else {
                movieRatingTextView.isVisible = false
            }
        }


    }

    class MovieSmallViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movies) = with(itemView) {
            Glide.with(context).load(Constants.IMAGE_LOADING_BASE_URL_780 + movie.backdropPath)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(moviePosterImageView)
        }

    }

}
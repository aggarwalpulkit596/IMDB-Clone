package com.puldroid.imdb.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puldroid.imdb.R
import com.puldroid.imdb.adapters.MoviesAdapter
import com.puldroid.imdb.modals.Movies
import com.puldroid.imdb.rest.ApiClient
import kotlinx.android.synthetic.main.fragment_movies.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesFragment : Fragment() {


    val moviesAdapter = MoviesAdapter(1)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)
        view.nowShowingARecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = moviesAdapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNowShowing()

    }

    private fun setUpNowShowing() {
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { ApiClient.service.getNowPlayingMovies(1) }
            if (response.isSuccessful) {
                response.body()?.let { movieResponse ->

                    moviesAdapter.swapData(movieResponse.results)
                }
            }
        }
    }


}

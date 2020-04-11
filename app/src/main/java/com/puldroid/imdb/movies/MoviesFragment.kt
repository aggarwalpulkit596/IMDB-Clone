package com.puldroid.imdb.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puldroid.imdb.R
import com.puldroid.imdb.adapters.MOVIE_BIG
import com.puldroid.imdb.adapters.MoviesAdapter
import com.puldroid.imdb.rest.ApiClient
import kotlinx.android.synthetic.main.fragment_movies.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesFragment : Fragment() {


    val moviesAdapter = MoviesAdapter(MOVIE_BIG)
    private val mPopularAdapter = MoviesAdapter(0)
    private val mUpcomingAdapter = MoviesAdapter(0)
    private val mTopRatedAdapter = MoviesAdapter(0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)
        view.nowShowingARecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = moviesAdapter
        }

        view.topRatedRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = mTopRatedAdapter
        }

        view.upcomingRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = mUpcomingAdapter
        }

        view.popularRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = mPopularAdapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNowShowing()
        setUpTopRated()
        setUpUpcoming()
        setUpPopular()

    }

    private fun setUpTopRated() {
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { ApiClient.service.getTopRatedMovies(1) }
            if (response.isSuccessful) {
                response.body()?.let { movieResponse ->
                    mTopRatedAdapter.swapData(movieResponse.results)
                }
            }
        }
    }

    private fun setUpUpcoming() {
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { ApiClient.service.getUpcomingMovies(1) }
            if (response.isSuccessful) {
                response.body()?.let { movieResponse ->
                    mUpcomingAdapter.swapData(movieResponse.results)
                }
            }
        }
    }

    private fun setUpPopular() {
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { ApiClient.service.getPopularMovies(1) }
            if (response.isSuccessful) {
                response.body()?.let { movieResponse ->
                    mPopularAdapter.swapData(movieResponse.results)
                }
            }
        }
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

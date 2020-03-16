package com.puldroid.imdb.modals

data class MovieResponse(
	val page: Int? = null,
	val totalPages: Int? = null,
	val results: List<Movies?>? = null,
	val totalResults: Int? = null
)

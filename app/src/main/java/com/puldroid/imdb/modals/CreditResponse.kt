package com.puldroid.imdb.modals

data class CreditResponse(
	val cast: List<CastItem?>? = null,
	val id: Int? = null,
	val crew: List<CrewItem?>? = null
)

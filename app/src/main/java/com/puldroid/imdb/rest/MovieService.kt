package com.puldroid.imdb.rest

import com.puldroid.imdb.modals.CreditResponse
import com.puldroid.imdb.modals.MovieResponse
import com.puldroid.imdb.modals.TrailerResponse
import retrofit2.Response
import retrofit2.http.*


interface MovieService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies( @Query("page") pageIndex: Int = 1): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies( @Query("page") pageIndex: Int  = 1): Response<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies( @Query("page") pageIndex: Int = 1): Response<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies( @Query("page") pageIndex: Int = 1): Response<MovieResponse>

    @GET("tv/popular")
    suspend fun getPopularSeries( @Query("page") pageIndex: Int = 1): Response<MovieResponse>

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries( @Query("page") pageIndex: Int = 1): Response<MovieResponse>

    @GET("tv/on_the_air")
    suspend fun getOnTheAirSeries( @Query("page") pageIndex: Int = 1): Response<MovieResponse>

    @GET("tv/airing_today")
    suspend fun getAiringTodayTVShows( @Query("page") pageIndex: Int = 1): Response<MovieResponse>


    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailer(@Path("movie_id") id: Int): Response<TrailerResponse>

    @GET("tv/{movie_id}/videos")
    suspend fun getSeriesTrailer(@Path("movie_id") id: Int): Response<TrailerResponse>

    @GET("movie/{id}/credits")
    suspend fun getMovieCredits(@Path("id") id: Int): Response<CreditResponse>

    @GET("tv/{tv_id}/credits")
    suspend fun getSeriesCredits(@Path("tv_id") id: Int): Response<CreditResponse>

    @GET("search/movie")
    suspend fun searchMovies( @Query("query") search: String): Response<MovieResponse>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendations(@Path("movie_id") id: Int): Response<MovieResponse>

    @GET("tv/{tv_id}/recommendations")
    suspend fun getSeriesRecommendations(@Path("tv_id") id: Int): Response<MovieResponse>

//    @GET("authentication/token/new")
//    suspend fun getRequestToken(@Query("api_key") apiKey: String): Response<LoginResponse>
//
//    @GET("authentication/session/new")
//    suspend fun getSessionId( @Query("request_token") token: String): Response<LoginResponse>
//
//    @GET("authentication/token/validate_with_login")
//    suspend fun getLogin(
//         @Query("username") username: String, @Query(
//            "password"
//        ) password: String, @Query("request_token") token: String
//    ): Response<LoginResponse>
//
//    @POST("account/{account_id}/favorite")
//    @FormUrlEncoded
//    suspend fun markFav(
//        @Path("account_id") id: Int,  @Query("session_id") username: String, @Field(
//            "media_type"
//        ) type: String, @Field("media_id") mediaid: Int, @Field("favorite") add: Boolean
//    ): Response<PostResponse>
//
//    @POST("account/{account_id}/watchlist")
//    @FormUrlEncoded
//    suspend fun markWish(
//        @Path("account_id") id: Int,  @Query(
//            "session_id"
//        ) username: String, @Field("media_type") type: String, @Field("media_id") mediaid: Int, @Field(
//            "watchlist"
//        ) add: Boolean
//    ): Response<PostResponse>
//
//    @GET("account")
//    suspend fun getDetail( @Query("session_id") username: String): Response<AccountResponse>

    @GET("account/{account_id}/watchlist/movies")
    suspend fun getMovieWish(
        @Path("account_id") id: Int,  @Query(
            "session_id"
        ) username: String, @Query("page") pageIndex: Int
    ): Response<MovieResponse>

    @GET("account/{account_id}/watchlist/tv")
    suspend fun getTvWish(
        @Path("account_id") id: Int,  @Query(
            "session_id"
        ) username: String, @Query("page") pageIndex: Int
    ): Response<MovieResponse>

    @GET("account/{account_id}/favorite/movies")
    suspend fun getMovieFav(
        @Path("account_id") id: Int,  @Query(
            "session_id"
        ) username: String, @Query("page") pageIndex: Int
    ): Response<MovieResponse>

    @GET("account/{account_id}/favorite/tv")
    suspend fun getTvFav(
        @Path("account_id") id: Int,  @Query(
            "session_id"
        ) username: String, @Query("page") pageIndex: Int
    ): Response<MovieResponse>
}
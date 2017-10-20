package com.raju.joel.gamerinside.data.source.remote;


import com.raju.joel.gamerinside.data.Game;
import com.raju.joel.gamerinside.data.NewsArticle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteService {

    String EXPANDER_QUERY_PARAM = "expand";

    String ORDER_QUERY_PARAM = "order";

    String FILTER_RELEASE_DATE_UPPER_QUERY_PARAM = "filter[first_release_date][lt]";

    String FILTER_RELEASE_DATE_LOWER_QUERY_PARAM = "filter[first_release_date][gt]";

    String SEARCH_QUERY_PARAM = "search";

    String FIELDS_QUERY_PARAM = "fields";

    String LIMIT_QUERY_PARAM = "limit";

    String OFFSET_QUERY_PARAM = "offset";


    /**
     * To get the latest news articles (title & imageId only)
     * @param limit
     * @param offset
     * @return
     */
    @GET("/pulses/?fields=title,pulse_image.cloudinary_id&order=created_at:desc" +
            "&filter[pulse_image][exists]&filter[author][exist]&filter[summary][exist]")
    Call<List<NewsArticle>> getLatestNews(
            @Query(LIMIT_QUERY_PARAM) int limit,
            @Query(OFFSET_QUERY_PARAM) int offset);


    /**
     * Gets the news details (title, image, summary) from the Ids
     * @param pathIds - comma separated pulse ids
     * @return
     */
    @GET("/pulses/{pulse_ids}")
    Call<List<NewsArticle>> getNewsByIds(@Path(value = "pulse_ids") String pathIds);


    /**
     * Get a list of top 10 popular games order by year desc
     * @return
     */
    @GET("games/?fields=name,cover&order=popularity:desc")
    Call<List<Game>> getPopularGames();

    /**
     * Get a list of 10 top anticipated games ordered by hype
     * @param dateLowerBound
     * @param dateUpperBound
     * @return
     */
    @GET("/games/?fields=name,cover")
    Call<List<Game>> getMostAnticipatedGames(
            @Query(FILTER_RELEASE_DATE_LOWER_QUERY_PARAM) String dateLowerBound,
            @Query(FILTER_RELEASE_DATE_UPPER_QUERY_PARAM) String dateUpperBound,
            @Query("order") String orderQuery);

    /**
     * Get a list of 10 Upcoming Games ordered by popularity
     * @param dateLowerBound
     * @param dateUpperBound
     * @return
     */
    @GET("/games/?fields=name,cover")
    Call<List<Game>> getUpcomingGames(
            @Query(FILTER_RELEASE_DATE_LOWER_QUERY_PARAM) String dateLowerBound,
            @Query(FILTER_RELEASE_DATE_UPPER_QUERY_PARAM) String dateUpperBound,
            @Query(ORDER_QUERY_PARAM) String orderQuery);

    /**
     * Get the detail of a particular game by Id
     * @param gameIds
     * @param expanderFields
     * @return
     */
    @GET("/games/{game_ids}")
    Call<List<Game>> getGameByIds(
            @Path(value = "game_ids") String gameIds,
            @Query((FIELDS_QUERY_PARAM)) String fields,
            @Query((EXPANDER_QUERY_PARAM)) String expanderFields);


    /**
     * Search for games by the given name
     * @param searchTerm
     * @return
     */
    @GET("/games/?fields=name,cover&filter[cover][exists]&order=popularity:desc")
    Call<List<Game>> searchForGames(@Query(SEARCH_QUERY_PARAM) String searchTerm);

}

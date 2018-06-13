package br.eti.wagnermessias.marvelexample.services;

import java.util.Map;

import br.eti.wagnermessias.marvelexample.entities.ResponseAPI;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Wagner on 05/05/2018.
 */

interface MarvelAPI {

    @GET("/v1/public/characters")
    Call<ResponseAPI> getCharacters(@QueryMap Map<String, String> options);

    @GET("/v1/public/comics")
    Call<ResponseAPI> getComics(@QueryMap Map<String, String> options);
   // Call<ResponseAPI> getCharacters(@Query("q") String q, @Query("sort") String sort, @Query("page") int page);

//    @GET("/repos/{creator}/{repository}/pulls")
//    Call<List<PullRequest>> getPullrequests(@Path("creator") String creator, @Path("repository") String repository);
}

package br.eti.wagnermessias.marvelexample.services;

import br.eti.wagnermessias.marvelexample.helpers.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wagner on 05/05/2018.
 */

public class RetrofitClientMarvel {

    private static RetrofitClientMarvel ourInstance = new RetrofitClientMarvel();
    private MarvelAPI serviceMarvelAPI;
    private Retrofit retrofit;
    private static int CACHE_SIZE = 10 * 1024 * 1024; // 10 MB

    private RetrofitClientMarvel() {
        //TODO implementar cache
        //  Cache cache = new Cache(cacheDir, CACHE_SIZE);// 10 MB
        // .cache(cache)
        OkHttpClient client = new OkHttpClient
                .Builder()

                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceMarvelAPI = retrofit.create(MarvelAPI.class);
    }

    public static RetrofitClientMarvel getInstance() {

        if (ourInstance == null) {
            ourInstance = new RetrofitClientMarvel();
        }

        return ourInstance;
    }
    public  MarvelAPI getServiceMarvelAPI(){
        return serviceMarvelAPI;
    }
}

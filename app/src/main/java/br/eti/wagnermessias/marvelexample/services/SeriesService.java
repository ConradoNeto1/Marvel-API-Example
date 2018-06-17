package br.eti.wagnermessias.marvelexample.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.eti.wagnermessias.marvelexample.entities.AppDatabase;
import br.eti.wagnermessias.marvelexample.entities.Data;
import br.eti.wagnermessias.marvelexample.entities.Serie;
import br.eti.wagnermessias.marvelexample.entities.ResponseAPI;
import br.eti.wagnermessias.marvelexample.series.SeriesContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getAuthorizationQueryMap;
import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getLimitOffsetMap;

public class SeriesService implements SeriesServiceContract{

    private AppDatabase db;
    public SeriesContract.Presenter presenter;
    private MarvelAPI serviceMarvel;
    private int countOffset = 0;
    private final int LIMIT = 20;

    public SeriesService(SeriesContract.Presenter presenter) {
        this.presenter = presenter;
        serviceMarvel = RetrofitClientMarvel.getInstance().getServiceMarvelAPI();
        db = AppDatabase.getAppDatabase(presenter.getContextoView());
    }

    @Override
    public void getSeriesAPI(int countOffset) {

        this.countOffset = countOffset;

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.putAll(getAuthorizationQueryMap());
        queryMap.putAll(getLimitOffsetMap(LIMIT, countOffset));

        Call<ResponseAPI> call = serviceMarvel.getSeries(queryMap);

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                if (response.isSuccessful()) {
                    response.body();
                    Data resposta = response.body().getData();
                    List<Serie> seriesAPI = converterResults(resposta.getResults());
                    if (seriesAPI != null && seriesAPI.size() > 0) {
                        insertOrUpdate(seriesAPI);
                        presenter.addData(seriesAPI,"API");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                presenter.toDecreaseCountOffset();
                presenter.notifyErro("Servidor indisponível no momento!");
            }
        });
    }

    @Override
    public void getSeriesDB() {
        List<Serie> seriesDB = db.serieDao().getAll();
        if (seriesDB != null && seriesDB.size() > 0) {
            presenter.addData(seriesDB, "DB");
        }
    }
    @Override
    public void deleteSerie(Serie serie) {
        db.serieDao().delete(serie);
        presenter.removeItemDisplay(serie);
    }

    private List<Serie> converterResults(List<?> result) {
        Gson gson = new Gson();
        String jsonList = gson.toJson(result);

        Type listType = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        List<Serie> list = gson.fromJson(jsonList, listType);
        return list;
    }

    public void insertOrUpdate(List<Serie> series) {

        List<Serie> seriesToUpdate = new ArrayList<>();
        List<Serie> seriesToInsert = new ArrayList<>();

        for (Serie serie : series) {

            Serie serieResult = db.serieDao().loadById(serie.getId());
            if (serieResult != null) {
                seriesToUpdate.add(serie);
            }else{
                seriesToInsert.add(serie);
            }
        }

        if(seriesToInsert != null && seriesToInsert.size() > 0){
            db.serieDao().insertAll(seriesToInsert);
        }

        if(seriesToUpdate != null && seriesToUpdate.size() > 0){
            db.serieDao().updateAll(seriesToUpdate);
        }
    }
}

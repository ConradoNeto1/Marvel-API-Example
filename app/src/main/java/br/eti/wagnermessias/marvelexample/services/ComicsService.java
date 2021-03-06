package br.eti.wagnermessias.marvelexample.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.eti.wagnermessias.marvelexample.comics.ComicsContract;
import br.eti.wagnermessias.marvelexample.entities.AppDatabase;
import br.eti.wagnermessias.marvelexample.entities.Comic;
import br.eti.wagnermessias.marvelexample.entities.Data;
import br.eti.wagnermessias.marvelexample.entities.ResponseAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getAuthorizationQueryMap;
import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getLimitOffsetMap;

public class ComicsService implements ComicsServiceContract{

    private AppDatabase db;
    public ComicsContract.Presenter presenter;
    private MarvelAPI serviceMarvel;
    private int countOffset = 0;
    private final int LIMIT = 20;
    private CreatorsService serviceCreator;

    public ComicsService(ComicsContract.Presenter presenter) {
        this.presenter = presenter;
        serviceMarvel = RetrofitClientMarvel.getInstance().getServiceMarvelAPI();
        db = AppDatabase.getAppDatabase(presenter.getContextoView());
        serviceCreator = new CreatorsService(presenter.getContextoView(),presenter);
    }

    @Override
    public void getComicsAPI(int countOffset) {

        this.countOffset = countOffset;

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.putAll(getAuthorizationQueryMap());
        queryMap.putAll(getLimitOffsetMap(LIMIT, countOffset));

        Call<ResponseAPI> call = serviceMarvel.getComics(queryMap);

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                if (response.isSuccessful()) {
                    response.body();
                    Data resposta = response.body().getData();
                    List<Comic> comicsAPI = converterResults(resposta.getResults());
                    if (comicsAPI != null && comicsAPI.size() > 0) {
                        insertOrUpdate(comicsAPI);
                        presenter.addData(comicsAPI,"API");

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
    public void getComicsDB() {
        List<Comic> comicsDB = db.comicDao().getAll();
        if (comicsDB != null && comicsDB.size() > 0) {
            presenter.addData(comicsDB, "DB");
        }
    }
    @Override
    public void deleteComic(Comic comic) {
        db.comicDao().delete(comic);
        presenter.removeItemDisplay(comic);
    }

    private List<Comic> converterResults(List<?> result) {
        Gson gson = new Gson();
        String jsonList = gson.toJson(result);

        Type listType = new TypeToken<ArrayList<Comic>>() {
        }.getType();
        List<Comic> list = gson.fromJson(jsonList, listType);
        return list;
    }

    public void insertOrUpdate(List<Comic> comics) {

        List<Comic> comicsToUpdate = new ArrayList<>();
        List<Comic> comicsToInsert = new ArrayList<>();

        for (Comic comic : comics) {

            Comic comicResult = db.comicDao().loadById(comic.getId());
            if (comicResult != null) {
                comicsToUpdate.add(comic);
            }else{
                comicsToInsert.add(comic);
            }
        }

        if(comicsToInsert != null && comicsToInsert.size() > 0){
            db.comicDao().insertAll(comicsToInsert);
        }

        if(comicsToUpdate != null && comicsToUpdate.size() > 0){
            db.comicDao().updateAll(comicsToUpdate);
        }

    }

    @Override
    public void loadCreatorsAPI(int idComics) {
        serviceCreator.getCreatorsComicIdAPI(idComics);
    }
}

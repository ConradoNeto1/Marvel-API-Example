package br.eti.wagnermessias.marvelexample.services;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.eti.wagnermessias.marvelexample.comics.ComicsContract;
import br.eti.wagnermessias.marvelexample.entities.AppDatabase;
import br.eti.wagnermessias.marvelexample.entities.ComicCreator;
import br.eti.wagnermessias.marvelexample.entities.Creator;
import br.eti.wagnermessias.marvelexample.entities.Data;
import br.eti.wagnermessias.marvelexample.entities.ResponseAPI;
import br.eti.wagnermessias.marvelexample.creators.CreatorsContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getAuthorizationQueryMap;

public class CreatorsService implements CreatorsServiceContract{

    private AppDatabase db;
    public CreatorsContract.Presenter presenter;
    public ComicsContract.Presenter presenterComics;
    private MarvelAPI serviceMarvel;
    private Context context;

    public CreatorsService(CreatorsContract.Presenter presenter) {
        this.presenter = presenter;
        serviceMarvel = RetrofitClientMarvel.getInstance().getServiceMarvelAPI();
        db = AppDatabase.getAppDatabase(presenter.getContextoView());
    }

    public CreatorsService(Context context, ComicsContract.Presenter presenter) {
        serviceMarvel = RetrofitClientMarvel.getInstance().getServiceMarvelAPI();
        db = AppDatabase.getAppDatabase(context);
        this.context = context;
        presenterComics = presenter;
    }

    @Override
    public void getCreatorsComicIdAPI(final int comicId) {

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.putAll(getAuthorizationQueryMap());

        Call<ResponseAPI> call = serviceMarvel.getCreatorsComicId(comicId,queryMap);

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                if (response.isSuccessful()) {
                    response.body();
                    Data resposta = response.body().getData();
                    List<Creator> CreatorsAPI = converterResults(resposta.getResults());
                    if (CreatorsAPI != null && CreatorsAPI.size() > 0) {
                        db.creatorDao().insertAll(CreatorsAPI);

                        creatorRelationship(CreatorsAPI, comicId);
//                      presenter.addData(CreatorsAPI,"API");
                    }
                }
            }

            private void creatorRelationship(List<Creator> creatorsAPI, int comicId) {

                for(Creator creator : creatorsAPI){

                    ComicCreator comicCreator = new ComicCreator(comicId,  creator.getId());
                    db.comicCreatorDao().insert(comicCreator);

                }

                if(creatorsAPI.size() > 0) {
                    presenterComics.showCreators(comicId);
                }else{
                    presenterComics.notifyErro("Não foi encontrado creators!");
                }

            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                presenterComics.notifyErro("Servidor indisponível no momento!");
            }
        });
    }

    @Override
    public void getCreatorsDB(int idComic) {
        List<Creator> creatorsDB = db.comicCreatorDao().getCreatorsForComic(idComic);
        if (creatorsDB != null && creatorsDB.size() > 0) {
            presenter.addData(creatorsDB);
        }
    }

    private List<Creator> converterResults(List<?> result) {
        Gson gson = new Gson();
        String jsonList = gson.toJson(result);

        Type listType = new TypeToken<ArrayList<Creator>>() {
        }.getType();
        List<Creator> list = gson.fromJson(jsonList, listType);
        return list;
    }
}

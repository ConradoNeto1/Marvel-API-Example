package br.eti.wagnermessias.marvelexample.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.eti.wagnermessias.marvelexample.entities.AppDatabase;
import br.eti.wagnermessias.marvelexample.entities.Creator;
import br.eti.wagnermessias.marvelexample.entities.Data;
import br.eti.wagnermessias.marvelexample.entities.ResponseAPI;
import br.eti.wagnermessias.marvelexample.entities.Story;
import br.eti.wagnermessias.marvelexample.Creators.CreatorsContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getAuthorizationQueryMap;
import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getLimitOffsetMap;

public class CreatorsService implements CreatorsServiceContract{

    private AppDatabase db;
    public CreatorsContract.Presenter presenter;
    private MarvelAPI serviceMarvel;
    private int countOffset = 0;
    private final int LIMIT = 20;

    public CreatorsService(CreatorsContract.Presenter presenter) {
        this.presenter = presenter;
        serviceMarvel = RetrofitClientMarvel.getInstance().getServiceMarvelAPI();
        db = AppDatabase.getAppDatabase(presenter.getContextoView());
    }
    public CreatorsService() {
        serviceMarvel = RetrofitClientMarvel.getInstance().getServiceMarvelAPI();
        db = AppDatabase.getAppDatabase(presenter.getContextoView());
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
//                        presenter.addData(CreatorsAPI,"API");
                    }
                }
            }

            private void creatorRelationship(List<Creator> creatorsAPI, int comicId) {

            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
//                presenter.notifyErro("Servidor indisponível no momento!");
            }
        });
    }

    @Override
    public void getCreatorsDB(int idComic) {
        List<Creator> creatorsDB = db.creatorDao().getAll();
        if (creatorsDB != null && creatorsDB.size() > 0) {
            // presenter.addData(creatorsDB);
        }
    }

//    @Override
//    public void deleteStory(Story story) {
//        db.storyDao().delete(story);
//        presenter.removeItemDisplay(story);
//    }

    private List<Creator> converterResults(List<?> result) {
        Gson gson = new Gson();
        String jsonList = gson.toJson(result);

        Type listType = new TypeToken<ArrayList<Creator>>() {
        }.getType();
        List<Creator> list = gson.fromJson(jsonList, listType);
        return list;
    }
}

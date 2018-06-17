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
import br.eti.wagnermessias.marvelexample.entities.ResponseAPI;
import br.eti.wagnermessias.marvelexample.entities.Story;
import br.eti.wagnermessias.marvelexample.stories.StoriesContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getAuthorizationQueryMap;
import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getLimitOffsetMap;

public class StoriesService implements StoriesServiceContract{

    private AppDatabase db;
    public StoriesContract.Presenter presenter;
    private MarvelAPI serviceMarvel;
    private int countOffset = 0;
    private final int LIMIT = 20;

    public StoriesService(StoriesContract.Presenter presenter) {
        this.presenter = presenter;
        serviceMarvel = RetrofitClientMarvel.getInstance().getServiceMarvelAPI();
        db = AppDatabase.getAppDatabase(presenter.getContextoView());
    }

    @Override
    public void getStoriesAPI(int countOffset) {

        this.countOffset = countOffset;

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.putAll(getAuthorizationQueryMap());
        queryMap.putAll(getLimitOffsetMap(LIMIT, countOffset));

        Call<ResponseAPI> call = serviceMarvel.getStories(queryMap);

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                if (response.isSuccessful()) {
                    response.body();
                    Data resposta = response.body().getData();
                    List<Story> storiesAPI = converterResults(resposta.getResults());
                    if (storiesAPI != null && storiesAPI.size() > 0) {
                        insertOrUpdate(storiesAPI);
                        presenter.addData(storiesAPI,"API");
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
    public void getStoriesDB() {
        List<Story> storiesDB = db.storyDao().getAll();
        if (storiesDB != null && storiesDB.size() > 0) {
            presenter.addData(storiesDB, "DB");
        }
    }
    @Override
    public void deleteStory(Story story) {
        db.storyDao().delete(story);
        presenter.removeItemDisplay(story);
    }

    private List<Story> converterResults(List<?> result) {
        Gson gson = new Gson();
        String jsonList = gson.toJson(result);

        Type listType = new TypeToken<ArrayList<Story>>() {
        }.getType();
        List<Story> list = gson.fromJson(jsonList, listType);
        return list;
    }

    public void insertOrUpdate(List<Story> stories) {

        List<Story> storiesToUpdate = new ArrayList<>();
        List<Story> storiesToInsert = new ArrayList<>();

        for (Story story : stories) {

            Story storyResult = db.storyDao().loadById(story.getId());
            if (storyResult != null) {
                storiesToUpdate.add(story);
            }else{
                storiesToInsert.add(story);
            }
        }

        if(storiesToInsert != null && storiesToInsert.size() > 0){
            db.storyDao().insertAll(storiesToInsert);
        }

        if(storiesToUpdate != null && storiesToUpdate.size() > 0){
            db.storyDao().updateAll(storiesToUpdate);
        }
    }
}

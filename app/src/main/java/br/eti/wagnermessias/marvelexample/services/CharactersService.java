package br.eti.wagnermessias.marvelexample.services;

/**
 * Created by Wagner on 05/05/2018.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.eti.wagnermessias.marvelexample.characters.CharactersContract;
import br.eti.wagnermessias.marvelexample.entities.AppDatabase;
import br.eti.wagnermessias.marvelexample.entities.Character;
import br.eti.wagnermessias.marvelexample.entities.Data;
import br.eti.wagnermessias.marvelexample.entities.ResponseAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getAuthorizationQueryMap;
import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getLimitOffsetMap;

public class CharactersService implements CharactersServiceContract {

    private AppDatabase db;
    public CharactersContract.Presenter presenter;
    private MarvelAPI serviceMarvel;
    private final int LIMIT_CHARACTERS = 20;

    public CharactersService(CharactersContract.Presenter presenter) {
        this.presenter = presenter;
        serviceMarvel = RetrofitClientMarvel.getInstance().getServiceMarvelAPI();
        db = AppDatabase.getAppDatabase(presenter.getContextoView());
    }

    @Override
    public void getCharactersAPI(int countOffset) {

        Map<String,String> queryMap = new HashMap<String,String>();
        queryMap.putAll(getAuthorizationQueryMap());
        queryMap.putAll(getLimitOffsetMap(LIMIT_CHARACTERS,countOffset));

        Call<ResponseAPI> call = serviceMarvel.getCharacters(queryMap);

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                if (response.isSuccessful()) {
                    response.body();
                    Data resposta = response.body().getData();
                    List<Character> charactersAPI = converterResults(resposta.getResults());
                    if(charactersAPI.size() > 0) {

                        db.characterDao().insertCharacters(charactersAPI);
                        List<Character> charactersDB = db.characterDao().getAll();

                        presenter.addData(charactersDB);

                    }else{
                        presenter.toDecreaseCountOffset();
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

    private List<Character> converterResults(List<?> result){
        Gson gson = new Gson();
        String jsonList = gson.toJson(result);

        Type listType = new TypeToken<ArrayList<Character>>(){}.getType();
        List<Character>  list = gson.fromJson(jsonList,listType);
        return list;
    }
}
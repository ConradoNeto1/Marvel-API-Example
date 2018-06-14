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
    private int countOffset = 0;
    private final int LIMIT = 20;

    public CharactersService(CharactersContract.Presenter presenter) {
        this.presenter = presenter;
        serviceMarvel = RetrofitClientMarvel.getInstance().getServiceMarvelAPI();
        db = AppDatabase.getAppDatabase(presenter.getContextoView());
    }

    @Override
    public void getCharactersAPI(int countOffset) {

        this.countOffset = countOffset;

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.putAll(getAuthorizationQueryMap());
        queryMap.putAll(getLimitOffsetMap(LIMIT, countOffset));

        Call<ResponseAPI> call = serviceMarvel.getCharacters(queryMap);

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                if (response.isSuccessful()) {
                    response.body();
                    Data resposta = response.body().getData();
                    List<Character> charactersAPI = converterResults(resposta.getResults());
                    if (charactersAPI != null && charactersAPI.size() > 0) {
                        insertOrUpdate(charactersAPI);
                        presenter.addData(charactersAPI, "API");

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
    public void getCharactersDB() {
        List<Character> charactersDB = db.characterDao().getAll();
        if (charactersDB != null && charactersDB.size() > 0) {
            presenter.addData(charactersDB, "DB");
        }
    }

    @Override
    public void deleteCharacter(Character character) {
        db.characterDao().delete(character);
        presenter.removeItemDisplay(character);

    }


    private List<Character> converterResults(List<?> result) {
        Gson gson = new Gson();
        String jsonList = gson.toJson(result);

        Type listType = new TypeToken<ArrayList<Character>>() {
        }.getType();
        List<Character> list = gson.fromJson(jsonList, listType);
        return list;
    }

    public void insertOrUpdate(List<Character> characters) {

        List<Character> characterToUpdate = new ArrayList<>();
        List<Character> characterToInsert = new ArrayList<>();

        for (Character character : characters) {

            Character characterResult = db.characterDao().loadById(character.getId());
            if (characterResult != null) {
                characterToUpdate.add(character);
            } else {
                characterToInsert.add(character);
            }
        }

        if (characterToInsert != null && characterToInsert.size() > 0) {
            db.characterDao().insertAll(characterToInsert);
        }

        if (characterToUpdate != null && characterToUpdate.size() > 0) {
            db.characterDao().updateAll(characterToUpdate);
        }


    }


    private int getOffset() {
        int offset = (this.countOffset * this.LIMIT) - this.LIMIT;
        return offset;
    }

    private int getLimit() {
        return this.LIMIT;
    }
}
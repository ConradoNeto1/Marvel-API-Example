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
import br.eti.wagnermessias.marvelexample.entities.Event;
import br.eti.wagnermessias.marvelexample.entities.ResponseAPI;
import br.eti.wagnermessias.marvelexample.events.EventsContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getAuthorizationQueryMap;
import static br.eti.wagnermessias.marvelexample.helpers.RequestHelper.getLimitOffsetMap;

public class EventsService implements EventsServiceContract{

    private AppDatabase db;
    public EventsContract.Presenter presenter;
    private MarvelAPI serviceMarvel;
    private int countOffset = 0;
    private final int LIMIT = 20;

    public EventsService(EventsContract.Presenter presenter) {
        this.presenter = presenter;
        serviceMarvel = RetrofitClientMarvel.getInstance().getServiceMarvelAPI();
        db = AppDatabase.getAppDatabase(presenter.getContextoView());
    }

    @Override
    public void getEventsAPI(int countOffset) {

        this.countOffset = countOffset;

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.putAll(getAuthorizationQueryMap());
        queryMap.putAll(getLimitOffsetMap(LIMIT, countOffset));

        Call<ResponseAPI> call = serviceMarvel.getEvents(queryMap);

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                if (response.isSuccessful()) {
                    response.body();
                    Data resposta = response.body().getData();
                    List<Event> eventsAPI = converterResults(resposta.getResults());
                    if (eventsAPI != null && eventsAPI.size() > 0) {
                        insertOrUpdate(eventsAPI);
                        presenter.addData(eventsAPI,"API");
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
    public void getEventsDB() {
        List<Event> eventsDB = db.eventDao().getAll();
        if (eventsDB != null && eventsDB.size() > 0) {
            presenter.addData(eventsDB, "DB");
        }
    }
    @Override
    public void deleteEvent(Event event) {
        db.eventDao().delete(event);
        presenter.removeItemDisplay(event);
    }

    private List<Event> converterResults(List<?> result) {
        Gson gson = new Gson();
        String jsonList = gson.toJson(result);

        Type listType = new TypeToken<ArrayList<Event>>() {
        }.getType();
        List<Event> list = gson.fromJson(jsonList, listType);
        return list;
    }

    public void insertOrUpdate(List<Event> events) {

        List<Event> eventsToUpdate = new ArrayList<>();
        List<Event> eventsToInsert = new ArrayList<>();

        for (Event event : events) {

            Event eventResult = db.eventDao().loadById(event.getId());
            if (eventResult != null) {
                eventsToUpdate.add(event);
            }else{
                eventsToInsert.add(event);
            }
        }

        if(eventsToInsert != null && eventsToInsert.size() > 0){
            db.eventDao().insertAll(eventsToInsert);
        }

        if(eventsToUpdate != null && eventsToUpdate.size() > 0){
            db.eventDao().updateAll(eventsToUpdate);
        }
    }
}

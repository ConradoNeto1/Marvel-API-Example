package br.eti.wagnermessias.marvelexample.events;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import br.eti.wagnermessias.marvelexample.entities.Event;
import br.eti.wagnermessias.marvelexample.helpers.NetworkHelper;
import br.eti.wagnermessias.marvelexample.services.EventsService;

public class EventsPresenter implements EventsContract.Presenter{

    private EventsService service;
    private  EventsContract.View viewEvents;
    private boolean mFirstLoad = true;


    public EventsPresenter(@NonNull EventsContract.View view) {
        this.viewEvents = view;
        this.service = new EventsService(this);

    }

    @Override
    public void loadEvents(int countOffset) {
        if (NetworkHelper.verificaConexao(viewEvents.getContexto())) {
            service.getEventsAPI(countOffset);
        } else {
            service.getEventsDB();
            notifyErro("Sem conexão com Internet");
        }

    }

    @Override
    public void addData(List<Event> eventsNews, String origin) {
        List<Event> eventsOld = viewEvents.getEvents();
        if(!origin.equals("DB")) {
            if (mFirstLoad && eventsOld.size() <= 0 && eventsNews.size() > 0) {
                viewEvents.initRecyclerView(eventsNews);
                mFirstLoad = false;
            } else if (eventsOld.size() > 0 || eventsNews.size() > 0) {
                eventsOld.addAll(eventsNews);
                viewEvents.updateRecyclerView(eventsOld);
            } else {
                // viewRepositories.showError("Nenhum repositório foi encontrado");
            }
        }else {
            if(mFirstLoad){
                viewEvents.initRecyclerView(eventsNews);
            }else{
                mFirstLoad = true;
                viewEvents.updateRecyclerView(eventsNews);
            }
        }
    }

    @Override
    public void deleteItem(Event event) {
        service.deleteEvent(event);
    }

    @Override
    public void removeItemDisplay(Event event) {
        List<Event> events = viewEvents.getEvents();
        events.remove(event);
        viewEvents.updateRecyclerView(events);
    }

    @Override
    public void notifyErro(String msgErro) {
        viewEvents.showErroDisplay(msgErro);
    }

    @Override
    public void toDecreaseCountOffset() {
        int countOffset = viewEvents.getCountOffset();
        if(countOffset > 1){
            viewEvents.setCountOffset(--countOffset);
        }else{
            viewEvents.setCountOffset(1);
        }
    }

    @Override
    public Context getContextoView() {
        return viewEvents.getContexto();
    }
}

package br.eti.wagnermessias.marvelexample.events;

import android.content.Context;

import java.util.List;

import br.eti.wagnermessias.marvelexample.base.BaseView;
import br.eti.wagnermessias.marvelexample.entities.Event;

public interface EventsContract {
    interface View extends BaseView<Presenter> {

        Context getContexto();
        List<Event> getEvents();

        void initRecyclerView(List<Event> events);
        void updateRecyclerView(List<Event> events);
        void showErroDisplay(String msgErro);
        int  getCountOffset();
        void setCountOffset(int countOffset);
    }

    interface Presenter{
        void loadEvents(int countOffset);

        void addData(List<Event> events, String origin);
        void deleteItem(Event event);
        void removeItemDisplay(Event event);

        void notifyErro(String msgErro);

        void toDecreaseCountOffset();

        Context getContextoView();

    }
}

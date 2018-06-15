package br.eti.wagnermessias.marvelexample.services;

import br.eti.wagnermessias.marvelexample.entities.Event;

public interface EventsServiceContract {
    void getEventsAPI(int countOffset);
    void getEventsDB();
    void deleteEvent(Event event);
}

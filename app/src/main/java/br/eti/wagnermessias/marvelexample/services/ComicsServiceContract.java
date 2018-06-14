package br.eti.wagnermessias.marvelexample.services;

import br.eti.wagnermessias.marvelexample.entities.Comic;

public interface ComicsServiceContract {
    void getComicsAPI(int countOffset);
    void getComicsDB();
    void deleteComic(Comic comic);
}

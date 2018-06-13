package br.eti.wagnermessias.marvelexample.services;

import br.eti.wagnermessias.marvelexample.entities.Comic;

public interface ComicsServiceContract {
    void getComicAPI(int countOffset);
    void deleteComic(Comic comic);
}

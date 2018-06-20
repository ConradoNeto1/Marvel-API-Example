package br.eti.wagnermessias.marvelexample.services;

import br.eti.wagnermessias.marvelexample.entities.Story;

public interface CreatorsServiceContract {

    void getCreatorsComicIdAPI(int idComic);

    void getCreatorsDB(int idComic);

//    void deleteStory(Story story);
}

package br.eti.wagnermessias.marvelexample.services;

import br.eti.wagnermessias.marvelexample.entities.Story;

public interface StoriesServiceContract {

    void getStoriesAPI(int countOffset);

    void getStoriesDB();

    void deleteStory(Story story);
}

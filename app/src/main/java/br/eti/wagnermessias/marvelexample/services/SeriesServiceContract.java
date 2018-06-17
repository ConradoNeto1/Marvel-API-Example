package br.eti.wagnermessias.marvelexample.services;

import br.eti.wagnermessias.marvelexample.entities.Serie;

public interface SeriesServiceContract {

    void getSeriesAPI(int countOffset);

    void getSeriesDB();

    void deleteSerie(Serie serie);
}

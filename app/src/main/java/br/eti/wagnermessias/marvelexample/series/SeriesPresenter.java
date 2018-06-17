package br.eti.wagnermessias.marvelexample.series;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import br.eti.wagnermessias.marvelexample.entities.Serie;
import br.eti.wagnermessias.marvelexample.series.SeriesContract;
import br.eti.wagnermessias.marvelexample.helpers.NetworkHelper;
import br.eti.wagnermessias.marvelexample.services.SeriesService;

public class SeriesPresenter implements SeriesContract.Presenter{

    private SeriesService service;
    private  SeriesContract.View viewSeries;
    private boolean mFirstLoad = true;


    public SeriesPresenter(@NonNull SeriesContract.View view) {
        this.viewSeries = view;
        this.service = new SeriesService(this);
    }

    @Override
    public void loadSeries(int countOffset) {
        if (NetworkHelper.verificaConexao(viewSeries.getContexto())) {
            service.getSeriesAPI(countOffset);
        } else {
            service.getSeriesDB();
            notifyErro("Sem conexão com Internet");
        }
    }

    @Override
    public void addData(List<Serie> seriesNews, String origin) {
        List<Serie> seriesOld = viewSeries.getSeries();
        if(!origin.equals("DB")) {
            if (mFirstLoad && seriesOld.size() <= 0 && seriesNews.size() > 0) {
                viewSeries.initRecyclerView(seriesNews);
                mFirstLoad = false;
            } else if (seriesOld.size() > 0 || seriesNews.size() > 0) {
                seriesOld.addAll(seriesNews);
                viewSeries.updateRecyclerView(seriesOld);
            } else {
                // viewRepositories.showError("Nenhum repositório foi encontrado");
            }
        }else {
            if(mFirstLoad){
                viewSeries.initRecyclerView(seriesNews);
            }else{
                mFirstLoad = true;
                viewSeries.updateRecyclerView(seriesNews);
            }
        }
    }

    @Override
    public void deleteItem(Serie serie) {
        service.deleteSerie(serie);
    }

    @Override
    public void removeItemDisplay(Serie serie) {
        List<Serie> series = viewSeries.getSeries();
        series.remove(serie);
        viewSeries.updateRecyclerView(series);
    }

    @Override
    public void notifyErro(String msgErro) {
        viewSeries.showErroDisplay(msgErro);
    }

    @Override
    public void toDecreaseCountOffset() {
        int countOffset = viewSeries.getCountOffset();
        if(countOffset > 1){
            viewSeries.setCountOffset(--countOffset);
        }else{
            viewSeries.setCountOffset(1);
        }
    }

    @Override
    public Context getContextoView() {
        return viewSeries.getContexto();
    }
}

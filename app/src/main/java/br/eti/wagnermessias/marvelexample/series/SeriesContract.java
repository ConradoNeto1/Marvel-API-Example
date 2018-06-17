package br.eti.wagnermessias.marvelexample.series;

import android.content.Context;

import java.util.List;

import br.eti.wagnermessias.marvelexample.base.BaseView;
import br.eti.wagnermessias.marvelexample.entities.Serie;

public interface SeriesContract {
    interface View extends BaseView<Presenter> {

        Context getContexto();
        List<Serie> getSeries();

        void initRecyclerView(List<Serie> series);
        void updateRecyclerView(List<Serie> series);
        void showErroDisplay(String msgErro);
        int  getCountOffset();
        void setCountOffset(int countOffset);
    }

    interface Presenter{
        void loadSeries(int countOffset);

        void addData(List<Serie> series, String origin);
        void deleteItem(Serie serie);
        void removeItemDisplay(Serie serie);

        void notifyErro(String msgErro);

        void toDecreaseCountOffset();

        Context getContextoView();

    }
}

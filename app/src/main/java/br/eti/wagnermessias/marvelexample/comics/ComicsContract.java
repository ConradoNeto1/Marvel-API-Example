package br.eti.wagnermessias.marvelexample.comics;

import android.content.Context;

import java.util.List;

import br.eti.wagnermessias.marvelexample.base.BaseView;
import br.eti.wagnermessias.marvelexample.entities.Comic;

public interface ComicsContract {
    interface View extends BaseView<Presenter> {

        Context getContexto();
        List<Comic> getComics();

        void initRecyclerView(List<Comic> comics);
        void updateRecyclerView(List<Comic> comics);
        void showErroDisplay(String msgErro);
        int  getCountOffset();
        void setCountOffset(int countOffset);
    }

    interface Presenter{
        void loadComics(int countOffset);

        void addData(List<Comic> comics, String origin);
        void deleteItem(Comic comic);
        void removeItemDisplay(Comic comic);

        void notifyErro(String msgErro);

        void toDecreaseCountOffset();

        Context getContextoView();

    }
}

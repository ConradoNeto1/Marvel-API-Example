package br.eti.wagnermessias.marvelexample.creators;

import android.content.Context;

import java.util.List;

import br.eti.wagnermessias.marvelexample.base.BaseView;
import br.eti.wagnermessias.marvelexample.entities.Creator;

public interface CreatorsContract {
    interface View extends BaseView<Presenter> {

        Context getContexto();

        void initRecyclerView(List<Creator> creators);
        void showErroDisplay(String msgErro);
    }

    interface Presenter{
        void loadCreatorsDB(int idComic);

        void addData(List<Creator> creators);

        void notifyErro(String msgErro);

        Context getContextoView();
    }
}

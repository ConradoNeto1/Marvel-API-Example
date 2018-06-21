package br.eti.wagnermessias.marvelexample.creators;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import br.eti.wagnermessias.marvelexample.entities.Creator;
import br.eti.wagnermessias.marvelexample.services.CreatorsService;

public class CreatorsPresenter implements CreatorsContract.Presenter{

    private CreatorsService service;
    private  CreatorsContract.View viewCreators;


    public CreatorsPresenter(@NonNull CreatorsContract.View view) {
        this.viewCreators = view;
        this.service = new CreatorsService(this);
    }

    @Override
    public void loadCreatorsDB(int idComic) {

        service.getCreatorsDB(idComic);

    }

    @Override
    public void addData(List<Creator> creators) {
        viewCreators.initRecyclerView(creators);
    }


    @Override
    public void notifyErro(String msgErro) {
        viewCreators.showErroDisplay(msgErro);
    }


    @Override
    public Context getContextoView() {
        return viewCreators.getContexto();
    }
}

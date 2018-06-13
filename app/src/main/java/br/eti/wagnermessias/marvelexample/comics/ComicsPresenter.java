package br.eti.wagnermessias.marvelexample.comics;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import br.eti.wagnermessias.marvelexample.entities.Comic;
import br.eti.wagnermessias.marvelexample.services.ComicsService;

public class ComicsPresenter implements ComicsContract.Presenter {
    private ComicsService service;
    private  ComicsContract.View viewComics;
    private boolean mFirstLoad = true;


    public ComicsPresenter(@NonNull ComicsContract.View view) {
        this.viewComics = view;
        this.service = new ComicsService(this);

    }

    @Override
    public void loadComics(int countOffset) {
        service.getComicAPI(countOffset);
    }

    @Override
    public void addData(List<Comic> comicsNews) {
        List<Comic> comicsOld = viewComics.getComics();

        if (mFirstLoad && comicsOld.size() <= 0 && comicsNews.size() > 0) {
            viewComics.initRecyclerView(comicsNews);
            mFirstLoad = false;
        } else if (comicsOld.size() > 0 || comicsNews.size() > 0) {
            comicsOld.addAll(comicsNews);
            viewComics.updateRecyclerView(comicsOld);
        } else {
            // viewRepositories.showError("Nenhum repositório foi encontrado");
        }
    }

    @Override
    public void deleteItem(Comic comic) {
        service.deleteComic(comic);
    }

    @Override
    public void removeItemDisplay(Comic comic) {
        List<Comic> comics = viewComics.getComics();
        comics.remove(comic);
        viewComics.updateRecyclerView(comics);
    }

    @Override
    public void notifyErro(String msgErro) {
        viewComics.showErroDisplay(msgErro);
    }

    @Override
    public void toDecreaseCountOffset() {
        int countOffset = viewComics.getCountOffset();
        if(countOffset > 1){
            viewComics.setCountOffset(--countOffset);
        }else{
            viewComics.setCountOffset(1);
        }
    }

    @Override
    public Context getContextoView() {
        return viewComics.getContexto();
    }
}

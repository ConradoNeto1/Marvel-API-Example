package br.eti.wagnermessias.marvelexample.characters;

import android.support.annotation.NonNull;

import java.util.List;

import br.eti.wagnermessias.marvelexample.entities.Character;
import br.eti.wagnermessias.marvelexample.services.CharactersService;

/**
 * Created by Wagner on 05/05/2018.
 */

public class CharactersPresenter implements CharactersContract.Presenter{

    private  CharactersService service;
    private  CharactersContract.View view;
    private boolean mFirstLoad = true;

    public CharactersPresenter(@NonNull CharactersContract.View view) {
        this.service = new CharactersService(this);
        this.view = view;
    }

    @Override
    public void loadCharacters(int countOffset) {
        service.getCharactersAPI(countOffset);
    }

    @Override
    public void addData(List<Character> charactersNews) {
            List<Character> charactersOld = view.getCharacters();

            if (mFirstLoad && charactersOld.size() <= 0 && charactersNews.size() > 0) {
                view.initRecyclerView(charactersNews);
                mFirstLoad = false;
            } else if (charactersOld.size() > 0 || charactersNews.size() > 0) {
                charactersOld.addAll(charactersNews);
                view.updateRecyclerView(charactersOld);
            } else {
               // viewRepositories.showError("Nenhum repositório foi encontrado");
            }
    }

    @Override
    public void notifyErro(String msgErro) {
        view.showErroDisplay(msgErro);
    }

    @Override
    public void toDecreaseCountOffset() {
       int countOffset = view.getCountOffset();
        if(countOffset > 1){
           view.setCountOffset(--countOffset);
        }else{
            view.setCountOffset(1);
        }
    }
}

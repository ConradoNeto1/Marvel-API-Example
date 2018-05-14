package br.eti.wagnermessias.marvelexample.characters;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import br.eti.wagnermessias.marvelexample.entities.Character;
import br.eti.wagnermessias.marvelexample.services.CharactersService;

/**
 * Created by Wagner on 05/05/2018.
 */

public class CharactersPresenter implements CharactersContract.Presenter{

    private  CharactersService service;
    private  CharactersContract.View viewCharacters;
    private boolean mFirstLoad = true;

    public CharactersPresenter(@NonNull CharactersContract.View viewCharacters) {
        this.viewCharacters = viewCharacters;
        this.service = new CharactersService(this);

    }

    @Override
    public void loadCharacters(int countOffset) {
        service.getCharactersAPI(countOffset);
    }

    @Override
    public void addData(List<Character> charactersNews) {
            List<Character> charactersOld = viewCharacters.getCharacters();

            if (mFirstLoad && charactersOld.size() <= 0 && charactersNews.size() > 0) {
                viewCharacters.initRecyclerView(charactersNews);
                mFirstLoad = false;
            } else if (charactersOld.size() > 0 || charactersNews.size() > 0) {
                charactersOld.addAll(charactersNews);
                viewCharacters.updateRecyclerView(charactersOld);
            } else {
               // viewRepositories.showError("Nenhum repositório foi encontrado");
            }
    }

    @Override
    public void notifyErro(String msgErro) {
        viewCharacters.showErroDisplay(msgErro);
    }

    @Override
    public void toDecreaseCountOffset() {
       int countOffset = viewCharacters.getCountOffset();
        if(countOffset > 1){
            viewCharacters.setCountOffset(--countOffset);
        }else{
            viewCharacters.setCountOffset(1);
        }
    }

    @Override
    public Context getContextoView() {
        return viewCharacters.getContexto();
    }

}

package br.eti.wagnermessias.marvelexample.characters;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import br.eti.wagnermessias.marvelexample.entities.Character;
import br.eti.wagnermessias.marvelexample.helpers.NetworkHelper;
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

        if (NetworkHelper.verificaConexao(viewCharacters.getContexto())) {
            service.getCharactersAPI(countOffset);
        } else {
            service.getCharactersDB();
            notifyErro("Sem conexão com Internet");
        }

    }

    @Override
    public void addData(List<Character> charactersNews,String origin) {
            List<Character> charactersOld = viewCharacters.getCharacters();
            if(!origin.equals("DB")) {
                if (mFirstLoad && charactersOld.size() <= 0 && charactersNews.size() > 0) {
                    viewCharacters.initRecyclerView(charactersNews);
                    mFirstLoad = false;
                } else if (charactersOld.size() > 0 || charactersNews.size() > 0) {
                    charactersOld.addAll(charactersNews);
                    viewCharacters.updateRecyclerView(charactersOld);
                } else {
                    // viewRepositories.showError("Nenhum repositório foi encontrado");
                }
            }else {
                if(mFirstLoad){
                    viewCharacters.initRecyclerView(charactersNews);
                }else{
                    mFirstLoad = true;
                    viewCharacters.updateRecyclerView(charactersNews);
                }

            }
    }

    @Override
    public void deleteItem(Character character) {
        service.deleteCharacter(character);
    }

    @Override
    public void removeItemDisplay(Character character) {
        List<Character> characters = viewCharacters.getCharacters();
        characters.remove(character);
        viewCharacters.updateRecyclerView(characters);
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

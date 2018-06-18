package br.eti.wagnermessias.marvelexample.characters;

import android.content.Context;

import java.util.List;

import br.eti.wagnermessias.marvelexample.base.BaseView;
import br.eti.wagnermessias.marvelexample.entities.Character;
import br.eti.wagnermessias.marvelexample.entities.Url;

/**
 * Created by Wagner on 05/05/2018.
 */

public interface CharactersContract {

       interface View extends BaseView<Presenter> {

        Context getContexto();
        List<Character> getCharacters();

        void initRecyclerView(List<Character> characters);
        void updateRecyclerView(List<Character> characters);
        void showErroDisplay(String msgErro);
        int  getCountOffset();
        void setCountOffset(int countOffset);
        //
        //        void showLoadingRecyclerView(boolean showLoadingUI);
        //
        //        void toDecreasePage();
    }

    interface ViewDetalhes extends BaseView<Presenter> {

        Context getContexto();
        void initRecyclerView(List<Url> urls);
        void showErroDisplay(String msgErro);

    }

//    interface Presenter extends BasePresenter {
    interface Presenter{
        void loadCharacters(int countOffset);

    void loadUrls(Integer idCharacter);

    void addData(List<Character> characters, String origin);
    void addData(List<Url> urls);
        void deleteItem(Character character);
        void removeItemDisplay(Character character);

        void notifyErro(String msgErro);

        void toDecreaseCountOffset();

        Context getContextoView();

    Context getContextoViewDetalhe();
//
//        void notifyErro(String msgErro, boolean toDecreasePage);
//
//        File getCacheDir();
//
//        boolean isNetworkAvailable();
    }
}

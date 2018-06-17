package br.eti.wagnermessias.marvelexample.stories;

import android.content.Context;

import java.util.List;

import br.eti.wagnermessias.marvelexample.base.BaseView;
import br.eti.wagnermessias.marvelexample.entities.Story;

public interface StoriesContract {
    interface View extends BaseView<Presenter> {

        Context getContexto();
        List<Story> getStories();

        void initRecyclerView(List<Story> stories);
        void updateRecyclerView(List<Story> stories);
        void showErroDisplay(String msgErro);
        int  getCountOffset();
        void setCountOffset(int countOffset);
    }

    interface Presenter{
        void loadStories(int countOffset);

        void addData(List<Story> stories, String origin);
        void deleteItem(Story story);
        void removeItemDisplay(Story story);

        void notifyErro(String msgErro);

        void toDecreaseCountOffset();

        Context getContextoView();

    }
}

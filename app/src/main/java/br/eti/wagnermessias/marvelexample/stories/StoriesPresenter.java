package br.eti.wagnermessias.marvelexample.stories;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import br.eti.wagnermessias.marvelexample.entities.Story;
import br.eti.wagnermessias.marvelexample.helpers.NetworkHelper;
import br.eti.wagnermessias.marvelexample.services.StoriesService;

public class StoriesPresenter implements StoriesContract.Presenter{

    private StoriesService service;
    private  StoriesContract.View viewStories;
    private boolean mFirstLoad = true;


    public StoriesPresenter(@NonNull StoriesContract.View view) {
        this.viewStories = view;
        this.service = new StoriesService(this);
    }

    @Override
    public void loadStories(int countOffset) {
        if (NetworkHelper.verificaConexao(viewStories.getContexto())) {
            service.getStoriesAPI(countOffset);
        } else {
            service.getStoriesDB();
            notifyErro("Sem conexão com Internet");
        }
    }

    @Override
    public void addData(List<Story> storiesNews, String origin) {
        List<Story> storiesOld = viewStories.getStories();
        if(!origin.equals("DB")) {
            if (mFirstLoad && storiesOld.size() <= 0 && storiesNews.size() > 0) {
                viewStories.initRecyclerView(storiesNews);
                mFirstLoad = false;
            } else if (storiesOld.size() > 0 || storiesNews.size() > 0) {
                storiesOld.addAll(storiesNews);
                viewStories.updateRecyclerView(storiesOld);
            } else {
                // viewRepositories.showError("Nenhum repositório foi encontrado");
            }
        }else {
            if(mFirstLoad){
                viewStories.initRecyclerView(storiesNews);
            }else{
                mFirstLoad = true;
                viewStories.updateRecyclerView(storiesNews);
            }
        }
    }

    @Override
    public void deleteItem(Story story) {
        service.deleteStory(story);
    }

    @Override
    public void removeItemDisplay(Story story) {
        List<Story> stories = viewStories.getStories();
        stories.remove(story);
        viewStories.updateRecyclerView(stories);
    }

    @Override
    public void notifyErro(String msgErro) {
        viewStories.showErroDisplay(msgErro);
    }

    @Override
    public void toDecreaseCountOffset() {
        int countOffset = viewStories.getCountOffset();
        if(countOffset > 1){
            viewStories.setCountOffset(--countOffset);
        }else{
            viewStories.setCountOffset(1);
        }
    }

    @Override
    public Context getContextoView() {
        return viewStories.getContexto();
    }
}

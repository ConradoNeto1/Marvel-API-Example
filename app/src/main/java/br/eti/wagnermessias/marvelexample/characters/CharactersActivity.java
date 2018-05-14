package br.eti.wagnermessias.marvelexample.characters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;

import java.util.ArrayList;
import java.util.List;

import br.eti.wagnermessias.marvelexample.R;
import br.eti.wagnermessias.marvelexample.entities.Character;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CharactersActivity extends AppCompatActivity implements CharactersContract.View {

    @BindView(R.id.rv_characters)
    RecyclerView mRecyclerView;
    private AutoFitGridLayoutManager layoutManager;
    private CharactersAdapter adapter;
    private CharactersPresenter presenter;
    private Context mContext;
    private int countOffset = 1;
    private List<Character> characters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        ButterKnife.bind(this);
        mContext = this;
        presenter = new CharactersPresenter(this);
        init();

    }

    public void init(){
//        ActionBar ab = getSupportActionBar();
//        ab.setTitle("Github JavaPop");

        presenter.loadCharacters(countOffset);
        //presenter.start();
    }

    //    https://www.journaldev.com/13792/android-gridlayoutmanager-example
    public void initRecyclerView(List<Character> characters) {
        //btn_tray_again.setVisibility(View.GONE);
        this.characters = characters;
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new AutoFitGridLayoutManager(this, 200);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new CharactersAdapter(this, this.characters);
//        adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(createInfiniteScrollListener());
//        showLoadingRecyclerView(false);
    }

    public void updateRecyclerView(List<Character> repositories) {
//        btn_tray_again.setVisibility(View.GONE);
//        showLoadingRecyclerView(false);
        this.characters = repositories;
        adapter.notifyDataSetChanged();
    }

    @NonNull
    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(4,layoutManager) {
            @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
                ++countOffset;
                presenter.loadCharacters(countOffset);

            }
        };
    }
    @Override
    public void setPresenter(CharactersContract.Presenter presenter) {
        presenter = presenter;
    }

    @Override
    public Context getContexto() {
        return mContext;
    }

    @Override
    public List<Character> getCharacters() {
        return characters;
    }

    @Override
    public void showErroDisplay(String msgErro){
//        showLoadingRecyclerView(false);
        Toast.makeText(this, msgErro, Toast.LENGTH_SHORT).show();

//        if (repositories == null || repositories.size() < 1) {
//            btn_tray_again.setVisibility(View.VISIBLE);
//        } else {
//            btn_tray_again.setVisibility(View.GONE);
//        }

    }

    public int getCountOffset() {
        return countOffset;
    }

    public void setCountOffset(int countOffset) {
        this.countOffset = countOffset;
    }
}

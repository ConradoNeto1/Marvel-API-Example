package br.eti.wagnermessias.marvelexample.characters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.eti.wagnermessias.marvelexample.R;
import br.eti.wagnermessias.marvelexample.entities.Character;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CharactersActivity extends AppCompatActivity {

    @BindView(R.id.rv_characters)
    RecyclerView mRecyclerView;
    private List<Character> characters = new ArrayList<>();
    private AutoFitGridLayoutManager layoutManager;
    private CharactersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        ButterKnife.bind(this);

        for (int i = 1; i <= 10; i++) {
            characters.add(new Character("Spider-Man", "http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b.jpg"));
        }

        initRecyclerView(characters);
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
//        mRecyclerView.addOnScrollListener(createInfiniteScrollListener());
//        showLoadingRecyclerView(false);
    }
}

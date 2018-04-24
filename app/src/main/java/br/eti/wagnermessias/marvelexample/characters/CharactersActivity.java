package br.eti.wagnermessias.marvelexample.characters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import br.eti.wagnermessias.marvelexample.R;
import br.eti.wagnermessias.marvelexample.entities.Character;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CharactersActivity extends AppCompatActivity {

    @BindView(R.id.rv_characters)
    RecyclerView mRecyclerView;
    private List<Character> characters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        ButterKnife.bind(this);

//        for (int i = 1; i <= 10;i++ ) {
//            characters.add(new Character("Spider-Man", "http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b.jpg"));
//        }

        initRecyclerView(characters);
    }

//    https://www.journaldev.com/13792/android-gridlayoutmanager-example
    public void initRecyclerView(List<Character> characters) {
        //btn_tray_again.setVisibility(View.GONE);
        this.characters = characters;
        mRecyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(layoutManager);
//        adapter = new RepositoriesAdapter(this, this.repositories);
//        adapter.setClickListener(this);
//        mRecyclerView.setAdapter(adapter);
//        mRecyclerView.addOnScrollListener(createInfiniteScrollListener());
//        showLoadingRecyclerView(false);
    }
}

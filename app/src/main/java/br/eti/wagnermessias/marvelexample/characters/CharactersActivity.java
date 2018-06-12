package br.eti.wagnermessias.marvelexample.characters;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
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

public class CharactersActivity extends AppCompatActivity implements CharactersContract.View, CharactersAdapter.ItemClickListener {

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

    public void init() {
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Characters");
        presenter.loadCharacters(countOffset);
    }

    //    https://www.journaldev.com/13792/android-gridlayoutmanager-example
    public void initRecyclerView(List<Character> characters) {
        this.characters = characters;
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new AutoFitGridLayoutManager(this, 200);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new CharactersAdapter(this, this.characters);
        adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(createInfiniteScrollListener());
    }

    @Override
    public void updateRecyclerView(List<Character> characters) {
        this.characters = characters;
        adapter.notifyDataSetChanged();
    }

    @NonNull
    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(4, layoutManager) {
            @Override
            public void onScrolledToEnd(final int firstVisibleItemPosition) {
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
    public void showErroDisplay(String msgErro) {
        Toast.makeText(this, msgErro, Toast.LENGTH_SHORT).show();
    }

    public int getCountOffset() {
        return countOffset;
    }

    public void setCountOffset(int countOffset) {
        this.countOffset = countOffset;
    }

    @Override
    public void onItemClick(View view, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Deseja excluir o Character?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                presenter.deleteItem(characters.get(position));

                String msg = characters.get(position).getName() + " foi excluído!";
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}

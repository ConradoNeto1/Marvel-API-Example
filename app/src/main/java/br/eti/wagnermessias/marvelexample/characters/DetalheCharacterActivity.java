package br.eti.wagnermessias.marvelexample.characters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.eti.wagnermessias.marvelexample.R;
import br.eti.wagnermessias.marvelexample.base.BaseActivity;
import br.eti.wagnermessias.marvelexample.entities.Url;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalheCharacterActivity extends BaseActivity implements CharactersContract.ViewDetalhes, UrlsAdapter.ItemClickListener {

    private String nameCharacter;
    private int idCharacter;
    private CharactersPresenter presenter;
    private List<Url> urls = new ArrayList<>();
    @BindView(R.id.rv_character_urls)
    RecyclerView mRecyclerView;
    private Context mContext;
    private LinearLayoutManager layoutManager;
    private UrlsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_detalhes);
        ButterKnife.bind(this);
        mContext = this;



        presenter = new CharactersPresenter(this);
        Bundle b = getIntent().getExtras();
        if (b != null) {

            nameCharacter = b.getString("name_character");
            idCharacter = b.getInt("id_character");
            presenter.loadUrls(idCharacter);
            setTitleActionbar(nameCharacter);
            enabledBackButon();
        }

    }

    @Override
    public void initRecyclerView(List<Url> urls) {
        this.urls = urls;
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new UrlsAdapter(this, this.urls);
        adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);
//      mRecyclerView.addOnScrollListener(createInfiniteScrollListener());
    }

    @Override
    public void showErroDisplay(String msgErro) {
        Toast.makeText(this, msgErro, Toast.LENGTH_SHORT).show();
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
    public void onItemClick(View view, final int position) {

        String url = urls.get(position).getUrl();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}

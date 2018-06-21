package br.eti.wagnermessias.marvelexample.creators;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.eti.wagnermessias.marvelexample.R;
import br.eti.wagnermessias.marvelexample.base.BaseActivity;
import br.eti.wagnermessias.marvelexample.entities.Creator;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatorsActivity extends BaseActivity implements CreatorsContract.View {

    private String titleComic;
    private int idComic;
    private CreatorsPresenter presenter;
    private List<Creator> creators = new ArrayList<>();
    @BindView(R.id.rv_creators)
            RecyclerView mRecyclerView;
    private Context mContext;
    private LinearLayoutManager layoutManager;
    private CreatorsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creators);
        ButterKnife.bind(this);
        mContext = this;

        presenter = new CreatorsPresenter(this);
        Bundle b = getIntent().getExtras();
        if (b != null) {

            titleComic = b.getString("title_comic");
            idComic = b.getInt("id_comic");
            presenter.loadCreatorsDB(idComic);
            setTitleActionbar(titleComic);
            enabledBackButon();
        }

    }

    @Override
    public void initRecyclerView(List<Creator> creators) {
        this.creators = creators;
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new CreatorsAdapter(this, this.creators);
        // adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);
//      mRecyclerView.addOnScrollListener(createInfiniteScrollListener());
    }

    @Override
    public void showErroDisplay(String msgErro) {
        Toast.makeText(this, msgErro, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(CreatorsContract.Presenter presenter) {
        presenter = presenter;
    }

    @Override
    public Context getContexto() {
        return mContext;
    }

//    @Override
//    public void onItemClick(View view, final int position) {
//
//        String url = urls.get(position).getUrl();
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setData(Uri.parse(url));
//        startActivity(i);
//    }

}

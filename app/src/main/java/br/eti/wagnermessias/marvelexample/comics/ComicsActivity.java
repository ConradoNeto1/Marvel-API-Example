package br.eti.wagnermessias.marvelexample.comics;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;

import java.util.ArrayList;
import java.util.List;

import br.eti.wagnermessias.marvelexample.R;
import br.eti.wagnermessias.marvelexample.base.BaseActivity;
import br.eti.wagnermessias.marvelexample.characters.AutoFitGridLayoutManager;
import br.eti.wagnermessias.marvelexample.entities.Comic;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicsActivity extends BaseActivity implements ComicsContract.View, ComicsAdapter.ItemClickListener{

    private Context mContext;
    private ComicsPresenter presenter;

    @BindView(R.id.rv_comics)
    RecyclerView mRecyclerView;
    private AutoFitGridLayoutManager layoutManager;
    private ComicsAdapter adapter;
    private int countOffset = 1;
    private List<Comic> comics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);
        ButterKnife.bind(this);
        mContext = this;
        presenter = new ComicsPresenter(this);
        init();
    }

    private void init() {
        enabledBackButon();
        setTitleActionbar("Comics");
        presenter.loadComics(countOffset);
    }

    public void initRecyclerView(List<Comic> comics) {
        this.comics = comics;
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new AutoFitGridLayoutManager(this, 300);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new ComicsAdapter(this, this.comics);
        adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(createInfiniteScrollListener());
    }

    @Override
    public void updateRecyclerView(List<Comic> comics) {
        this.comics = comics;
        adapter.notifyDataSetChanged();
    }

    @NonNull
    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(4, layoutManager) {
            @Override
            public void onScrolledToEnd(final int firstVisibleItemPosition) {
                ++countOffset;
                presenter.loadComics(countOffset);
            }
        };
    }

    @Override
    public void setPresenter(ComicsContract.Presenter presenter) {
        presenter = presenter;
    }

    @Override
    public Context getContexto() {
        return mContext;
    }

    @Override
    public List<Comic> getComics() {
        return comics;
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
        builder.setTitle("Deseja excluir o Comic?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                presenter.deleteItem(comics.get(position));

                String msg = comics.get(position).getTitle() + " foi excluído!";
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

package br.eti.wagnermessias.marvelexample.series;

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
import br.eti.wagnermessias.marvelexample.entities.Serie;
import br.eti.wagnermessias.marvelexample.series.SeriesAdapter;
import br.eti.wagnermessias.marvelexample.series.SeriesContract;
import br.eti.wagnermessias.marvelexample.series.SeriesPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SeriesActivity extends BaseActivity implements SeriesContract.View, SeriesAdapter.ItemClickListener{

    private Context mContext;
    private SeriesPresenter presenter;

    @BindView(R.id.rv_series)
    RecyclerView mRecyclerView;
    private AutoFitGridLayoutManager layoutManager;
    private SeriesAdapter adapter;
    private int countOffset = 1;
    private List<Serie> series = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        ButterKnife.bind(this);
        mContext = this;
        presenter = new SeriesPresenter(this);
        init();
    }

    private void init() {
        enabledBackButon();
        setTitleActionbar("Series");
        presenter.loadSeries(countOffset);
    }

    public void initRecyclerView(List<Serie> series) {
        this.series = series;
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new AutoFitGridLayoutManager(this, 300);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new SeriesAdapter(this, this.series);
        adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(createInfiniteScrollListener());
    }

    @Override
    public void updateRecyclerView(List<Serie> series) {
        this.series = series;
        adapter.notifyDataSetChanged();
    }

    @NonNull
    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(4, layoutManager) {
            @Override
            public void onScrolledToEnd(final int firstVisibleItemPosition) {
                ++countOffset;
                presenter.loadSeries(countOffset);
            }
        };
    }

    @Override
    public void setPresenter(SeriesContract.Presenter presenter) {
        presenter = presenter;
    }

    @Override
    public Context getContexto() {
        return mContext;
    }

    @Override
    public List<Serie> getSeries() {
        return series;
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
        builder.setTitle("Deseja excluir o Serie?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                presenter.deleteItem(series.get(position));

                String msg = series.get(position).getTitle() + " foi excluído!";
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

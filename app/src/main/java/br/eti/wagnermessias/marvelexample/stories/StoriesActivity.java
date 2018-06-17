package br.eti.wagnermessias.marvelexample.stories;

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
import br.eti.wagnermessias.marvelexample.entities.Story;
import br.eti.wagnermessias.marvelexample.stories.StoriesAdapter;
import br.eti.wagnermessias.marvelexample.stories.StoriesContract;
import br.eti.wagnermessias.marvelexample.stories.StoriesPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StoriesActivity extends BaseActivity implements StoriesContract.View, StoriesAdapter.ItemClickListener{

    private Context mContext;
    private StoriesPresenter presenter;

    @BindView(R.id.rv_stories)
    RecyclerView mRecyclerView;
    private AutoFitGridLayoutManager layoutManager;
    private StoriesAdapter adapter;
    private int countOffset = 1;
    private List<Story> stories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);
        ButterKnife.bind(this);
        mContext = this;
        presenter = new StoriesPresenter(this);
        init();
    }

    private void init() {
        enabledBackButon();
        setTitleActionbar("Stories");
        presenter.loadStories(countOffset);
    }

    public void initRecyclerView(List<Story> stories) {
        this.stories = stories;
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new AutoFitGridLayoutManager(this, 300);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new StoriesAdapter(this, this.stories);
        adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(createInfiniteScrollListener());
    }

    @Override
    public void updateRecyclerView(List<Story> stories) {
        this.stories = stories;
        adapter.notifyDataSetChanged();
    }

    @NonNull
    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(4, layoutManager) {
            @Override
            public void onScrolledToEnd(final int firstVisibleItemPosition) {
                ++countOffset;
                presenter.loadStories(countOffset);
            }
        };
    }

    @Override
    public void setPresenter(StoriesContract.Presenter presenter) {
        presenter = presenter;
    }

    @Override
    public Context getContexto() {
        return mContext;
    }

    @Override
    public List<Story> getStories() {
        return stories;
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
        builder.setTitle("Deseja excluir o Story?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                presenter.deleteItem(stories.get(position));

                String msg = stories.get(position).getTitle() + " foi excluído!";
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

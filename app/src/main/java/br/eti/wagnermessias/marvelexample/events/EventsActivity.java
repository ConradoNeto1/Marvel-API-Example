package br.eti.wagnermessias.marvelexample.events;

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
import br.eti.wagnermessias.marvelexample.events.EventsAdapter;
import br.eti.wagnermessias.marvelexample.events.EventsContract;
import br.eti.wagnermessias.marvelexample.events.EventsPresenter;
import br.eti.wagnermessias.marvelexample.entities.Event;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsActivity extends BaseActivity implements EventsContract.View, EventsAdapter.ItemClickListener{

    private Context mContext;
    private EventsPresenter presenter;

    @BindView(R.id.rv_events)
    RecyclerView mRecyclerView;
    private AutoFitGridLayoutManager layoutManager;
    private EventsAdapter adapter;
    private int countOffset = 1;
    private List<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);
        mContext = this;
        presenter = new EventsPresenter(this);
        init();
    }

    private void init() {
        enabledBackButon();
        setTitleActionbar("Events");
        presenter.loadEvents(countOffset);
    }

    public void initRecyclerView(List<Event> events) {
        this.events = events;
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new AutoFitGridLayoutManager(this, 300);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new EventsAdapter(this, this.events);
        adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(createInfiniteScrollListener());
    }

    @Override
    public void updateRecyclerView(List<Event> events) {
        this.events = events;
        adapter.notifyDataSetChanged();
    }

    @NonNull
    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(4, layoutManager) {
            @Override
            public void onScrolledToEnd(final int firstVisibleItemPosition) {
                ++countOffset;
                presenter.loadEvents(countOffset);
            }
        };
    }

    @Override
    public void setPresenter(EventsContract.Presenter presenter) {
        presenter = presenter;
    }

    @Override
    public Context getContexto() {
        return mContext;
    }

    @Override
    public List<Event> getEvents() {
        return events;
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
        builder.setTitle("Deseja excluir o Event?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                presenter.deleteItem(events.get(position));

                String msg = events.get(position).getTitle() + " foi excluído!";
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

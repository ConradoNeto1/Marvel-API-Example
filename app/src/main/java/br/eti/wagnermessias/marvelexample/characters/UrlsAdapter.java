package br.eti.wagnermessias.marvelexample.characters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.eti.wagnermessias.marvelexample.R;
import br.eti.wagnermessias.marvelexample.entities.Story;
import br.eti.wagnermessias.marvelexample.entities.Url;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wagner on 24/04/2018.
 */

public class UrlsAdapter extends RecyclerView.Adapter<UrlsAdapter.ViewHolder> {

    public List<Url> urls;
    private ItemClickListener mClickListener;
    private LayoutInflater mInflater;
    private Context mContexto;


    UrlsAdapter(Context context, List<Url> urls) {
        this.mInflater = LayoutInflater.from(context);
        this.urls = urls;
        this.mContexto = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_url, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Url url = urls.get(position);
        holder.url_type.setText(url.getType().toUpperCase());

    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_type)
        TextView url_type;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    Url getItem(int id) {
        return urls.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
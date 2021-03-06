package br.eti.wagnermessias.marvelexample.comics;

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
import br.eti.wagnermessias.marvelexample.entities.Comic;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wagner on 24/04/2018.
 */

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ViewHolder> {


    public List<Comic> comics;
    private ItemClickListener mClickListener;
    private LayoutInflater mInflater;
    private Context mContexto;


    ComicsAdapter(Context context, List<Comic> comics) {
        this.mInflater = LayoutInflater.from(context);
        this.comics = comics;
        this.mContexto = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_comics, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comic comic = comics.get(position);
        holder.comic_title.setText(comic.getTitle());

        Glide.with(mContexto)
                .load(comic.getThumbnailImagem())
                .placeholder(R.mipmap.ic_launcher_round)
                .centerCrop()
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_comics_title)
        TextView comic_title;

        @BindView(R.id.iv_comic_thumbnail)
        ImageView thumbnail;

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

    Comic getItem(int id) {
        return comics.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
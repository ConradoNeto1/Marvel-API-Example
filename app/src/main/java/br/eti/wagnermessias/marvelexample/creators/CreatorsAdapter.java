package br.eti.wagnermessias.marvelexample.creators;

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
import br.eti.wagnermessias.marvelexample.entities.Creator;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wagner on 24/04/2018.
 */

public class CreatorsAdapter extends RecyclerView.Adapter<CreatorsAdapter.ViewHolder> {
    
    public List<Creator> creators;
    private ItemClickListener mClickListener;
    private LayoutInflater mInflater;
    private Context mContexto;


    CreatorsAdapter(Context context, List<Creator> creators) {
        this.mInflater = LayoutInflater.from(context);
        this.creators = creators;
        this.mContexto = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_creator, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Creator creator = creators.get(position);
        holder.creator_fullname.setText(creator.getFullName());


        Glide.with(mContexto)
                .load(creator.getThumbnailImagem())
                .placeholder(R.mipmap.ic_launcher_round)
                .centerCrop()
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return creators.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_creator_fullname)
        TextView creator_fullname;

        @BindView(R.id.iv_creator_thumbnail)
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

    Creator getItem(int id) {
        return creators.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
package br.eti.wagnermessias.marvelexample.stories;

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
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wagner on 24/04/2018.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {
    
    public List<Story> stories;
    private ItemClickListener mClickListener;
    private LayoutInflater mInflater;
    private Context mContexto;


    StoriesAdapter(Context context, List<Story> stories) {
        this.mInflater = LayoutInflater.from(context);
        this.stories = stories;
        this.mContexto = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_stories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.story_title.setText(story.getTitle());

        holder.story_type.setText(story.getType());
        holder.story_description.setText(story.getDescription());

        Glide.with(mContexto)
                .load(story.getThumbnailImagem())
                .placeholder(R.mipmap.ic_launcher_round)
                .centerCrop()
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_stories_title)
        TextView story_title;

        @BindView(R.id.tv_description)
        TextView story_description;

        @BindView(R.id.tv_type)
        TextView story_type;

        @BindView(R.id.iv_story_thumbnail)
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

    Story getItem(int id) {
        return stories.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
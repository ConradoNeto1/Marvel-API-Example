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
import br.eti.wagnermessias.marvelexample.entities.Character;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wagner on 24/04/2018.
 */

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {


    public List<Character> characters;
    private ItemClickListener mClickListener;
    private LayoutInflater mInflater;
    private Context mContexto;


    CharactersAdapter(Context context, List<Character> characters) {
        this.mInflater = LayoutInflater.from(context);
        this.characters = characters;
        this.mContexto = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_characters, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Character character = characters.get(position);
        holder.character_name.setText(character.getName());

        Glide.with(mContexto)
                .load(character.getImagem())
                .placeholder(R.mipmap.ic_launcher_round)
                .centerCrop()
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_character_name)
        TextView character_name;

        @BindView(R.id.iv_character_thumbnail)
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

    Character getItem(int id) {
        return characters.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
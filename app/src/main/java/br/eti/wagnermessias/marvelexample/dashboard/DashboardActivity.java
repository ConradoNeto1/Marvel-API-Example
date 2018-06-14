package br.eti.wagnermessias.marvelexample.dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.Toast;

import br.eti.wagnermessias.marvelexample.R;
import br.eti.wagnermessias.marvelexample.characters.CharactersActivity;
import br.eti.wagnermessias.marvelexample.comics.ComicsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.cardViewCharacters)
    public void onCardCharactersClicked() {
        Intent intent = new Intent(DashboardActivity.this, CharactersActivity.class);
        startActivity(intent);
//        Bundle b = new Bundle();
//        b.putString("key_creator", creator);
//        b.putString("key_repository", repository);
//        intent.putExtras(b);
    }

    @OnClick(R.id.cardViewComics)
    public void onCardComicsClicked() {
        Intent intent = new Intent(DashboardActivity.this, ComicsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.cardViewEvents)
    public void onCardEventsClicked() {
        Toast.makeText(this, "Events", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.cardViewSeries)
    public void onCardSeriesClicked() {
        Toast.makeText(this, "Series", Toast.LENGTH_SHORT).show();
    }
}

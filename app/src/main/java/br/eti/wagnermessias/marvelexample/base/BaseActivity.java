package br.eti.wagnermessias.marvelexample.base;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity  extends AppCompatActivity{

    public void enabledBackButon(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setTitleActionbar(String title){
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

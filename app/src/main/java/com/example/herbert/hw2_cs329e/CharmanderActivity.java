package com.example.herbert.hw2_cs329e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CharmanderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charmander);
    }

    public void onButtonClickCharmander(View v) {

        if (v.getId() == R.id.button)
        {
            this.finish();
        }
    }

}

package com.example.herbert.hw2_cs329e;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUTEID;
    private EditText editTextPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void onButtonClick(View v) {
        editTextUTEID = (EditText)findViewById(R.id.editTextUTEID);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        if (v.getId() == R.id.button)
        {
            if (editTextUTEID.getText().toString().equals("hog74") &&
                    editTextPassword.getText().toString().equals("password"))
            {
                Intent i = new Intent(MainActivity.this, PokemonSighting.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(MainActivity.this, "wrong username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

package com.example.herbert.hw2_cs329e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PokemonOptions extends AppCompatActivity {


    private static RadioGroup radioGroup;
    private static Button button;
    private static RadioButton radioButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_options);

    }



    public void onButtonClick(View v){
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        button = (Button) findViewById(R.id.buttonSelect);

        if(v.getId() == R.id.buttonSelect) {
            int select_id = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(select_id);
            ///Toast.makeText(PokemonOptions.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();


            if (radioButton.getText().toString().equals("Pikachu"))
            {
                Toast.makeText(PokemonOptions.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PokemonOptions.this, PikachuActivity.class);
                startActivity(i);
            }

            else if (radioButton.getText().toString().equals("Bulbasaur"))
            {
                Toast.makeText(PokemonOptions.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PokemonOptions.this, BulbasaurActivity.class);
                startActivity(i);

            }

            else if (radioButton.getText().toString().equals("Charmander"))
            {
                Toast.makeText(PokemonOptions.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PokemonOptions.this, CharmanderActivity.class);
                startActivity(i);
            }

            else if (radioButton.getText().toString().equals("Squirtle"))
            {
                Toast.makeText(PokemonOptions.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PokemonOptions.this, SquirtleActivity.class);
                startActivity(i);
            }

            else if (radioButton.getText().toString().equals("None of these"))
            {
                Toast.makeText(PokemonOptions.this, "You must pick one!", Toast.LENGTH_SHORT).show();

            }

        }
    }
}

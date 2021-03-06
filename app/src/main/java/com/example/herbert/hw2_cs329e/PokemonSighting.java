package com.example.herbert.hw2_cs329e;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PokemonSighting extends AppCompatActivity {

    // create an instance of the DatabaseHelper class here
    DatabaseHelper myDb;

    // declare the variables needed to manipulate the widgets
    EditText editNotes, editDate, editTextId, editTime, editLocation;
    Button buttonAddData;
    Button buttonViewAll;
    Button updateButton;
    Button deleteButton;

    Spinner editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_sighting);

        // this will call the constructor of the DatabaseHelper class which will create the DB
        myDb = new DatabaseHelper(this);

        // this section gets all the GUI widget ID's for use in the Listeners
        //editName = (EditText)findViewById(R.id.editTextName);
        editName = (Spinner)findViewById(R.id.spinnerName);
        editDate = (EditText)findViewById(R.id.editTextDate);
        editTime = (EditText)findViewById(R.id.editTextTime);
        editLocation = (EditText)findViewById(R.id.editTextLocation);
        editNotes = (EditText)findViewById(R.id.editTextNotes);
        editTextId = (EditText)findViewById(R.id.editTextID);
        buttonAddData = (Button)findViewById(R.id.buttonData);
        buttonViewAll = (Button)findViewById(R.id.buttonAll);
        updateButton = (Button)findViewById(R.id.buttonUpdate);
        deleteButton = (Button)findViewById(R.id.buttonDelete);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.pokemon,android.R.layout.simple_spinner_item);
        editName.setAdapter(adapter);



        // in the onCreate of the Main Activity, call all of the methods needed to manage the DB
        AddData();
        viewAll();
        updateData();
        DeleteData();
    }

    // this section listens for the "Add Data" button event and inserts into the DB
    // must convert to String in order to input into the DB
    public void AddData() {
        buttonAddData.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Uri uri;
                        Intent i;
                        boolean isInserted = myDb.insertData(editName.getSelectedItem().toString(), editDate.getText().toString(), editTime.getText().toString(), editLocation.getText().toString(), editNotes.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(PokemonSighting.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            if (editName.getSelectedItem().toString().equals("Pikachu")){
                                uri = Uri.parse("http://pokemondb.net/pokedex/pikachu");
                                i = new Intent(Intent.ACTION_VIEW,uri);
                                startActivity(i);
                            }
                            else if (editName.getSelectedItem().toString().equals("Charmander")){
                                uri = Uri.parse("http://pokemondb.net/pokedex/charmander");
                                i = new Intent(Intent.ACTION_VIEW,uri);
                                startActivity(i);
                            }
                            else if (editName.getSelectedItem().toString().equals("Squirtle")){
                                uri = Uri.parse("http://pokemondb.net/pokedex/squirtle");
                                i = new Intent(Intent.ACTION_VIEW,uri);
                                startActivity(i);
                            }
                            else if (editName.getSelectedItem().toString().equals("Bulbasaur")){
                                uri = Uri.parse("http://pokemondb.net/pokedex/bulbasaur");
                                i = new Intent(Intent.ACTION_VIEW,uri);
                                startActivity(i);
                            }
                        else
                            Toast.makeText(PokemonSighting.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();
                    }

                }
        );
    }
    // this section listens for the "delete" button event and deletes the data with the ID specified
    public void DeleteData() {
        deleteButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(PokemonSighting.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PokemonSighting.this, "Data NOT Deleted", Toast.LENGTH_LONG).show();
                    }

                }
        );
    }

    // this method listens for the "View All" button to be pressed the displays all data to the user
    public void viewAll() {
        buttonViewAll.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // write message
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Date :" + res.getString(2) + "\n");
                            buffer.append("Time :" + res.getString(3) + "\n");
                            buffer.append("Location :" + res.getString(4) + "\n");
                            buffer.append("Notes :" + res.getString(5) + "\n");
                        }
                        // call the method to show all data on the GUI
                        showMessage("Pokemon Sightings Log",buffer.toString());
                    }
                });
    }

    // this listens for the "update" button on the GUI to update the DB based on ID entered
    public void updateData() {
        updateButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        boolean isUpdated = myDb.updateData(editTextId.getText().toString(), editName.getSelectedItem().toString(), editDate.getText().toString(), editTime.getText().toString(), editLocation.getText().toString(), editNotes.getText().toString());
                        if (isUpdated == true)
                            Toast.makeText(PokemonSighting.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PokemonSighting.this, "Data NOT Updated", Toast.LENGTH_LONG).show();
                    }

                }
        );
    }

    // this method uses the AlertDialog method to create the data
    // Creates a builder for an alert dialog that uses the default alert dialog theme.
    public void showMessage (String title, String Message){
        // create a new alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Sets whether the dialog is cancelable or not.
        builder.setCancelable(true);

        // Set the title displayed in the dialog
        builder.setTitle(title);

        // set the message to display
        builder.setMessage(Message);

        // creates an AlertDialog with the arguments supplied to this builder and immediately displays the dialog.
        builder.show();
    }
}

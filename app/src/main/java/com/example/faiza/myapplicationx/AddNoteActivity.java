package com.example.faiza.myapplicationx;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    Button buttonSave;
    EditText editTextTitles, editTextNotes, editTextDate;
    NoteDataSource noteDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteDataSource = new NoteDataSource(this);
        noteDataSource.open();

        // link each editText variable to the xml layout
        editTextTitles = (EditText) findViewById(R.id.editTextTitles);
        editTextNotes = (EditText) findViewById(R.id.editTextNotes);
        editTextDate = (EditText) findViewById(R.id.editTextDate);



        // set up the button listener
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Add the fish to the database
                String titles = editTextTitles.getText().toString();
                String notes = editTextNotes.getText().toString();
                String dateCaught = editTextDate.getText().toString();
                noteDataSource.createNote(titles, notes, dateCaught);
                //fishDataSource.createFish(species, weight, dateCaught, lattitude.toString(), longiture.toString());
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        noteDataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        noteDataSource.close();
        super.onPause();
    }
}


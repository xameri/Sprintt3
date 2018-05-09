package com.example.faiza.myapplicationx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NotesActivity extends AppCompatActivity {

    Button buttonBack;
    EditText editTextTitles, editTextNotes, editTextDate;
    NoteDataSource noteDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Bundle bundle = getIntent().getExtras();
        Notes notes = (Notes)   bundle.getSerializable("Notes");

        // link each editText variable to the xml layout
        editTextTitles = (EditText) findViewById(R.id.editTextTitles);
        editTextNotes = (EditText) findViewById(R.id.editTextNotes);
        editTextDate = (EditText) findViewById(R.id.editTextDate);


        editTextTitles.setText(notes.getTitles());
        editTextNotes.setText(notes.getNotes());
        editTextDate.setText(notes.getDateCaught());


        // set up the button listener
        buttonBack = (Button) findViewById(R.id.buttonReturn);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);
            }
        });

    }
}


package com.example.faiza.myapplicationx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonAdd, buttonDetails, buttonDelete;            // two button widgets
    ListView listViewNote;                      // listview to display all the note in the database
    NoteDataSource noteDataSource;              // provides interaction to the SQLite note table
    ArrayAdapter<Notes> noteAdapter;
    List<Notes> noteList;
    int positionSelected;
    Notes noteSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteDataSource = new NoteDataSource(this);          // set up the note data source
        noteDataSource.open();                              // open up this data source--close before we leave

        // Set up the listVeiw to display all the note using a custam adapter
        listViewNote = (ListView) findViewById(R.id.ListViewNote);
        noteList = noteDataSource.getAllNote();              // Get the list of note from the database
        // Instantiate a custom adapter for displaying each note
        noteAdapter = new NoteAdapter(this, android.R.layout.simple_list_item_single_choice, noteList);
        // Apply the adapter to the list
        listViewNote.setAdapter(noteAdapter);
        listViewNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View parent,
                                    int position, long id) {
                positionSelected = position;
                Log.d("MAIN", "Note selected at position " + positionSelected);
            }
        });

        // Set up the button to add a new note using a seperate activity
        buttonAdd = (Button) findViewById(R.id.buttonAddNote);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Start up the add note activity with an intent
                Intent detailActIntent = new Intent(view.getContext(), AddNoteActivity.class);
                finish();
                startActivity(detailActIntent);
            }
        });
        // Set up the button to display details on one note using a seperate activity
        buttonDetails = (Button) findViewById(R.id.buttonDetails);
        buttonDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("MAIN", "onClick for Details");
                Intent detailActIntent = new Intent(view.getContext(), NotesActivity.class);
                detailActIntent.putExtra("Note", noteList.get(positionSelected));
                finish();
                startActivity(detailActIntent);
            }
        });
        // Set up the button to display details on one note using a seperate activity
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MAIN", "onClick for Delete");
                Log.d("MAIN", "Delete at position " + positionSelected);
                noteDataSource.deleteNote(noteList.get(positionSelected));
                noteAdapter.remove( noteList.get(positionSelected) );
                noteAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    protected void onResume() {
        // re-open the database after a resume
        noteDataSource.open();
        Log.d("MAIN", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        // close the database when the app goes into the background incase it doesn't come back
        noteDataSource.close();
        Log.d("MAIN", "onPause");
        super.onPause();
    }
}


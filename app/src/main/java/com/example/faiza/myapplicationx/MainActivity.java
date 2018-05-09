package com.example.faiza.myapplicationx;
// I am commiting now
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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


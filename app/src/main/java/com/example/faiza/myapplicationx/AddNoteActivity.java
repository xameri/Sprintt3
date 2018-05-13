package com.example.faiza.myapplicationx;
// My Name is Farah here commiting Sprint 3

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

   
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
        if (id == R.id.action_save) {
            String titles = editTextTitles.getText().toString();
            String notes = editTextNotes.getText().toString();
            String dateCaughted = editTextDate.getText().toString();
            noteDataSource.createNote(titles, notes, dateCaughted);
            Intent mainActIntent = new Intent(this, MainActivity.class);
            finish();
            startActivity(mainActIntent);
            return true;
        }
        if (id == R.id.action_edit) {
            return true;
        }
        if (id == R.id.action_delete) {
            return true;
        }
        if (id == R.id.action_back) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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


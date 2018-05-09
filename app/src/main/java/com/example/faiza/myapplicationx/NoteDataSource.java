package com.example.faiza.myapplicationx;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgibons on 4/20/2017.
 *
 * Provide methods for CRUD functions on the SQLite databse for the fish object
 */

public class NoteDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    /*
     * This method creates a new database helper that is a new MySQLHelper object with the parameter context.
     *
     * @param context This parameter is a handle to the system. Helps obtain access to databases,
     * preferences, and helps resolve resources.
     */
    public NoteDataSource(Context context) {
        dbHelper = MySQLiteHelper.getInstance(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Notes createNote( String titles, String notes, String dateCaught) {           //Added String rating as a parameter
        ContentValues values = new ContentValues();                         // Create a new ContentValue Object
        values.put(MySQLiteHelper.COLUMN_TITLES, titles);                 // Insert a species into the COLUMN_SPECIES field using MYSQLiteHelper
        values.put(MySQLiteHelper.COLUMN_NOTES, notes);               // Insert weightInOz into the COLUMN_WEIGHT field using MYSQLiteHelper
        values.put(MySQLiteHelper.COLUMN_DATECAUGHTED, dateCaught);

        long insertId = database.insert(MySQLiteHelper.TABLE_NOTES, null, values);         //  Instert the fish into the database using the parameters above
        Notes newNote = new Notes(insertId, titles, notes, dateCaught);
        return newNote;
    }



    public void deleteNote(Notes note) {
        long id = note.getId();
        System.out.println("Fish deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_NOTES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Notes> getAllNote() {
        List<Notes> noteList = new ArrayList<Notes>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTES,       //Modified to return all database fields
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Notes note = cursorToNote(cursor);
            noteList.add(note);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        //cursor.close();
        return noteList;
    }

    /**
     *  Converts the current row in the database cursor into a fish object
     * @param cursor points to the current row in the databsae cursor
     * @return a fish object created from that row
     */
    private Notes cursorToNote(Cursor cursor) {
        Notes note = new Notes();
        note.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)));
        note.setTitles(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_TITLES)));
        note.setNotes(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_NOTES)));
        note.setDateCaught(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_DATECAUGHTED)));

        return note;
    }

}

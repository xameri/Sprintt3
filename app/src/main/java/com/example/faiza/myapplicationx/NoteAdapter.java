package com.example.faiza.myapplicationx;
// My Name is Farah here commiting Sprint 3
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cssuser on 4/20/2017.
 */

public class NoteAdapter extends ArrayAdapter<Notes> {

    private List<Notes> noteList;            // The list of fish to display
    private Context context;                // The original activity that displays this
    public int layoutResource;                   // the layout to use

    /**
     *   Basic constructo
     * @param context - The activity calling this
     * @param resource  The layout to use to display
     * @param noteList  The list of fish to display
     */
    public NoteAdapter(Context context, int resource, List<Notes> noteList) {
        super(context, resource, noteList);
        this.context = context;
        this.layoutResource = resource;
        this.noteList = noteList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the fish we are displaying
        Notes note = noteList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //view = inflater.inflate(R.layout.fish_row_layout, null);
        view = inflater.inflate(R.layout.note_row_layout, null);

        TextView tvTitles=(TextView)view.findViewById(R.id.textViewTitles);
        TextView tvNotes=(TextView)view.findViewById(R.id.textViewNotes);
        TextView tvDate=(TextView)view.findViewById(R.id.textViewDate);
        tvTitles.setText(note.getTitles());
        tvNotes.setText(note.getNotes());
        tvDate.setText(note.getDateCaught());

        return(view);
    }


}
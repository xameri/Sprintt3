package com.example.faiza.myapplicationx;
// My Name is Farah here commiting Sprint 3
import java.io.Serializable;
import java.util.Date;

/**
 * Created by cssuser on 4/20/2017.
 */

public class Notes implements Serializable {
    private long id;
    private String titles;
    private String notes;
    private String dateCaughted;


    public Notes() {
    }

    public Notes(long id, String titles, String notes, String dateCaughted) {
        this.id = id;
        this.titles = titles;
        this.notes = notes;
        this.dateCaughted = dateCaughted;

    }

    public Notes(String titles, String notes, String dateCaught) {
        this.titles = titles;
        this.notes = notes;
        this.dateCaughted = dateCaught;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String note) {
        this.notes = note;
    }

    public String getDateCaught() {
        return dateCaughted;
    }

    public void setDateCaught(String dateCaughted) {
        this.dateCaughted = dateCaughted;
    }



    @Override
    public String toString() {
        return "Notes{" +
                "titles='" + titles + '\'' +
                ", notes='" + notes + '\'' +
                ", dateCaughted='" + dateCaughted + '\'' +
                '}';
    }
}


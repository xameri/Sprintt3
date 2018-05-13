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
    private String note;
    private String dateCaught;


    public Notes() {
    }

    public Notes(long id, String titles, String note, String dateCaught) {
        this.id = id;
        this.titles = titles;
        this.note = note;
        this.dateCaught = dateCaught;

    }

    public Notes(String titles, String notes, String dateCaught) {
        this.titles = titles;
        this.note = note;
        this.dateCaught = dateCaught;

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
        return note;
    }

    public void setNotes(String note) {
        this.note = note;
    }

    public String getDateCaught() {
        return dateCaught;
    }

    public void setDateCaught(String dateCaught) {
        this.dateCaught = dateCaught;
    }



    @Override
    public String toString() {
        return "Notes{" +
                "titles='" + titles + '\'' +
                ", note='" + note + '\'' +
                ", dateCaught='" + dateCaught + '\'' +
                '}';
    }
}


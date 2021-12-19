package com.qtest.program.hibernate;

import java.sql.Timestamp;

public class QhRow {
    private int id;
    private String name;
    private Timestamp timestamp;
    private String note;

    public QhRow() {
        id = 0;
        name = null;
        timestamp = null;
        note = null;
    }

    public QhRow(int id, String name, Timestamp datetime, String note) {
        this.id = id;
        this.name = name;
        this.timestamp = datetime;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String toString() {
        return(id+"\t"+name+"\t"+ timestamp +"\t"+note);
    }
}

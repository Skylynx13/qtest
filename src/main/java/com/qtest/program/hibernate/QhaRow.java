package com.qtest.program.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "qhiberan")
public class QhaRow {
    @Id
    @Column(name="id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Column(name = "note")
    private String note;

    public QhaRow() {}

    public QhaRow(int id, String name, Timestamp datetime, String note) {
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

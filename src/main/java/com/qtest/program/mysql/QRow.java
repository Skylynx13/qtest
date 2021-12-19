package com.qtest.program.mysql;

public class QRow {
    int id;
    String name;
    String datetime;
    String note;
    public String toString() {
        return(id+"\t"+name+"\t"+datetime+"\t"+note);
    }
}

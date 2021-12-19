package com.qtest.program.mysql;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataProcessorTest {
    @Test
    public void test1_queryQtableTest() throws ClassNotFoundException, SQLException {
        QRow targetRow= new QRow();
        targetRow.id=1;
        targetRow.name="row1";
        targetRow.datetime="2021-12-18 19:57:55";
        targetRow.note="note1";

        DataProcessor dataProcessor=new DataProcessor();
        List<QRow> resultRows = dataProcessor.queryQtable();
        assertEquals(targetRow.id, resultRows.get(0).id);
        assertEquals(targetRow.name, resultRows.get(0).name);
        assertEquals(targetRow.datetime, resultRows.get(0).datetime);
        assertEquals(targetRow.note, resultRows.get(0).note);
    }

    @Test
    public void test2_insertQtableTest() throws ClassNotFoundException {
        QRow targetRow= new QRow();
        targetRow.id=3;
        targetRow.name="row3";
        targetRow.datetime="2021-12-18 21:00:00.123";
        targetRow.note="note3";

        try {
            DataProcessor dataProcessor = new DataProcessor();
            int ret = dataProcessor.insertQtable();
            assertEquals(1, ret);

            List<QRow> resultRows = dataProcessor.queryQtable();
            assertEquals(targetRow.id, resultRows.get(2).id);
            assertEquals(targetRow.name, resultRows.get(2).name);
            assertEquals(targetRow.datetime, resultRows.get(2).datetime);
            assertEquals(targetRow.note, resultRows.get(2).note);
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                assertEquals("Duplicate entry '3' for key 'qtable.IDX_ID'", e.getMessage());
                System.out.println("Duplicate entry '3' for key 'qtable.IDX_ID'");
            } else {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test3_deleteQtableTest() throws ClassNotFoundException {
        QRow targetRow= new QRow();
        targetRow.id=3;
        targetRow.name="row3";
        targetRow.datetime="2021-12-18 21:00:00";
        targetRow.note="note3";

        try {
            DataProcessor dataProcessor = new DataProcessor();
            int ret = dataProcessor.deleteQtable();
            List<QRow> resultRows = dataProcessor.queryQtable();
            assertEquals(targetRow.id, resultRows.get(2).id);
            assertEquals(targetRow.name, resultRows.get(2).name);
            assertEquals(targetRow.datetime, resultRows.get(2).datetime);
            assertEquals(targetRow.note, resultRows.get(2).note);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 2 out of bounds for length 2", e.getMessage());
        }
    }

}

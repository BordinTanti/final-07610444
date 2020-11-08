package com.example.speedrecords.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.speedrecords.Record;

@Dao
public interface SpeedDao {
@Insert
void addRecord(Record...Records);

    @Query("SELECT * FROM Records")
    Record[] getAllRecord();

}

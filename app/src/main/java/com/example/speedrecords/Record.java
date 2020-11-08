package com.example.speedrecords;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Records")
public class Record {
    @PrimaryKey(autoGenerate = true)
    public int id ;
    public double cal ;
 public String speedKM ;
 public String detail ;

    public Record() {
    }

    @Ignore
    public Record(String speed, String detail,double cal) {
        this.speedKM = speed;
        this.detail = detail;
        this.cal = cal ;
    }



    public void setCal(double cal) {
        this.cal = cal;
    }
}

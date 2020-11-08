package com.example.speedrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.speedrecords.database.AppDatabase;
import com.example.speedrecords.util.AppExecutors;

import java.util.Locale;

public class AddRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Button saveBut = findViewById(R.id.saveButton);
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totalcount = 0;
                int overcount = 0 ;
                EditText distance = findViewById(R.id.distanceEditText);
                EditText duration = findViewById(R.id.durationEditText);
                String distanceText = distance.getText().toString();
                String durationText = duration.getText().toString();
                double distanceNum = Double.parseDouble(distanceText);
                double durationNum = Double.parseDouble(durationText);
                double cal = (distanceNum/durationNum)*3.6 ;
                if(cal > 80){
                    overcount++;
                    totalcount++;

                }else{
                    totalcount++;
                }
                String str = String.format(Locale.getDefault(),"%.1f METERS,%.1f SECONDS"
                        ,distanceNum,durationNum);
                String calText = String.format(Locale.getDefault(),"%.1f KM/H"
                        ,cal);

                final Record record = new Record( calText , str, cal);

                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() { // worker thread
                        AppDatabase db = AppDatabase.getInstance(AddRecordActivity.this);
                        db.speedDao().addRecord(record);
                        finish();
                    }
                });
                Intent i = new Intent(AddRecordActivity.this, MainActivity.class);
                i.putExtra("Totalcount",totalcount);
                i.putExtra("Overcount",overcount);
                startActivity(i);
            }
        });
    }
}
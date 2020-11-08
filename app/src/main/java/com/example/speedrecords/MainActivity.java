package com.example.speedrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.speedrecords.Adapter.SpeedAdapter;
import com.example.speedrecords.database.AppDatabase;
import com.example.speedrecords.util.AppExecutors;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
private RecyclerView mRecyclerView ;
    @Override
    protected void onResume() {
        super.onResume();

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final Record[] records = db.speedDao().getAllRecord();

                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                       SpeedAdapter adapter = new SpeedAdapter(MainActivity.this, records);
                        mRecyclerView.setAdapter(adapter);
                    }
                });


            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView total = findViewById(R.id.TotalTextview);
        TextView over = findViewById(R.id.OverlimitTextview);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.speedRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Button addButton = findViewById(R.id.AddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddRecordActivity.class);
                startActivity(i);
            }
        });
        /*int a = getIntent().getExtras().getInt("Totalcount");
        int b = getIntent().getExtras().getInt("Overcount");
        String str = String.format(Locale.getDefault(),"Total : %d"
                ,a);
        String str2 = String.format(Locale.getDefault(),"Overlimit : %d"
                ,b);
        total.setText(str);
        over.setText(str2);*/
    }
}
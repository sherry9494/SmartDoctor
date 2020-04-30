package com.fyp.smartdoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    String namestr;
    TextView Name, bpm, prediction;
    FirebaseDatabase database;
    DatabaseReference bpmRef, predic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            namestr = extras.getString("NAME");
        }

        database = FirebaseDatabase.getInstance();
        bpmRef = database.getReference("BPM");
        predic = database.getReference("prediction");

        Name = findViewById(R.id.home_name);
        bpm = findViewById(R.id.home_BPM);
        prediction = findViewById(R.id.home_prediction);
        Name.setText("Welcome "+namestr);

        final ProgressDialog dialog = new ProgressDialog(HomeActivity.this);
        dialog.setMessage("Fetching Data...");
        dialog.show();


        bpmRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                BPM_Model bpmStr = dataSnapshot.getValue(BPM_Model.class);
                bpm.setText("Result: "+bpmStr.Value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        predic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dialog.dismiss();

                Prediction_Model bpmStr = dataSnapshot.getValue(Prediction_Model.class);
                prediction.setText("Result: "+bpmStr.Value);
                Toast.makeText(HomeActivity.this, bpmStr.Value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

package com.example.rahulbhenjalia.user_parking_navigator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Availability extends AppCompatActivity {

    TextView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);


        FirebaseDatabase.
                getInstance().getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/CurrentSlots/").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        int CountO = 0,CountU = 0;

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                        {
                            CurrentSlot clt = postSnapshot.getValue(CurrentSlot.class);

                            if(clt.getStatus().equals("UnOccupied")) { CountU++;}
                            CountO++;

                        }

                        iv  = (TextView)findViewById(R.id.iv);
                        String x = "Near Parking Mall"+"\n"+"TOTAL SPOTS: "+CountO+
                                                       "\n"+"AVAILABLE SPOTS: "+CountU;
                        iv.setText(x);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}

package com.example.rahulbhenjalia.user_parking_navigator;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class history extends AppCompatActivity {


    private TextView tv,jtxt,slno;
    private Button clk,ava;
    private String phn,sltno;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);




        Log.d("purav","HELLOx");
        Intent i = getIntent();
        phn = i.getStringExtra("mob");

        Log.d("purav","HELLO"+phn);
        jtxt = (TextView)findViewById(R.id.txt);
        tv = (TextView)findViewById(R.id.stat);
        slno = (TextView)findViewById(R.id.slt);
        clk = (Button)findViewById(R.id.nav);
        ava = (Button)findViewById(R.id.ava);



        jtxt.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        slno.setVisibility(View.INVISIBLE);
        clk.setVisibility(View.INVISIBLE);
        ava.setVisibility(View.INVISIBLE);

        FirebaseDatabase.
                getInstance().
                getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/UpcomingSlots/").
                addValueEventListener(
                        new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {


                                   int flag = 0;


                                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                        final UpcomingSlots upcomingSlots = postSnapshot.getValue(UpcomingSlots.class);

                                        if(upcomingSlots.getPhone().equals(phn))
                                        {
                                            flag = 1;
                                            jtxt.setVisibility(View.VISIBLE);
                                            clk.setVisibility(View.VISIBLE);
                                            slno.setVisibility(View.VISIBLE);
                                            slno.setText(upcomingSlots.getSlotNo());
                                            sltno = upcomingSlots.getSlotNo();

                                            clk.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent i = new Intent(history.super.getApplicationContext(),Map_Navigation.class);
                                                    Log.d("purav","SLOT NO: "+upcomingSlots.SlotNo);
                                                    i.putExtra("slt",upcomingSlots.getSlotNo());
                                                    startActivity(i);
                                                }
                                            });

                                            break;
                                        }


                                    }

                                    if(flag == 0  )
                                    {

                                        tv.setVisibility(View.VISIBLE);
                                        tv.setText("NO CURRENT ALLOTMENTS");
                                        ava.setVisibility(View.VISIBLE);

                                        ava.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent i = new Intent(history.super.getApplicationContext(),Availability.class);
                                                startActivity(i);
                                            }
                                        });


                                    }





                                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onBackPressed() {
        // Simply Do noting!
    }
}

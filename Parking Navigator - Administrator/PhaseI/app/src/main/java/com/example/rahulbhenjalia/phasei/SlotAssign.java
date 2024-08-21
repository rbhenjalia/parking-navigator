package com.example.rahulbhenjalia.phasei;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Connection;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class SlotAssign extends AppCompatActivity {
    private String uname,email,phone,vehicleno,id,no,s_time,dt;

    Boolean a = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_assign);

        Calendar c = Calendar.getInstance();

        long timeStamp = c.getTimeInMillis();
        s_time = String.valueOf(timeStamp);

        SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy");
        Date  date = Calendar.getInstance().getTime();
        dt = sd.format(date);

        Intent in = getIntent();
        uname = in.getStringExtra("uname");
        email = in.getStringExtra("email");
        phone = in.getStringExtra("phone");
        vehicleno = in.getStringExtra("vehicleno");

        Log.d("chkk",uname+""+phone);
        DatabaseReference dB = FirebaseDatabase.
                getInstance().
                getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/CurrentSlots/");

        dB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {


                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        CurrentSlot obj = postSnapshot.getValue(CurrentSlot.class);

                        if(obj.getStatus().equals("UnOccupied") && !a)
                        {
                            id = "Slot"+obj.getSlotNo();
                            no = obj.getSlotNo();
                            a=true;
                            break;
                        }
                    }


                    DatabaseReference SLOT = FirebaseDatabase.
                            getInstance().
                            getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/CurrentSlots/"+id);


                    DatabaseReference UserName = SLOT.child("Username");
                    UserName.setValue(uname);

                    DatabaseReference EMail = SLOT.child("Email");
                    EMail.setValue(email);

                    DatabaseReference VehicleNo = SLOT.child("VehicleNo");
                    VehicleNo.setValue(vehicleno);

                    DatabaseReference Phone = SLOT.child("Phone");
                    Phone.setValue(phone);

                    DatabaseReference StartTime = SLOT.child("StartTime");
                    StartTime.setValue(s_time);

                    final DatabaseReference SlotNo = SLOT.child("SlotNo");
                    SlotNo.setValue(no);

                    DatabaseReference Status = SLOT.child("Status");
                    Status.setValue("Occupied");

                    DatabaseReference Date = SLOT.child("Date");
                    Date.setValue(dt);

                    DatabaseReference SLT = FirebaseDatabase.
                            getInstance().
                            getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/UpcomingSlots/"+phone);

                    DatabaseReference Phone1 = SLT.child("Phone");
                    Phone1.setValue(phone);
                    DatabaseReference SlotNo1 = SLT.child("SlotNo");
                    SlotNo1.setValue(no);



                    Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_SHORT).show();
                    final Handler handler = new Handler();


                    boolean flag=false;

                    StringBuilder sb = new StringBuilder(phone);

                    String s = sb.substring(3,sb.length());
                    Log.d("bhenju",s);
                    new SMS().execute(s, "YOU HAVE BEEN ALLOTTED SLOT NO : "+no+"\n" +
                            "CLICK THE GIVENT LINK TO OPEN NAVIGATION "+"\n\n\n" +
                            "http://navigateto.park.com");


                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {



                            Intent next = new Intent(SlotAssign.super.getApplicationContext(),CurrentPanel.class);
                            startActivity(next);
                        }
                    }, 5000);


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

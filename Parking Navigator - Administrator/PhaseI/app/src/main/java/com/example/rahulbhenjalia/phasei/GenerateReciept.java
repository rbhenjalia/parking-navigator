package com.example.rahulbhenjalia.phasei;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class GenerateReciept extends AppCompatActivity {


    String e_time,slotno;

    private TextView name,rec_no,date,stime,etime,amt,contact;
    private Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_reciept);


        name = (TextView)findViewById(R.id.name);
        rec_no = (TextView)findViewById(R.id.rec_no);
        stime = (TextView)findViewById(R.id.stime);
        etime = (TextView)findViewById(R.id.etime);
        amt = (TextView)findViewById(R.id.amt);
        contact = (TextView)findViewById(R.id.contact);
        date =(TextView)findViewById(R.id.date);

        confirm = (Button)findViewById(R.id.cnf);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),CurrentPanel.class);
                startActivity(in);
            }
        });
        Log.d("chk1","TextViews Created");

        Intent in = getIntent();
        e_time = in.getStringExtra("etime");
        slotno = in.getStringExtra("num");



        Log.d("chk1","Intent Got : "+slotno);



        FirebaseDatabase.
                getInstance().
                getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/CurrentSlots/Slot"+slotno+"/").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        CurrentSlot slot = dataSnapshot.getValue(CurrentSlot.class);


                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                        Query query = ref.child("UpcomingSlots").orderByChild("Phone").equalTo(slot.getPhone());



                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                                {

                                    snapshot.getRef().removeValue();
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        Log.d("chk1","Snapshot Recieved");


                        Random rnd = new Random();
                        int n = 100000 + rnd.nextInt(999999);

                        Log.d("chk1","Random Number Generated : "+n);

                        Log.d("chk1","History Reference Created");


                        long s = Long.parseLong(slot.getStartTime());
                        long e = Long.parseLong(e_time);

                        long p = e - s;

                        long minutes = (p/1000)/60;

                        long amount = minutes * 5;

                        Log.d("chk1","Amount Calculated:"+amount);


                        StringBuilder sb = new StringBuilder(slot.getPhone());

                        String N = n+""+sb.substring(3,sb.length());

                        new SMS().execute(sb.substring(3,sb.length()),
                                "YOUR PARKING TICKET NO IS "+N+"\n" +
                                "PLEASE PAY AMOUNT RS. "+amount+ " TO THE OPERATOR AT THE END GATE "+
                                "\n OR.. PAY USING PHONEPE HERE "+
                                "http://LINK TO PHONE PE.com");

                        DatabaseReference SLOTX = FirebaseDatabase.
                                getInstance().
                                getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/History/"+ N+"/");


                        Log.d("chk1","ref created:");
                        DatabaseReference UserNameX = SLOTX.child("Username");
                        UserNameX.setValue(slot.getUsername());
                        Log.d("chk1","ref1 created:");
                        name.setText(slot.getUsername());
                        Log.d("chk1","ref2 created:");
                        rec_no.setText(String.valueOf(N));
                        Log.d("chk1","ref3 created:");
                        DatabaseReference EMailX = SLOTX.child("Email");



                        DatabaseReference VehicleNoX = SLOTX.child("VehicleNo");
                        VehicleNoX.setValue(slot.getVehicleNo());
                        Log.d("chk1","Create History Check 1");

                        DatabaseReference PhoneX = SLOTX.child("Phone");
                        PhoneX.setValue(slot.getPhone());

                        contact.setText(slot.getPhone());

                        Timestamp ts = new Timestamp(s);
                        Date dte=new Date(ts.getTime());
                        SimpleDateFormat sd = new SimpleDateFormat("hh:mm a");
                        String t = sd.format(dte);

                        stime.setText(t);

                        DatabaseReference StartTimeX = SLOTX.child("StartTime");
                        StartTimeX.setValue(t);

                        Log.d("chk1","Create History Check 1");


                        Timestamp ts1 = new Timestamp(e);
                        Date dte1=new Date(ts1.getTime());
                        SimpleDateFormat sd1 = new SimpleDateFormat("hh:mm a");
                        String t1 = sd1.format(dte1);

                        DatabaseReference EndTimeX = SLOTX.child("EndTime");
                        EndTimeX.setValue(t1);

                        etime.setText(t1);

                        DatabaseReference SlotNoX = SLOTX.child("SlotNo");
                        SlotNoX.setValue("" + slotno);

                        DatabaseReference DateX = SLOTX.child("Date");
                        DateX.setValue(slot.getDate());




                        date.setText(slot.getDate());
                        Log.d("chk1","Create History Check 1");

                        DatabaseReference Amount = SLOTX.child("Amount");
                        Amount.setValue(String.valueOf(amount));

                        amt.setText(String.valueOf(amount));


                        Log.d("chk1","Create History Check 1");

                        DatabaseReference SLOT = FirebaseDatabase.
                                getInstance().
                                getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/CurrentSlots/Slot" + slotno+"/");



                        Log.d("chk1","Clear Slot 1");

                        DatabaseReference UserName = SLOT.child("Username");
                        UserName.setValue("");

                        DatabaseReference EMail = SLOT.child("Email");
                        EMail.setValue("");

                        DatabaseReference VehicleNo = SLOT.child("VehicleNo");
                        VehicleNo.setValue("");

                        DatabaseReference Phone = SLOT.child("Phone");
                        Phone.setValue("");


                        Log.d("chk1","Clear");


                        DatabaseReference StartTime = SLOT.child("StartTime");
                        StartTime.setValue("");

                        DatabaseReference SlotNo = SLOT.child("SlotNo");
                        SlotNo.setValue("" + slotno);

                        DatabaseReference Status = SLOT.child("Status");
                        Status.setValue("UnOccupied");

                        DatabaseReference Date = SLOT.child("Date");
                        Date.setValue("");



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

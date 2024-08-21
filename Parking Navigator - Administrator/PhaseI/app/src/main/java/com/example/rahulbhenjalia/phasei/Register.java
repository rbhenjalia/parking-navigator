package com.example.rahulbhenjalia.phasei;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    private String carno;
    private String uname,email,phone,userid,vehicleno;

    private EditText name,contact;
    private TextView cno;
    private Button cnfrm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent in = getIntent();
        carno = in.getStringExtra("carno");


        char[] a = carno.toCharArray();
        StringBuilder ab = new StringBuilder("");
        for(int i=0;i<carno.length();i++)
        {
            if(a[i] != ' ')
            {
                ab.append(a[i]);
            }
        }

        carno = ab.toString();
        name = (EditText)findViewById(R.id.name);
        contact = (EditText)findViewById(R.id.contact);

        cno = (TextView)findViewById(R.id.cno);
        cnfrm = (Button)findViewById(R.id.cnfrm);


        name.setVisibility(View.INVISIBLE);
        contact.setVisibility(View.INVISIBLE);

        cno.setVisibility(View.INVISIBLE);
        cnfrm.setVisibility(View.INVISIBLE);

        DatabaseReference dB = FirebaseDatabase.
                getInstance().
                getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/RegisteredUsers/");

        dB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChildren()) {

                    int flag = 0;
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        RegisteredUsers obj = postSnapshot.getValue(RegisteredUsers.class);

                        if(obj.getVehicleNo().equals(carno))
                        {
                            uname = obj.getName();
                            vehicleno = obj.getVehicleNo();

                            phone = obj.getPhone();

                            name.setVisibility(View.VISIBLE);
                            contact.setVisibility(View.VISIBLE);
                            cno.setVisibility(View.VISIBLE);
                            cnfrm.setVisibility(View.VISIBLE);

                            name.setText(obj.getName());
                            contact.setText(obj.getPhone());
                            cno.setText(obj.getVehicleNo());

                            name.setEnabled(false);

                            contact.setEnabled(false);


                            flag = 1;

                            break;
                        }

                    }

                    if(flag == 0)
                    {
                        name.setVisibility(View.VISIBLE);
                        contact.setVisibility(View.VISIBLE);
                        cno.setVisibility(View.VISIBLE);
                        cnfrm.setVisibility(View.VISIBLE);


                        uname = name.getText().toString();
                        vehicleno = carno;
                        email = "";
                        userid = "";
                        phone = "+91"+contact.getText().toString();



                        cno.setText(carno);

                    }



                    cnfrm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(name.getText().toString().equals(""))
                            {
                                Toast.makeText(Register.super.getApplicationContext(),"ENTER NAME",Toast.LENGTH_SHORT).show();
                            }

                            else
                            {

                                if(contact.getText().toString().equals(""))
                                {
                                    Toast.makeText(Register.super.getApplicationContext(),"ENTER CONTACT",Toast.LENGTH_SHORT).show();
                                }

                                else
                                {
                                   Intent in = new Intent(Register.super.getApplicationContext(),SlotAssign.class);
                                   in.putExtra("uname",name.getText().toString());
                                   in.putExtra("userid",userid);
                                   in.putExtra("vehicleno",vehicleno);
                                   in.putExtra("email",email);
                                   in.putExtra("phone",contact.getText().toString());
                                    Log.d("chkk",uname+"  "+userid+"  "+vehicleno);
                                   startActivity(in);


                                }


                            }

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

    }


    @Override
    public void onBackPressed() {
        // Simply Do noting!
    }
}

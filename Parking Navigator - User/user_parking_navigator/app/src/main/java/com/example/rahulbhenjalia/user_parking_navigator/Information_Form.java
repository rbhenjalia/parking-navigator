package com.example.rahulbhenjalia.user_parking_navigator;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Information_Form extends AppCompatActivity {

    EditText nn,mobi,car;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information__form);

        Intent i = getIntent();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        final String id = auth.getCurrentUser().getUid();

        final String mob = i.getStringExtra("mob");
        final String carno = i.getStringExtra("carno");

        nn = (EditText)findViewById(R.id.nme);
        mobi = (EditText)findViewById(R.id.phno);
        car = (EditText)findViewById(R.id.carno);
        btn = (Button)findViewById(R.id.cnfirm);

        mobi.setText(mob);
        car.setText(carno);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!nn.getText().toString().equals(""))
                {


                    DatabaseReference SLOT = FirebaseDatabase.
                            getInstance().
                            getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/RegisteredUsers/"+mob

                            );


                    DatabaseReference UserName = SLOT.child("Name");
                    UserName.setValue(nn.getText().toString());

                    DatabaseReference VehicleNo = SLOT.child("VehicleNo");
                    VehicleNo.setValue(carno);

                    DatabaseReference Phone = SLOT.child("Phone");
                    Phone.setValue(mob);



                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent in = new Intent(getApplicationContext(),history.class);
                            startActivity(in);
                        }
                    }, 3000);

                }

                else
                {
                    Toast.makeText(getApplicationContext(),"ENTER YOUR NAME PLEASE",Toast.LENGTH_LONG).show();
                }

            }
        });




    }


    @Override
    public void onBackPressed() {
        // Simply Do noting!
    }
}

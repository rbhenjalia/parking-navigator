package com.example.rahulbhenjalia.phasei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GoogleSignIN extends AppCompatActivity {
    EditText edt;
    Button cnfrm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        cnfrm = (Button) findViewById(R.id.cnfrm);
        edt = (EditText) findViewById(R.id.autho);


        cnfrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edt.getText().toString().equals("1919"))
                {
                    Intent n = new Intent(getApplicationContext(),CurrentPanel.class);
                    startActivity(n);
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        // Simply Do noting!
    }
}

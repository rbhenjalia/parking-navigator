package com.example.rahulbhenjalia.user_parking_navigator;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Sign_up extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    private EditText mob_no;
    private EditText code;
    private Button verify;

    private String mVerificationId;


    private TextView merror;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mob_no= findViewById(R.id.mob_no);
        code= findViewById(R.id.code);
        code.setVisibility(View.INVISIBLE);

        verify = findViewById(R.id.verify);
        verify.setText("Send Code");


        mAuth = FirebaseAuth.getInstance();
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String phone_no = mob_no.getText().toString();

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phone_no,
                        60,
                        TimeUnit.SECONDS,
                        Sign_up.this,
                        mCallbacks
                        );
            }
        });



        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
            {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e)
            {
                merror.setText("Error in verification");
                merror.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                code.setVisibility(View.VISIBLE);

                verify.setText("Verify");
                // ...
            }


        };

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                           FirebaseUser user = task.getResult().getUser();

                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            i.putExtra("mob","+91"+mob_no.getText().toString());
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            // Sign in failed, display a message and update the UI

                            merror.setText("Error in logging in");
                            merror.setVisibility(View.VISIBLE);

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null)
        {
            Intent i = new Intent(this,history.class);
            i.putExtra("mob",currentUser.getPhoneNumber());
            startActivity(i);
            finish();
        }
    }


    /*void verify_phone_no(String phno)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phno,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks); // OnVerificationStateChangedCallbacks
    }*/

    @Override
    public void onBackPressed() {
        // Simply Do noting!
    }

}

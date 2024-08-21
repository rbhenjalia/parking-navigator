package com.example.rahulbhenjalia.phasei;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchByCar extends AppCompatActivity {
    private List<History> mainList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HistoryAdapter mAdapter;
    private TextView tv;
    Context c = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);

        Intent i  = getIntent();
        String name = i.getStringExtra("name");

        Log.d("chkk","In Activity");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        mAdapter = new HistoryAdapter(mainList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        Log.d("chkk","Recycler View Set");
        prepareData(name);

    }

    private void prepareData(final String name)
    {


        tv = (TextView)findViewById(R.id.result);

        Toast.makeText(getApplicationContext(),"IN PREPARE DATA",Toast.LENGTH_SHORT).show();
        FirebaseDatabase.
                getInstance().
                getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/History/").
                addValueEventListener
                        (new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                boolean empty = true;
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                    History history = postSnapshot.getValue(History.class);
                                    Log.d("chkk", "ADDING : " + history.getUsername());
                                    if (history.getUsername().equalsIgnoreCase(name)) {
                                        empty = false;
                                        mainList.add(history);
                                    }
                                }
                                Log.d("chkk", "LIST CREATED");
                                if (empty)
                                {
                                    tv.setText("NO RESULTS FOUND");
                                }
                                else {
                                    mAdapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);

        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);

        switch (id)
        {

            case R.id.current_panel:
                Intent i = new Intent(getApplicationContext(),CurrentPanel.class);
                startActivity(i);
                return true;
            case R.id.recents:
                Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.search_by_name:

                Toast.makeText(getApplicationContext(),"ABC",Toast.LENGTH_LONG).show();
                 alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                                Intent i = new Intent(getApplicationContext(),SearchByName.class);
                                if(userInputDialogEditText.getText().toString().equals(""))
                                {
                                    userInputDialogEditText.setHint("PLEASE ENTER SOMETHING");
                                }
                                else {
                                    i.putExtra("name", userInputDialogEditText.getText().toString());
                                    startActivity(i);
                                }
                            }
                        })

                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid1 = alertDialogBuilderUserInput.create();
                alertDialogAndroid1.show();

                return true;

            case R.id.search_by_car:

                Toast.makeText(getApplicationContext(),"ABC",Toast.LENGTH_LONG).show();
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                                Intent i = new Intent(getApplicationContext(),SearchByCar.class);
                                if(userInputDialogEditText.getText().toString().equals(""))
                                {
                                    userInputDialogEditText.setHint("PLEASE ENTER SOMETHING");
                                }
                                else {
                                    i.putExtra("name", userInputDialogEditText.getText().toString());
                                    startActivity(i);
                                }
                            }
                        })

                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid2 = alertDialogBuilderUserInput.create();
                alertDialogAndroid2.show();

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        // Simply Do noting!
    }

}

package com.example.rahulbhenjalia.phasei;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CurrentPanel extends AppCompatActivity {

    private int i;
    private ArrayAdapter<String> adapter;
    // You could do it as well generic, that's what I do in my lib:
    public interface SimpleCallback<T> {
        void callback(T data);
    }

    ListView listView;
    Context c = this;
    ArrayList<String> a;
    private String n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_panel);

/*
        FirebaseDatabase ref  = FirebaseDatabase.getInstance();

        for (i = 0; i < 8; i++) {


                    DatabaseReference SLOT = ref.
                            getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/CurrentSlots/Slot"+(i+1)+"/");


                    DatabaseReference UserName = SLOT.child("Username");
                    UserName.setValue("");

                    DatabaseReference VehicleNo = SLOT.child("VehicleNo");
                    VehicleNo.setValue("");

                    DatabaseReference Phone = SLOT.child("Phone");
                    Phone.setValue("");

                    DatabaseReference StartTime = SLOT.child("StartTime");
                    StartTime.setValue("");

                    DatabaseReference SlotNo = SLOT.child("SlotNo");
                    SlotNo.setValue("" + (i + 1));

                    DatabaseReference Status = SLOT.child("Status");
                    Status.setValue("UnOccupied");

                    DatabaseReference Date = SLOT.child("Date");
                    Date.setValue("");

        }
*/

        SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy");
        Date c = Calendar.getInstance().getTime();
        String d = sd.format(c);
        Toast.makeText(getApplicationContext(),d,Toast.LENGTH_LONG).show();

        final String vehic = "";

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);

            }
        });



        DatabaseReference dB = FirebaseDatabase.
                               getInstance().
                               getReferenceFromUrl("https://friday-8a7cb.firebaseio.com/CurrentSlots/");

        Toast.makeText(this,"data got",Toast.LENGTH_LONG);
        dB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                a = new ArrayList<String>();
                if(dataSnapshot.hasChildren()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        CurrentSlot obj = postSnapshot.getValue(CurrentSlot.class);

                       if(obj.getStatus().equals("UnOccupied")) {
                           a.add("Slot - " + obj.getSlotNo() + " * \n" + obj.getStatus());
                       }
                       else
                       {
                           a.add("Slot - " + obj.getSlotNo() + "\nVehicle No:  " + obj.getVehicleNo());
                       }
                       }
                }

                listView=(ListView)findViewById(R.id.listView);
                listView.setEmptyView(findViewById(R.id.listView));
                adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,a);



                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,a) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View row = super.getView(position, convertView, parent);

                        Toast.makeText(CurrentPanel.super.getApplicationContext(),getItem(position),Toast.LENGTH_LONG).show();

                        String p = getItem(position);
                        if(p.charAt(9) == '*')
                        {
                            row.setBackgroundColor (Color.WHITE);
                            row.setContextClickable(true);// some color

                        }
                        else
                        {
                            // default state
                            row.setBackgroundColor (Color.GRAY); // default coloe
                            row.setContextClickable(false);


                        }
                        return row;
                    }
                });
                // Register the ListView  for Context menu
                registerForContextMenu(listView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_homeview, menu);
        menu.setHeaderTitle("SELECT AN ACTION");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        View view = info.targetView;

        if(item.getItemId()==R.id.EndTime){

            Calendar c = Calendar.getInstance();
            Intent in = new Intent(getApplicationContext(),GenerateReciept.class);
            long timeStamp = c.getTimeInMillis();
            String e_time = String.valueOf(timeStamp);
            in.putExtra("etime",e_time);
            in.putExtra("num",String.valueOf(index+1));
            startActivity(in);
        }

        else if(item.getItemId()==R.id.ViewInfo){
            Toast.makeText(getApplicationContext(),"Viewing Info",Toast.LENGTH_LONG).show();
        }
        else{
            return false;
        }
        return true;
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
                Intent in = new Intent(getApplicationContext(),Recents.class);
                startActivity(in);
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



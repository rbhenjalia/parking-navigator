package com.example.rahulbhenjalia.phasei;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by DELL on 12/2/2018.
 */

public class SMS extends AsyncTask<String,Void,Bitmap>
{

    @Override
    protected Bitmap doInBackground(String[] phno)
    {

        Log.d("bhenju","Got phno "+phno[0]);
        //Your authentication key
        String authkey = "218142ATwJm4bmZ25b0fccc3";
//Multiple mobiles numbers separated by comma
        String mobiles = phno[0];
//Sender ID,While using route4 sender id should be 6 characters long.
        String senderId = "HELLIO";
//Your message to send, Add URL encoding here.
        String message = "Hello "+phno[1];
//define route
        String route="4";

        URLConnection myURLConnection=null;
        URL myURL=null;
        BufferedReader reader=null;

//encoding message
        String encoded_message= URLEncoder.encode(message);

//Send SMS API
        String mainUrl="http://api.msg91.com/api/sendhttp.php?";

//Prepare parameter string
        StringBuilder sbPostData= new StringBuilder(mainUrl);
        sbPostData.append("authkey="+authkey);
        sbPostData.append("&mobiles="+mobiles);
        sbPostData.append("&message="+encoded_message);
        sbPostData.append("&route="+route);
        sbPostData.append("&sender="+senderId);

//final string
        mainUrl = sbPostData.toString();
        try
        {
            //prepare connection
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));

            //reading response
            String response;
            while ((response = reader.readLine()) != null)
                //print response
                Log.d("RESPONSE", ""+response);

            //finally close connection
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}

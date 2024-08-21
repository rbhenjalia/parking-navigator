package com.example.rahulbhenjalia.user_parking_navigator;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;


import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class Map_Navigation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int slot_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map__navigation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent i = getIntent();
        Log.d("purav","RECIEVED SLOT NO: "+i.getStringExtra("slt"));
        slot_no = Integer.parseInt(i.getStringExtra("slt"));
        //slot_no=5;

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(23.039450, 72.53100),
                        new LatLng(23.039450, 72.531000),
                        new LatLng(23.039050, 72.531000),
                        new LatLng(23.039050, 72.531600),
                        new LatLng(23.039450, 72.531600));

        Polyline mainLine1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(23.039350,72.531150),

                        new LatLng(23.039100, 72.531150)));

        Polyline mainLine2 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(23.039350,72.531350),

                        new LatLng(23.039100, 72.531350)));

        Polyline subLine11 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(23.039300,72.531150),

                        new LatLng(23.039300, 72.531250)));

        Polyline subLine12 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(23.039250,72.531150),

                        new LatLng(23.039250, 72.531250)));

        Polyline subLine13 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(23.039200,72.531150),

                        new LatLng(23.039200, 72.531250)));

        Polyline subLine14 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(23.039150,72.531150),

                        new LatLng(23.039150, 72.531250)));

        Polyline subLine21 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(23.039300,72.531350),

                        new LatLng(23.039300, 72.531450)));

        Polyline subLine22 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(23.039250,72.531350),

                        new LatLng(23.039250, 72.531450)));

        Polyline subLine23 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(23.039200,72.531350),

                        new LatLng(23.039200, 72.531450)));

        Polyline subLine24 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(23.039150,72.531350),

                        new LatLng(23.039150, 72.531450)));

        switch(slot_no)
        {
            case 1 :

                Polyline route_to_1 = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(23.039100, 72.531600),
                                new LatLng(23.039100, 72.531500),
                                new LatLng(23.039325, 72.531500),
                                new LatLng(23.039325, 72.531400)).color(Color.BLUE));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(23.039325,72.531400))
                        .title("Parking Lot 1")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                break;

            case 2:

                Polyline route_to_2 = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(23.039100, 72.531600),
                                new LatLng(23.039100, 72.531500),
                                new LatLng(23.039275, 72.531500),
                                new LatLng(23.039275, 72.531400)).color(Color.BLUE));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(23.039275,72.531400))
                        .title("Parking Lot 2")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                break;

            case 3:

                Polyline route_to_3 = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(23.039100, 72.531600),
                                new LatLng(23.039100, 72.531500),
                                new LatLng(23.039225, 72.531500),
                                new LatLng(23.039225, 72.531400)).color(Color.BLUE));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(23.039225,72.531400))
                        .title("Parking Lot 3")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                break;

            case 4:

                Polyline route_to_4 = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(23.039100, 72.531600),
                                new LatLng(23.039100, 72.531500),
                                new LatLng(23.039175, 72.531500),
                                new LatLng(23.039175, 72.531400)).color(Color.BLUE));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(23.039175,72.531400))
                        .title("Parking Lot 4")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                break;

                    /*case 5:

                        Polyline route_to_5 = mMap.addPolyline(new PolylineOptions()
                                .clickable(true)
                                .add(
                                        new LatLng(23.039100, 72.531600),
                                        new LatLng(23.039100, 72.531500),
                                        new LatLng(23.039125, 72.531500),
                                        new LatLng(23.039125, 72.531400)));

                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(23.039125,72.531400))
                                .title("Parking Lot 5"));
                        break;
*/
            case 5:

                Polyline route_to_6 = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(23.039100, 72.531600),
                                new LatLng(23.039100, 72.531500),
                                new LatLng(23.039400, 72.531500),
                                new LatLng(23.039400, 72.531300),
                                new LatLng(23.039325, 72.531300),
                                new LatLng(23.039325, 72.531200)).color(Color.BLUE));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(23.039325, 72.531200))
                        .title("Parking Lot 5")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                break;

            case 6:

                Polyline route_to_7 = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(23.039100, 72.531600),
                                new LatLng(23.039100, 72.531500),
                                new LatLng(23.039400, 72.531500),
                                new LatLng(23.039400, 72.531300),
                                new LatLng(23.039275, 72.531300),
                                new LatLng(23.039275, 72.531200)).color(Color.BLUE));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(23.039275, 72.531200))
                        .title("Parking Lot 6")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                break;

            case 7:

                Polyline route_to_8 = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(23.039100, 72.531600),
                                new LatLng(23.039100, 72.531500),
                                new LatLng(23.039400, 72.531500),
                                new LatLng(23.039400, 72.531300),
                                new LatLng(23.039225, 72.531300),
                                new LatLng(23.039225, 72.531200)).color(Color.BLUE));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(23.039225, 72.531200))
                        .title("Parking Lot 7")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                break;

            case 8:

                Polyline route_to_9 = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(23.039100, 72.531600),
                                new LatLng(23.039100, 72.531500),
                                new LatLng(23.039400, 72.531500),
                                new LatLng(23.039400, 72.531300),
                                new LatLng(23.039175, 72.531300),
                                new LatLng(23.039175, 72.531200)).color(Color.BLUE));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(23.039175, 72.531200))
                        .title("Parking Lot 8")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                break;

                    /*case 10:

                        Polyline route_to_10 = mMap.addPolyline(new PolylineOptions()
                                .clickable(true)
                                .add(
                                        new LatLng(23.039100, 72.531600),
                                        new LatLng(23.039100, 72.531500),
                                        new LatLng(23.039400, 72.531500),
                                        new LatLng(23.039400, 72.531300),
                                        new LatLng(23.039125, 72.531300),
                                        new LatLng(23.039125, 72.531200)));

                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(23.039125, 72.531200))
                                .title("Parking Lot 10"));
                        break;

*/
        }


// Get back the mutable Polygon
        Polygon polygon = mMap.addPolygon(rectOptions);
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(23.039100, 72.531600))
                .title("YOU ARE AT THE ENTRANCE"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(23.039200, 72.531300), 19.25f));


    }
}

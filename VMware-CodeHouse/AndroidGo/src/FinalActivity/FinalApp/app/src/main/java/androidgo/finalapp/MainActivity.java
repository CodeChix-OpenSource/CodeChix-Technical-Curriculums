package androidgo.finalapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidgo.finalapp.model.Contact;
import androidgo.finalapp.util.DatabaseConnector;

/**
 * Save My Soul: This application allows the user to send an SOS message to a list of contacts.
 */

/**
 * Authors: Pallavi Santhosh Kumar
 *          Akansha Patel
 *          Shyama Sankar Vellore
 *          Ketki Haridas
 * Date: 08/16/2016
 *
 */
public class MainActivity extends AppCompatActivity {

    ArrayList<Contact> contacts = new ArrayList<>();
    DatabaseConnector databaseConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check to determine if app is being run for the first time
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        //Prompt user to register on first run
        if (isFirstRun) {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            Toast.makeText(MainActivity.this, "First Run: Register phone numbers",
                    Toast.LENGTH_LONG).show();
        }

        //Set the first run boolean to false
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();


        databaseConnector = new DatabaseConnector(this);

        contacts = databaseConnector.getAllContacts();

        String contactNames = databaseConnector.getContactNames();

        TextView friends = (TextView) findViewById(R.id.friendlist);
        //set friends list to names retrieved from database
        friends.setText("MY EMERGENCY CONTACTS:\n");
        friends.append(contactNames);
        friends.setTextColor(Color.BLUE);


        Button sendSOS = (Button) findViewById(R.id.sendSOS);
        sendSOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Implement send sms code
                for(int i = 0; i < contacts.size(); i++){
                    sendSMS(contacts.get(i).getPhoneNumber());
                }
            }
        });

        Button addEdit = (Button) findViewById(R.id.addEdit);
        addEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    protected void sendSMS(String contactNumber) {
        String sms = "HELP ME! I'm at " + getLocation();
        try {
            // Get an instance of SMS Manager which is used to send SMSs
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(contactNumber, null, sms, null, null); //send "sms" to "phoneNo"
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error: SMS not sent.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    protected String getLocation(){
        LocationManager locationManager;
        String provider;

        //Use location manager to request location services
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        //Select the best available location provider
        provider = locationManager.getBestProvider(criteria, false);
        provider = "network"; //for use only if device does not have a sim card

        Log.d("ServiceDebugger", "Provider: " + provider);

        //Check permissions for accessing location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("ServiceDebugger", "Permissions not provided");
        }

        //Get location from the provider
        final Location location = locationManager.getLastKnownLocation(provider);

        String latitude;
        String longitude;
        if (location != null) {
            //Get latitudes and longitudes from the location
            latitude = "" + location.getLatitude();
            longitude = "" + location.getLongitude();
            Log.d("ServiceDebugger", "Latitude: " + latitude + " Longitude: " + longitude);

            //Toast out the lat and long
            Toast.makeText(getBaseContext(), "Latitude: " + latitude + " Longitude: " + longitude,
                    Toast.LENGTH_SHORT).show();

        } else {
            Log.v("ServiceDebugger", "No location available");
            latitude = "NA";
            longitude = "NA";
        }

        return ("Latitude: " + latitude + " Longitude: " + longitude);
    }


}

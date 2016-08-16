package androidgo.androidservices;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Author: Pallavi Santhosh Kumar
 * Date: 08/16/2016
 * ServiceClass: Class that implements the service
 */
public class ServiceClass extends Service {

    String latitude = "";
    String longitude = "";

    @Override
    public void onCreate() {
        //Toast out a message to show that the service has been started
        Toast.makeText(getBaseContext(), "Android Service started", Toast.LENGTH_SHORT).show();


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
            return;
        }

        //Get location from the provider
        final Location location = locationManager.getLastKnownLocation(provider);

        //
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

        try {
            wait(2000); //Wait 2 seconds before shutting the app
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.v("ServiceDebugger", "Service Completed");
        //Toast out the end of the service
        Toast.makeText(getBaseContext(), "Android Service completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        /*
        Called when the service is stopped
         */
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}

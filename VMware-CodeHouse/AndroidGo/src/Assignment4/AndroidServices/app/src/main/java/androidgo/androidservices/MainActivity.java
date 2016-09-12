package androidgo.androidservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * AndroidServices: This applications demonstrates the use of Android Services that
 * run in the background. The application accesses GPS location (in the background)
 * and displays it in the foreground using a Toast message. The GPS location is
 * toasted out, and then the service auto-finishes 2 seconds after.
 *
 * Created by: Team AndroidGo
 */

/**
 * Author: Pallavi Santhosh Kumar
 * Date: 08/16/2016
 * MainActivity: Activity that starts the service
 */
public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Start the service that runs in the background
        Intent i = new Intent(MainActivity.this, ServiceClass.class);
        startService(i);
        //Close up the UI
        finish();
    }
}

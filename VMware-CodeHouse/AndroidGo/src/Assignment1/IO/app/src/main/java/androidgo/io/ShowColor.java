package androidgo.io;

import android.graphics.*;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Author: Ketki Haridas
 * Date: 08/16/2016
 * ShowColor: Activity that shows the color
 */
public class ShowColor extends AppCompatActivity {

    public final static String key = "ColorKEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_color);
        //Bind Intent from HomeActivity to ShowColor
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String Color = extras.getString(key);
            //The key argument here must match that used in the other activity
            //Use Image view to display color
            ImageView colorbox = (ImageView) findViewById(R.id.colorbox);
            Log.v("Debugging", "Color: " + Color);
            if (Color.equals("blue"))
                colorbox.setBackgroundColor(getResources().getColor(R.color.blue));

            else if (Color.equals("yellow"))
                colorbox.setBackgroundColor(getResources().getColor(R.color.yellow));


            else if (Color.equals("red"))
                colorbox.setBackgroundColor(getResources().getColor(R.color.red));


            else if (Color.equals("silver"))
                colorbox.setBackgroundColor(getResources().getColor(R.color.silver));


            else if (Color.equals("purple"))
                colorbox.setBackgroundColor(getResources().getColor(R.color.purple));

            else if (Color.equals("aqua"))
                colorbox.setBackgroundColor(getResources().getColor(R.color.aqua));


            else if (Color.equals("green"))
                colorbox.setBackgroundColor(getResources().getColor(R.color.green));

//If the input string is null
            else {
                Toast msg = Toast.makeText(getBaseContext(), "NOt a valid Color",
                        Toast.LENGTH_LONG);
                msg.show();
            }


        }
    }
}


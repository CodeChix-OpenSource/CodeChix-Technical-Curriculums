package androidgo.io;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Author: Ketki Haridas
 * Date: 08/16/2016
 * HomeActivity: Activity that accepts a color
 */
public class HomeActivity extends FragmentActivity {

    public final static String key = "ColorKEY";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final EditText editText = (EditText) findViewById(R.id.colorEditText);
        Button showColor = (Button)findViewById(R.id.button);
        showColor.setOnClickListener(new View.OnClickListener() {
            @Override
            //onclick event when the button show color is clicked
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ShowColor.class);
                String color = editText.getText().toString();
                //to carry the data type from one activity
                intent.putExtra(key, color);
                startActivity(intent);
            }
        });
    }
}

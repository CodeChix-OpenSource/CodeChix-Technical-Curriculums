package androidgo.communication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * Communication: An app that introduces communication APIs, file I/O and managing permissions
 */

/**
 * Authors: Akansha Patel
 * Date: 08/16/2016
 *
 */
public class MainActivity extends AppCompatActivity {

    Button sendButton;
    EditText phoneNumber;
    EditText textMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = (EditText)findViewById(R.id.phoneNumber);
        textMsg = (EditText)findViewById(R.id.textMsg);
        sendButton = (Button)findViewById(R.id.sendButton);

        //Set a listener on the send button
        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Button click action
                sendSMS();
            }
        });
        }

    protected void sendSMS() {
        String phoneNo = phoneNumber.getText().toString();
        String sms = textMsg.getText().toString();

        try {
            // Get an instance of SMS Manager which is used to send SMSs
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(phoneNo, null, sms, null, null); //send "sms" to "phoneNo"
            createFile(phoneNo, sms); //Create a file to store logs of sent messages
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error: SMS not sent.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    protected void createFile(String phoneNo, String sms){

        String filename = "SMSLog.txt";
        try{
            //create a file writer object with the given filename in external storage directory
            FileWriter fileWriter = new FileWriter(new File(
                    Environment.getExternalStorageDirectory().getPath() + "/" + filename), true);
            //append the phone number and the sms to the log file
            fileWriter.append(phoneNo + " : " + sms + "\n");
            Toast.makeText(getApplicationContext(),"File Created. Check External Directory",
                    Toast.LENGTH_LONG).show();
            fileWriter.close(); //close filewriter object
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"ERROR: File Not Created",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}

package androidgo.finalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidgo.finalapp.model.Contact;
import androidgo.finalapp.util.DatabaseConnector;

/**
 * Authors: Pallavi Santhosh Kumar
 *          Akansha Patel
 *          Shyama Sankar Vellore
 *          Ketki Haridas
 * Date: 08/16/2016
 * RegisterActivity: Activity that allows user to save contact data
 */
public class RegisterActivity extends AppCompatActivity {

    DatabaseConnector databaseConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText name1 = (EditText) findViewById(R.id.name1);
        final EditText phone1 = (EditText) findViewById(R.id.phone1);

        databaseConnector = new DatabaseConnector(this);

        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameString = name1.getText().toString();
                String phoneString = phone1.getText().toString();

                //Implement database functionality
                if (nameString.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Please enter a name",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if (phoneString.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Please enter a phone number",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                Contact contact = new Contact(nameString, phoneString);
                databaseConnector.insertData(contact);

                Toast.makeText(RegisterActivity.this, "Saved contact",
                        Toast.LENGTH_LONG).show();

                //Return to home screen on saving
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}

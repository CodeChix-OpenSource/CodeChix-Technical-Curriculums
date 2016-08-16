package androidgo.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidgo.model.Contact;
import androidgo.util.DatabaseConnector;

/**
 * Contacts: This app demonstrates the use of databases in Android apps.
 * The application lets the user to save contact information (name and
 * phone number) and displays the contact names.
 *
 * Created by: Team AndroidGo
 */

/**
 * Author: Shyama Sankar Vellore
 * Date: 08/16/2016
 * MainActivity
 */
public class MainActivity extends AppCompatActivity {
    private EditText contactName = null;
    private EditText contactNumber = null;
    private Button addContactButton = null;
    private TextView allContacts = null;

    private DatabaseConnector databaseConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Open the database connection
        databaseConnector = new DatabaseConnector(this);

        // Initialize views
        contactName = (EditText) findViewById(R.id.contact_name);
        contactNumber = (EditText) findViewById(R.id.contact_number);
        addContactButton = (Button) findViewById(R.id.add_contact_button);
        allContacts = (TextView) findViewById(R.id.all_contacts);
        initializeFields();

        // Display existing contact names
        String contactNames = databaseConnector.getContactNames();
        if (!contactNames.equals(""))
            allContacts.setText(contactNames);

        // Set on-click listeners
        addContactButton.setOnClickListener(addContactListener);

    }

    /**
     * addContactListener: OnClick listener for addContactButton
     */
    View.OnClickListener addContactListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Get user input
            String name = contactName.getText().toString();
            String number = contactNumber.getText().toString();

            // Verify user input
            if (name.equals("")) {
                Toast.makeText(MainActivity.this, "Please enter a name",
                        Toast.LENGTH_LONG).show();
                return;
            }
            if (number.equals("")) {
                Toast.makeText(MainActivity.this, "Please enter a phone number",
                        Toast.LENGTH_LONG).show();
                return;
            }

            // Insert the contact to database
            Contact contact = new Contact(name, number);
            databaseConnector.insertData(contact);

            Toast.makeText(MainActivity.this, "Saved contact",
                    Toast.LENGTH_LONG).show();

            // Get all contact names and display them
            String contactNames = databaseConnector.getContactNames();
            allContacts.setText(contactNames);
        }
    };


    /**
     * initializeFields: Initialize text fields
     */
    private void initializeFields() {
        contactName.setText("");
        contactNumber.setText("");
    }
}

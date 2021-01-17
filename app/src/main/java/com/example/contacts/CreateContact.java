package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity {

    EditText editTextNewLName;
    EditText editTextNewFName;
    EditText editTextNewEmail;
    EditText editTextNewBirthday;
    EditText editTextNewFix;
    EditText editTextNewMobile;
    EditText editTextNewFax;
    Button buttonNewSave;
    Button buttonNewCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        editTextNewLName = (EditText) findViewById(R.id.editTextNewLName);
        editTextNewFName = (EditText) findViewById(R.id.editTextNewFName);
        editTextNewEmail = (EditText) findViewById(R.id.editTextNewEmail);
        editTextNewBirthday = (EditText) findViewById(R.id.editTextNewBirthday);
        editTextNewFix = (EditText) findViewById(R.id.editTextNewFix);
        editTextNewMobile = (EditText) findViewById(R.id.editTextNewMobile);
        editTextNewFax = (EditText) findViewById(R.id.editTextNewFax);
        buttonNewSave = (Button) findViewById(R.id.buttonNewSave);
        buttonNewCancel = (Button) findViewById(R.id.buttonNewCancel);

        buttonNewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContactModel contactModel = null;

                try {
                    contactModel = new ContactModel(-1, editTextNewLName.getText().toString(), editTextNewFName.getText().toString(), editTextNewEmail.getText().toString(), editTextNewBirthday.getText().toString(), editTextNewFix.getText().toString(), editTextNewMobile.getText().toString(), editTextNewFax.getText().toString());

                } catch (Exception e) {
                    Toast.makeText(CreateContact.this, "Unable to create contact!", Toast.LENGTH_LONG).show();
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(CreateContact.this);
                boolean created = dataBaseHelper.addOne(contactModel);

                if (created) {
                    Intent intent = new Intent(CreateContact.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CreateContact.this, "Unable to create contact!", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonNewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateContact.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContact extends AppCompatActivity {

    EditText editTextUpdateLName;
    EditText editTextUpdateFName;
    EditText editTextUpdateEmail;
    EditText editTextUpdateBirthday;
    EditText editTextUpdateFix;
    EditText editTextUpdateMobile;
    EditText editTextUpdateFax;
    Button buttonUpdateSave;
    Button buttonUpdateCancel;
    Button buttonUpdateDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        editTextUpdateLName = (EditText) findViewById(R.id.editTextUpdateLName);
        editTextUpdateFName = (EditText) findViewById(R.id.editTextUpdateFName);
        editTextUpdateEmail = (EditText) findViewById(R.id.editTextUpdateEmail);
        editTextUpdateBirthday = (EditText) findViewById(R.id.editTextUpdateBirthday);
        editTextUpdateFix = (EditText) findViewById(R.id.editTextUpdateFix);
        editTextUpdateMobile = (EditText) findViewById(R.id.editTextUpdateMobile);
        editTextUpdateFax = (EditText) findViewById(R.id.editTextUpdateFax);
        buttonUpdateSave = (Button) findViewById(R.id.buttonUpdateSave);
        buttonUpdateCancel = (Button) findViewById(R.id.buttonUpdateCancel);
        buttonUpdateDelete = (Button) findViewById(R.id.buttonUpdateDelete);

        Intent intent = getIntent();

        String contactId = intent.getStringExtra("contactId");
        String contactFirstName = intent.getStringExtra("contactFirstName");
        String contactLastName = intent.getStringExtra("contactLastName");
        String contactEmail = intent.getStringExtra("contactEmail");
        String contactBirthday = intent.getStringExtra("contactBirthday");
        String contactFix = intent.getStringExtra("contactFix");
        String contactMobile = intent.getStringExtra("contactMobile");
        String contactFax = intent.getStringExtra("contactFax");

        editTextUpdateLName.setText(contactLastName);
        editTextUpdateFName.setText(contactFirstName);
        editTextUpdateEmail.setText(contactEmail);
        editTextUpdateBirthday.setText(contactBirthday);
        editTextUpdateFix.setText(contactFix);
        editTextUpdateMobile.setText(contactMobile);
        editTextUpdateFax.setText(contactFax);

        buttonUpdateSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactModel contactModel = null;

                try {
                    contactModel = new ContactModel(-1, editTextUpdateLName.getText().toString(), editTextUpdateFName.getText().toString(), editTextUpdateEmail.getText().toString(), editTextUpdateBirthday.getText().toString(), editTextUpdateFix.getText().toString(), editTextUpdateMobile.getText().toString(), editTextUpdateFax.getText().toString());

                } catch (Exception e) {
                    Toast.makeText(UpdateContact.this, "Unable to update contact!", Toast.LENGTH_LONG).show();
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(UpdateContact.this);
                boolean updated = dataBaseHelper.updateOne(contactId, contactModel);

                if (updated) {
                    Intent intent = new Intent(UpdateContact.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UpdateContact.this, "Unable to update contact!", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonUpdateCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateContact.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        buttonUpdateDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(UpdateContact.this);
                boolean deleted = dataBaseHelper.deleteOne(contactId);

                if (deleted) {
                    Intent intent = new Intent(UpdateContact.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UpdateContact.this, "Unable to delete contact!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
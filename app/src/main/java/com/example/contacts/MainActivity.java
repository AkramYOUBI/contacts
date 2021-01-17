package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextSearch;
    Button buttonSearch;
    RecyclerView recyclerViewContacts;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    Button buttonAddContact;

    List<ContactModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        recyclerViewContacts = (RecyclerView) findViewById(R.id.recyclerViewContacts);
        buttonAddContact = (Button) findViewById(R.id.buttonAddContact);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        list = dataBaseHelper.getAll();

        layoutManager = new LinearLayoutManager(this);
        recyclerViewContacts.setLayoutManager(layoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(list, new RecyclerViewAdapter.MyRecyclerViewItemClickListener() {
            @Override
            public void onItemClicked(ContactModel contactModel) {
                Intent intent = new Intent(MainActivity.this, UpdateContact.class);
                intent.putExtra("contactId", String.valueOf(contactModel.getId()));
                intent.putExtra("contactFirstName", contactModel.getFirstName());
                intent.putExtra("contactLastName", contactModel.getLastName());
                intent.putExtra("contactEmail", contactModel.getEmail());
                intent.putExtra("contactBirthday", contactModel.getBirthday());
                intent.putExtra("contactFix", contactModel.getFix());
                intent.putExtra("contactMobile", contactModel.getMobile());
                intent.putExtra("contactFax", contactModel.getFax());
                startActivity(intent);
            }
        });
        recyclerViewContacts.setAdapter(recyclerViewAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<ContactModel> contacts = dataBaseHelper.search(editTextSearch.getText().toString());
                if (contacts != null) {
                    recyclerViewContacts.setAdapter(new RecyclerViewAdapter(contacts, new RecyclerViewAdapter.MyRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClicked(ContactModel contactModel) {
                            Intent intent = new Intent(MainActivity.this, UpdateContact.class);
                            intent.putExtra("contactId", String.valueOf(contactModel.getId()));
                            intent.putExtra("contactFirstName", contactModel.getFirstName());
                            intent.putExtra("contactLastName", contactModel.getLastName());
                            intent.putExtra("contactEmail", contactModel.getEmail());
                            intent.putExtra("contactBirthday", contactModel.getBirthday());
                            intent.putExtra("contactFix", contactModel.getFix());
                            intent.putExtra("contactMobile", contactModel.getMobile());
                            intent.putExtra("contactFax", contactModel.getFax());
                            startActivity(intent);
                        }
                    }));
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateContact.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
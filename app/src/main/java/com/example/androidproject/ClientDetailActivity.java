package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ClientDetailActivity extends AppCompatActivity {

    String firstName, lastName, email, address, phone, notes;
    TextView firstNameTextView, lastNameTextView, emailTextView, addressTextView, phoneTextView;
    EditText noteEditText;
    Button editBtn, saveNotesBtn, closeBtn, deleteBtn;
    DatabaseReference contactAppRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

        firstNameTextView = findViewById(R.id.first_Name);
        lastNameTextView = findViewById(R.id.last_Name);
        emailTextView = findViewById(R.id.email_Address);
        addressTextView = findViewById(R.id.address);
        phoneTextView = findViewById(R.id.phone_Number);
        noteEditText = findViewById(R.id.note);

        editBtn = findViewById(R.id.edit_Btn);
        closeBtn = findViewById(R.id.close_Btn);
        deleteBtn = findViewById(R.id.delete_Btn);
        saveNotesBtn = findViewById(R.id.save_Notes_Btn);


        firstName = getIntent().getStringExtra("firstname");
        lastName = getIntent().getStringExtra("lastname");
        email = getIntent().getStringExtra("email");
        address = getIntent().getStringExtra("address");
        phone = getIntent().getStringExtra("phone");
        notes = getIntent().getStringExtra("notes");


        firstNameTextView.setText(firstName);
        lastNameTextView.setText(lastName);
        emailTextView.setText(email);
        addressTextView.setText(address);
        phoneTextView.setText(phone);
        noteEditText.setText(notes);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientDetailActivity.this, EditContactActivity.class);
                intent.putExtra("firstname", firstName);
                intent.putExtra("lastname", lastName);
                intent.putExtra("email", email);
                intent.putExtra("address", address);
                intent.putExtra("phone", phone);
                intent.putExtra("notes", notes);

                ClientDetailActivity.this.startActivity(intent);
            }
        });

        saveNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactAppRef = FirebaseDatabase.getInstance().getReference("Contacts");
                contactAppRef.child("Contacts").getKey().

                Toast.makeText(ClientDetailActivity.this, "Deleted Contact Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }




}
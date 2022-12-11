package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

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

        contactAppRef = FirebaseDatabase.getInstance().getReference().child("Contacts");

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
                String note = noteEditText.getText().toString();

                contactAppRef = FirebaseDatabase.getInstance().getReference("Contacts").child(lastName).child("notes");
                contactAppRef.setValue(note);
                Toast.makeText(ClientDetailActivity.this, "Note Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClientDetailActivity.this);
                builder.setMessage("Are you sure you want to delete this contact?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        contactAppRef.child(lastName).setValue(null);
                        Toast.makeText(ClientDetailActivity.this, "Deleted Contact Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ClientDetailActivity.this, MainActivity.class);
                        ClientDetailActivity.this.startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

}
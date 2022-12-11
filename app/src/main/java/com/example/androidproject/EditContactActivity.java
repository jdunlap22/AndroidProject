package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditContactActivity extends AppCompatActivity {

    EditText firstNameEditText, lastNameEditText, emailEditText, addressEditText, phoneNumberEditText;
    String firstName, lastName, email, address, phone, notes;
    TextView notesTextView;
    Button saveBtn;
    DatabaseReference contactAppRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.emailAddress);
        addressEditText = findViewById(R.id.address);
        phoneNumberEditText = findViewById(R.id.phoneNumber);
        saveBtn = findViewById(R.id.save_Btn);
        notesTextView = findViewById(R.id.note);

        firstName = getIntent().getStringExtra("firstname");
        lastName = getIntent().getStringExtra("lastname");
        email = getIntent().getStringExtra("email");
        address = getIntent().getStringExtra("address");
        phone = getIntent().getStringExtra("phone");
        notes = getIntent().getStringExtra("notes");

        firstNameEditText.setText(firstName);
        lastNameEditText.setText(lastName);
        emailEditText.setText(email);
        addressEditText.setText(address);
        phoneNumberEditText.setText(phone);
        phoneNumberEditText.setText(phone);
        notesTextView.setText(notes);

        contactAppRef = FirebaseDatabase.getInstance().getReference().child("Contacts");

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData(firstName, lastName, email, address, phone);

                Intent intent = new Intent(EditContactActivity.this, ClientDetailActivity.class);

                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String phone =phoneNumberEditText.getText().toString();
                String notes = notesTextView.getText().toString();

                intent.putExtra("firstname", firstName);
                intent.putExtra("lastname", lastName);
                intent.putExtra("email", email);
                intent.putExtra("address", address);
                intent.putExtra("phone", phone);
                intent.putExtra("notes", notes);

                Contacts contacts = new Contacts(firstName, lastName, email, address, phone, notes);

                contactAppRef = FirebaseDatabase.getInstance().getReference("Contacts");
                contactAppRef.child(lastName).setValue(contacts);

                EditContactActivity.this.startActivity(intent);
            }
        });
    }

    boolean validateData(String firstName, String lastName, String email, String address, String phone) {

        if(firstName.isEmpty()) {
            firstNameEditText.setError("First Name is blank");
        }

        if(lastName.isEmpty()) {
            firstNameEditText.setError("Last Name is blank");
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Email is invalid");
        }

        if(address.isEmpty()){
            addressEditText.setError("Address is blank");
        }

        if(phone.length()<10) {
            phoneNumberEditText.setError("Phone number is invalid");
            return false;
        }

        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
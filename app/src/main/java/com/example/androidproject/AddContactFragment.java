package com.example.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;

public class AddContactFragment extends Fragment {

    EditText firstNameEditText, lastNameEditText, emailEditText, addressEditText, phoneNumberEditText, noteEditText;
    Button createContactBtn;
    AddContactFragment binding;
    boolean isEditMode = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        EditText firstNameEditText = binding.firstNameEditText;
        EditText lastNameEditText = binding.lastNameEditText;
        EditText emailEditText = binding.emailEditText;
        EditText addressEditText = binding.addressEditText;
        EditText phoneNumberEditText = binding.phoneNumberEditText;
        EditText noteEditText = binding.noteEditText;
        Button createContactBtn = binding.createContactBtn;

        createContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return inflater.inflate(R.layout.fragment_add_contact, container, false);
    }

    boolean validateData(String email, String phone) {
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Email is invalid");
            return false;
        }

        if(phone.length()<10) {
            phoneNumberEditText.setError("Phone number is invalid");
            return false;
        }
        return true;
    }

    void createContact() {
        String firstname = firstNameEditText.getText().toString();
        String lastname = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String phone =phoneNumberEditText.getText().toString();
        String notes = noteEditText.getText().toString();

        boolean isValidated = validateData(email, phone);
        if(!isValidated) {
            return;
        }

        createContactInFireBase();
    }

    void createContactInFireBase(String firstname, String lastname, String email, String address, String phone, String notes) {

        DocumentReference contactReference;
        if(isEditMode) {
            contactReference =
        }
    }

}
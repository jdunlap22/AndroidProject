package com.example.androidproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.androidproject.databinding.FragmentAddContactBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;

public class AddContactFragment extends Fragment {

    EditText firstNameEditText, lastNameEditText, emailEditText, addressEditText, phoneNumberEditText, noteEditText;
    Button createContactBtn;
    private FragmentAddContactBinding binding;

    DatabaseReference contactAppRef;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentAddContactBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        firstNameEditText = root.findViewById(R.id.firstName);
        lastNameEditText = root.findViewById(R.id.lastName);
        emailEditText = root.findViewById(R.id.emailAddress);
        addressEditText = root.findViewById(R.id.address);
        phoneNumberEditText = root.findViewById(R.id.phoneNumber);
        noteEditText = root.findViewById(R.id.note);
        createContactBtn = root.findViewById(R.id.createBtn);

        contactAppRef = FirebaseDatabase.getInstance().getReference().child("Contacts");

        createContactBtn.setOnClickListener((v)-> createContact());

        return root;
    }

    boolean validateData(String firstName, String lastName, String email, String address, String phone, String notes) {

        if(firstName.isEmpty()) {
            firstNameEditText.setError("First Name is blank");
        }

        if(lastName.isEmpty()) {
            firstNameEditText.setError("Last Name is blank");
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Email is invalid");
            return false;
        }

        if(address.isEmpty()){
            addressEditText.setError("Address is blank");
        }

        if(phone.length()<10) {
            phoneNumberEditText.setError("Phone number is invalid");
            return false;
        }

        if(notes.isEmpty()) {
            noteEditText.setError("Notes are blank");
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

        Contacts contacts = new Contacts(firstname, lastname, email, address, phone, notes);

        boolean isValidated = validateData(firstname, lastname, email, address, phone, notes);
        if(!isValidated) {
            return;
        }

        contactAppRef.push().setValue(contacts);
    }

    void saveContactToFirebase(Contacts contacts) {
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForContacts().document();

        documentReference.set(contacts).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Contact Added successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
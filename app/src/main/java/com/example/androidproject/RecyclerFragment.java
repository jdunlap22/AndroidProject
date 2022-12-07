package com.example.androidproject;

import android.app.DownloadManager;
import android.icu.util.ULocale;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproject.databinding.FragmentRecyclerBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;

import java.util.ArrayList;

public class RecyclerFragment extends Fragment {

    RecyclerView recyclerView;
    private FragmentRecyclerBinding binding;
    ContactAdapter contactAdapter;
    DatabaseReference contactReference;
    ArrayList<Contacts> contacts;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRecyclerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recycler_view);
        contactReference = FirebaseDatabase.getInstance().getReference().child("Contacts");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        contacts = new ArrayList<>();
        contactAdapter = new ContactAdapter(getContext(), contacts);
        recyclerView.setAdapter(contactAdapter);

        contactReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Contacts contact = dataSnapshot.getValue(Contacts.class);
                    contacts.add(contact);
                }
                contactAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }
}
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
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;

public class RecyclerFragment extends Fragment {

    RecyclerView recyclerView;
    private FragmentRecyclerBinding binding;
    ContactAdapter contactAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRecyclerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recycler_view);
        setupRecyclerView();
        return root;
    }

   void setupRecyclerView() {
        CollectionReference query = Utility.getCollectionReferenceForContacts();
        FirestoreRecyclerOptions<Contacts> options = new FirestoreRecyclerOptions.Builder<Contacts>().setQuery(query,Contacts.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contactAdapter = new ContactAdapter(options,getContext());
        recyclerView.setAdapter(contactAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        contactAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        contactAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        contactAdapter.notifyDataSetChanged();
    }
}
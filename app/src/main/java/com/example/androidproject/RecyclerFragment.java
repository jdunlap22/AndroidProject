package com.example.androidproject;

import android.app.DownloadManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproject.databinding.FragmentRecyclerBinding;
import com.google.firebase.database.Query;

public class RecyclerFragment extends Fragment {

    RecyclerView recyclerView;
    private FragmentRecyclerBinding binding;


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
        Query query = Utility.getCollectionReferenceForNotes().orderBy("Last Name", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Contacts>
    }
}
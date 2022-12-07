package com.example.androidproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidproject.databinding.FragmentAddContactBinding;
import com.example.androidproject.databinding.FragmentCreditsBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreditsFragment extends Fragment {

    private FragmentCreditsBinding binding;
    TextView creditsTextView;
    TextView dateTextView;
    String date = new SimpleDateFormat("MM dd, yyyy", Locale.getDefault()).format(new Date());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCreditsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        creditsTextView = root.findViewById(R.id.credits);
        dateTextView = root.findViewById(R.id.date);

        creditsTextView.setText("Jeremy Dunlap MOBI3002");
        dateTextView.setText(date);

        return root;
    }
}
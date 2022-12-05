package com.example.androidproject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class ContactAdapter extends FirestoreRecyclerAdapter<Contacts, ContactAdapter.ContactViewHolder> {
    Context context;

    public ContactAdapter(@NonNull FirestoreRecyclerOptions<Contacts> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ContactViewHolder holder, int position, @NonNull Contacts contacts) {
        holder.lastName.setText(contacts.lastname);
        holder.firstName.setText(contacts.firstname);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_contact_item,parent, false);
        return new ContactViewHolder(view);
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView lastName, firstName;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            lastName = itemView.findViewById(R.id.contact_Last_Name);
            firstName = itemView.findViewById(R.id.contact_First_Name);
        }
    }
}

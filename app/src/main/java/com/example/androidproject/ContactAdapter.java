package com.example.androidproject;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    Context context;
    ArrayList<Contacts> contact;

    public ContactAdapter(Context context, ArrayList<Contacts> contact) {
        this.context = context;
        this.contact = contact;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_contact_item,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contacts contacts = contact.get(position);
        holder.lastName.setText(contacts.getLastname());
        holder.firstName.setText(contacts.getFirstname());
        holder.email.setText(contacts.getEmail());
        holder.address.setText(contacts.getAddress());
        holder.phone.setText(contacts.getPhone());
        holder.notes.setText(contacts.getNotes());

        holder.itemView.setOnClickListener((v) ->{
            Intent intent = new Intent(context,ClientDetailActivity.class);
            intent.putExtra("firstname",contacts.getFirstname());
            intent.putExtra("lastname",contacts.getLastname());
            intent.putExtra("email",contacts.getEmail());
            intent.putExtra("address",contacts.getAddress());
            intent.putExtra("phone",contacts.getPhone());
            intent.putExtra("notes",contacts.getNotes());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return contact.size();
    }


    class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView lastName, firstName, email, address, phone, notes;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            lastName = itemView.findViewById(R.id.contact_Last_Name);
            firstName = itemView.findViewById(R.id.contact_First_Name);
            email = itemView.findViewById(R.id.contact_Email);
            address = itemView.findViewById(R.id.contact_Address);
            phone = itemView.findViewById(R.id.contact_Phone);
            notes = itemView.findViewById(R.id.contact_Note);
        }
    }
}

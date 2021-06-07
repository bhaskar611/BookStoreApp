package com.example.bridgelabz.bookstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Address;
import com.example.bridgelabz.bookstore.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Address_Pick_Adapter extends RecyclerView.Adapter<AddressPick_ViewHolder> {

    private List<Address> addressArrayList = new ArrayList<>();
    private OnAddressListener onAddressListener;
    public Address_Pick_Adapter(List<Address> addressArrayList,OnAddressListener onAddressListener) {

        this.addressArrayList = addressArrayList;
        this.onAddressListener = onAddressListener;
    }

    @NonNull
    @Override
    public AddressPick_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.address_pick, parent, false);
        return new AddressPick_ViewHolder(view,onAddressListener);

    }

    @Override
    public void onBindViewHolder(@NonNull AddressPick_ViewHolder holder, int position) {
        Address address = addressArrayList.get(position);
        holder.bind(address);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

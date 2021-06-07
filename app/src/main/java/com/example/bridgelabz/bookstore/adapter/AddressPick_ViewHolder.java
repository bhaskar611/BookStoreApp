package com.example.bridgelabz.bookstore.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Address;
import com.example.bridgelabz.bookstore.model.Book;

public class AddressPick_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView mobile,houseNO,Street,City,State,pin;
    private OnAddressListener onAddressListener;
    private static final String TAG = "AddressPick_ViewHolder";

    public AddressPick_ViewHolder(@NonNull View itemView,OnAddressListener onAddressListener) {
        super(itemView);
        mobile = itemView.findViewById(R.id.mobile_pick);
        houseNO = itemView.findViewById(R.id.house_pick);
        Street = itemView.findViewById(R.id.Street_pick);
        City = itemView.findViewById(R.id.city_pick);
        State = itemView.findViewById(R.id.state_pick);
        pin = itemView.findViewById(R.id.pin_pick);
        this.onAddressListener = onAddressListener;
        itemView.setOnClickListener(this);
    }
    public void bind(Address address) {
        mobile.setText(address.getMobile());
        Log.e(TAG, "bind: " + mobile );
        houseNO.setText(address.getHouseNo());
        Street.setText(address.getStreet());
        City.setText(address.getCity());
        State.setText(address.getState());
        pin.setText(address.getPincode());
    }

    @Override
    public void onClick(View v) {
        onAddressListener.onAddressClick(getBindingAdapterPosition(),v);
    }
}

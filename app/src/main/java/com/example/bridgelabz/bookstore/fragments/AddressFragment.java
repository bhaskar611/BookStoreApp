package com.example.bridgelabz.bookstore.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressFragment extends Fragment {
    EditText mobile,HouseNO,Street,City,State,PIN;
    Button Submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address, container, false);

        mobile = view.findViewById(R.id.Mobile);
        HouseNO = view.findViewById(R.id.HouseNo);
        Street = view.findViewById(R.id.Street);
        City = view.findViewById(R.id.City);
        State = view.findViewById(R.id.State);
        PIN = view.findViewById(R.id.Pincode);
        Submit = view.findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        long AddressID = System.currentTimeMillis();
        String Mobile = mobile.getText().toString();
        String houseNO = HouseNO.getText().toString();
        String street = Street.getText().toString();
        String city = City.getText().toString();
        String state = State.getText().toString();
        String pin = PIN.getText().toString();
        Address userAddress = new Address(AddressID,Mobile,houseNO,street,city,state,pin);
        List<Address> userAddressList = new ArrayList<>();
        userAddressList.add(userAddress);

        return view;
    }
}
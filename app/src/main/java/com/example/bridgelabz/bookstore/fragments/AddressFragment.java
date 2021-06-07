package com.example.bridgelabz.bookstore.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.model.Address;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddressFragment extends Fragment {
    EditText mobile,HouseNO,Street,City,State,PIN;
    Button Submit;
    SharedPreference sharedPreference;
    private static final String TAG = "AddressFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        sharedPreference = new SharedPreference(this.getContext());

        mobile = view.findViewById(R.id.Mobile);
        Log.e(TAG, "onCreateView: " + mobile );
        HouseNO = view.findViewById(R.id.HouseNo);
        Street = view.findViewById(R.id.Street);
        City = view.findViewById(R.id.City);
        State = view.findViewById(R.id.State);
        PIN = view.findViewById(R.id.Pincode);
        Submit = view.findViewById(R.id.submit);
//        List<Address> userAddressList = new ArrayList<>();
//        userAddressList.add(userAddress);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long AddressID = System.currentTimeMillis();
                String Mobile = mobile.getText().toString();
                Log.e(TAG, "onCreateView: " + Mobile );
                String houseNO = HouseNO.getText().toString();
                String street = Street.getText().toString();
                String city = City.getText().toString();
                String state = State.getText().toString();
                String pin = PIN.getText().toString();
                Address userAddress = new Address(AddressID,Mobile,houseNO,street,city,state,pin);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(),
                            "Users.json"), new TypeReference<List<User>>(){});
                    List<Address> address = userList1.get(sharedPreference.getPresentUserId()).getUserAddress();
                    address.add(userAddress);
                    userList1.get(sharedPreference.getPresentUserId()).setUserAddress(address);
                    String updatedFile = mapper.writeValueAsString(userList1);
                    FileOutputStream fos = getContext().openFileOutput("Users.json", Context.MODE_PRIVATE);
                    fos.write(updatedFile.getBytes());
                    fos.close();

                } catch (IOException jsonParseException) {
                    jsonParseException.printStackTrace();
                }

                Fragment fragment = new Pick_Address_Fragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        onBackPressed(view);
        return view;
    }

    private void onBackPressed(View view) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        toolbar.setTitle("Address");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle any click event
                getParentFragmentManager().popBackStack();

            }
        });


    }

    public void onResume() {
        super.onResume();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();
    }
}
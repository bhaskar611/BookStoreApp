package com.example.bridgelabz.bookstore.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.adapter.Address_Pick_Adapter;
import com.example.bridgelabz.bookstore.adapter.OnAddressListener;
import com.example.bridgelabz.bookstore.model.Address;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PickAddressFragment extends Fragment {

    SharedPreference sharedPreference;
    RecyclerView recyclerView;
    int spanCount;
    private Address_Pick_Adapter address_pick_adapter;
    Button addAddress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        List<Address> addressList = new ArrayList<>();
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            spanCount = 2;
        } else {
            // In portrait
            spanCount = 1;
        }
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        sharedPreference = new SharedPreference(this.getContext());
        View view = inflater.inflate(R.layout.fragment_pick__address_, container, false);
        addAddress = view.findViewById(R.id.addAddressPick);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AddressFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
               fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(), "Users.json"),new TypeReference<List<User>>(){} );
            int i;
            for (i=0;i<userList1.size();i++) {
                if(userList1.get(i).getUserID() ==  sharedPreference.getPresentUserId()) {
//                    isLoggedIN = true;
                   addressList = userList1.get(i).getUserAddress();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            recyclerView = view.findViewById(R.id.pick_RecyclerView);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
        address_pick_adapter = new Address_Pick_Adapter(addressList, new OnAddressListener() {
            @Override
            public void onAddressClick(int position, View viewHolder) {
                Fragment fragment = new OrderPlacedFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        recyclerView.setAdapter(address_pick_adapter);
        address_pick_adapter.notifyDataSetChanged();
        onBackPressed(view);
            return view;
    }
    private void onBackPressed(View view) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.Address_Pick_ToolBar);
        toolbar.setTitle("Pick Address");
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
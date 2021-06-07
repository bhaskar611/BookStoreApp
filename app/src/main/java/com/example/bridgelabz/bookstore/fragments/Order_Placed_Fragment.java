package com.example.bridgelabz.bookstore.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bridgelabz.bookstore.R;

import java.util.Objects;

public class Order_Placed_Fragment extends Fragment {

    EditText orderPlaced;
    Button cont_Shopping;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        orderPlaced = view.findViewById(R.id.textView10);
        cont_Shopping = view.findViewById(R.id.button2);
        //orderid + date + time
        long orderNo = System.currentTimeMillis();
        orderPlaced.setText((int) orderNo);
        cont_Shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new bookListFragment();
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

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.Order_Placed_ToolBar);
        toolbar.setTitle("Cart List");
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
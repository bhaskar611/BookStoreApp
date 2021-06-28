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

import com.example.bridgelabz.bookstore.R;

import java.util.Objects;


public class PremieumUserFragment extends Fragment {

    Button button;
    BookListFragment bookListFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_premieum_user, container, false);
        button = view.findViewById(R.id.pay_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookListFragment = new BookListFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, bookListFragment).commit();
            }
        });
        onBackPressed(view);
        return view;
    }
    private void onBackPressed(View view) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar4);
        toolbar.setTitle("p");
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
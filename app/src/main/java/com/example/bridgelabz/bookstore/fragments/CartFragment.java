package com.example.bridgelabz.bookstore.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
import android.widget.Toast;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.BookRepository;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.adapter.BookListAdapter;
import com.example.bridgelabz.bookstore.adapter.CartListAdapter;
import com.example.bridgelabz.bookstore.adapter.OnBookListener;
import com.example.bridgelabz.bookstore.model.Address;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CartFragment extends Fragment {

    private CartListAdapter cartListAdapter;
    private static final String TAG = "FavouriteFragment";
    private RecyclerView cart_recyclerView;
    private int spanCount;
    private BookRepository bookRepository;
    Button checkOut;
    SharedPreference sharedPreference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        bookRepository = new BookRepository(getContext());
        ArrayList<Book> cartItemBooks = bookRepository.getCartItemBooks();
        sharedPreference = new SharedPreference(this.getContext());
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            spanCount = 2;
        } else {
            // In portrait
            spanCount = 2;
        }
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        cart_recyclerView = view.findViewById(R.id.CartList_RecyclerView);
        checkOut = view.findViewById(R.id.button);
        cart_recyclerView.setLayoutManager(layoutManager);
        cart_recyclerView.setHasFixedSize(true);
        cartListAdapter = new CartListAdapter(cartItemBooks);
        cart_recyclerView.setAdapter(cartListAdapter);
        cartListAdapter.notifyDataSetChanged();
        onBackPressed(view);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOutCart();
            }
        });
        return view;
    }

    private void checkOutCart() {

        ObjectMapper mapper = new ObjectMapper();
        List<User> userList1 = null;
        try {
            userList1 = mapper.readValue(new File(getContext().getFilesDir(),
                    "Users.json"), new TypeReference<List<User>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Address> userAddress = userList1.get(sharedPreference.getPresentUserId()).getUserAddress();
        if(userAddress.size() == 0){
            Fragment fragment = new AddressFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else{
            Fragment fragment = new Pick_Address_Fragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    private void onBackPressed(View view) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.CartList_toolbar);
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
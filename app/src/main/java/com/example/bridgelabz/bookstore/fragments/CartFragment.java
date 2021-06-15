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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.BookRepository;
import com.example.bridgelabz.bookstore.Repository.CartRepository;
import com.example.bridgelabz.bookstore.Repository.ReviewRepository;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.adapter.CartBookClickListener;
import com.example.bridgelabz.bookstore.adapter.CartListAdapter;
import com.example.bridgelabz.bookstore.model.Address;
import com.example.bridgelabz.bookstore.model.CartModel;
import com.example.bridgelabz.bookstore.model.User;
import com.example.bridgelabz.bookstore.ui.dashBoard.AddBadge;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class CartFragment extends Fragment {

    private CartListAdapter cartListAdapter;
    private static final String TAG = "CartFragment";
    private RecyclerView cart_recyclerView;
    private int spanCount;
    private BookRepository bookRepository;
    private CartRepository cartRepository;
    Button checkOut;
    SharedPreference sharedPreference;
    TextView totalAmount;
    float totalamount_Cart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        File reviewsFile = new File(getContext().getFilesDir(), "reviews.json");
        cartRepository = new CartRepository(getContext(),new ReviewRepository(reviewsFile));

        bookRepository = new BookRepository(getContext(),new ReviewRepository(reviewsFile));
        List<CartModel> cartItemBooks = cartRepository.getCartList();
      totalAmount = view.findViewById(R.id.textView25);

        totalAmount = view.findViewById(R.id.textView35);
        totalamount_Cart = cartRepository.calculateTotalPrice(cartItemBooks);
        totalAmount.setText(String.valueOf(totalamount_Cart));
        sharedPreference = new SharedPreference(this.getContext());
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            spanCount = 2;
        } else {
            // In portrait
            spanCount = 1;
        }
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        cart_recyclerView = view.findViewById(R.id.CartList_RecyclerView);
        checkOut = view.findViewById(R.id.button);
        cart_recyclerView.setLayoutManager(layoutManager);
        cart_recyclerView.setHasFixedSize(true);
        cartListAdapter = new CartListAdapter(cartItemBooks, new CartBookClickListener() {
            @Override
            public void onAddItemQuantity(CartModel cart) {
                bookRepository.incrementCartItemQuantity(cart.getBook().getBookID());
                List<CartModel> updatedCart = cartRepository.getCartList();
                Log.e(TAG, "onAddItemQuantity: "+ updatedCart.size());
                totalamount_Cart = cartRepository.calculateTotalPrice(updatedCart);
                Log.e(TAG, "onAddItemQuantity: " + totalamount_Cart );
                totalAmount.setText(String.valueOf(totalamount_Cart));
            }

            @Override
            public void onMinusItemQuantity(CartModel cart, int position) {
                if(cart.getQuantites() < 2){
                    cartListAdapter.removeAt(position);
                }
                bookRepository.decrementCartItemQuantity(cart.getBook().getBookID());
                List<CartModel> updatedCart = cartRepository.getCartList();
                totalamount_Cart = cartRepository.calculateTotalPrice(updatedCart);
                totalAmount.setText(String.valueOf(totalamount_Cart));
                cartListAdapter.setCartBooksList(updatedCart);
                    try{
                        ((AddBadge) requireActivity()).onAddCart(cartRepository.getCartList().size());
    //This will invoke the implemented method in your activity class. You
    //can pass any type of value through to your activity. Just add the
    //parameter in your interface declaration.
                    }catch (ClassCastException e){
                        e.printStackTrace();
                    }

//                cartListAdapter.notifyItemRemoved(position);
//                cartListAdapter.setCartBooksList(updatedCart);
//                if(updatedCart.size() == 0){
//                    cartListAdapter.notifyDataSetChanged();
//                }
            }
        });
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
            Fragment fragment = new PickAddressFragment();
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
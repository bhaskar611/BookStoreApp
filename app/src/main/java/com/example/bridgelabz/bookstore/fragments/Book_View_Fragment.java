package com.example.bridgelabz.bookstore.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.BookRepository;
import com.example.bridgelabz.bookstore.Repository.CartRepository;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.adapter.Address_Pick_Adapter;
import com.example.bridgelabz.bookstore.adapter.ReviewAdapter;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.CartResponseModel;
import com.example.bridgelabz.bookstore.model.Cart_Item;
import com.example.bridgelabz.bookstore.model.Review;
import com.example.bridgelabz.bookstore.model.User;
import com.example.bridgelabz.bookstore.util.CallBack;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book_View_Fragment extends Fragment {


    private static final String TAG = "Book_View_Fragment";
    ImageView bookImage;
    Button add_To_Cart;
    TextView bookTitle, bookAuthor, bookPrice;
    ArrayList<Cart_Item> cart_items = new ArrayList<>();
    SharedPreference sharedPreference;
    BookRepository bookRepository;
    CartRepository cartRepository;
    RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book__view_, container, false);
        cartRepository = new CartRepository(getContext());
        bookRepository = new BookRepository(getContext());
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        onBackPressed(view);

        // Inflate the layout for this fragment

        String BookTitle = getArguments().getString("BookTitle");
        String BookAuthor = getArguments().getString("BookAuthor");
        String BookImage = getArguments().getString("BookImage");
        float BookPrice = getArguments().getFloat("BookPrice");
        int BookId = getArguments().getInt("BookID");

        bookImage = view.findViewById(R.id.BookView_Image);
        bookTitle = view.findViewById(R.id.BookView_Title);
        bookAuthor = view.findViewById(R.id.BookView_Author);
        bookPrice = view.findViewById(R.id.BookView_Price);
        add_To_Cart = view.findViewById(R.id.Add_To_Cart);
        sharedPreference = new SharedPreference(this.getContext());

        add_To_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getCartItems(BookId);
                bookRepository.addBookToCart(BookId);
                add_To_Cart.setEnabled(false);
            }
        });
        bookTitle.setText(BookTitle);
        bookAuthor.setText(BookAuthor);
        bookPrice.setText(String.valueOf(BookPrice));
        Glide.with(getContext())
                .load(BookImage)
                .into(bookImage);
        recyclerView = view.findViewById(R.id.review_recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        List<Review> bookArrayList = bookRepository.getBookList().get(BookId).getReviewList();
        Log.e(TAG, "onCreateView: " + bookArrayList );
        reviewAdapter = new ReviewAdapter(bookArrayList);
        recyclerView.setAdapter(reviewAdapter);
        reviewAdapter.notifyDataSetChanged();

        return view;
    }

//    private void removeCartItems(int bookID) {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(),
//                    "Users.json"), new TypeReference<List<User>>(){});
//            List<Integer> cartItems = userList1.get(sharedPreference.getPresentUserId()).getCartItems();
//            cartItems.remove(Integer.valueOf(bookID));
//            userList1.get(sharedPreference.getPresentUserId()).setCartItems(cartItems);
//            String updatedFile = mapper.writeValueAsString(userList1);
//            FileOutputStream fos = getContext().openFileOutput("Users.json", Context.MODE_PRIVATE);
//            fos.write(updatedFile.getBytes());
//            fos.close();
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }
    private void onBackPressed(View view) {

       Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Book Title");
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

//    public void getCartItems(int bookID){
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//                List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(),
//                        "Users.json"), new TypeReference<List<User>>(){});
//                List<CartResponseModel> cartItems = userList1.get(sharedPreference.getPresentUserId()).getCartItemList();
//                cartItems.add(bookID);
//                userList1.get(sharedPreference.getPresentUserId()).setCartItemList(cartItems);
//                String updatedFile = mapper.writeValueAsString(userList1);
//                FileOutputStream fos = getContext().openFileOutput("Users.json", Context.MODE_PRIVATE);
//                fos.write(updatedFile.getBytes());
//                fos.close();
//
//        } catch (IOException e){
//                    e.printStackTrace();
//}
//    }
}




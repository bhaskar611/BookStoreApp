package com.example.bridgelabz.bookstore.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.adapter.WishListAdapter;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.Book_Wish_Item;
import com.example.bridgelabz.bookstore.model.Book_Wish_List;

import java.util.ArrayList;

public class WishListFragment extends Fragment {

    private static final String TAG ="WishListFragment" ;
    Book_Wish_Item book_wish_item = new Book_Wish_Item();
    ArrayList<Book_Wish_Item> book_wish_items = new ArrayList<>();
    SharedPreference sharedPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);

        RecyclerView wishList_recyclerView = view.findViewById(R.id.wishList_RecyclerView);
        final GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2);
        wishList_recyclerView.setLayoutManager(linearLayoutManager);
        wishList_recyclerView.setHasFixedSize(true);
//       String bookTitle = book_wish_item.getBookWishTitle();
//       String bookAuthor = book_wish_item.getBookWishAuthor();
//       String bookImage = book_wish_item.getBookWishImage();
        sharedPreference = new SharedPreference(this.getContext());
        String bookTitle = sharedPreference.getBookTitle();
        String bookAuthor = sharedPreference.getBookAuthor();
        String bookImage = sharedPreference.getBookImage();


      Book_Wish_Item book_wish_list = new Book_Wish_Item(bookTitle,bookAuthor,bookImage);
//
//       book_wish_.add(book_wish_list);
        book_wish_items.add(book_wish_list);

//      ArrayList<Book_Wish_Item>  book_wish_ = book_wish_item.getBookWishList();
        //    ArrayList<Book> book_wish_ = new ArrayList<>();
        WishListAdapter wishListAdapter = new WishListAdapter(book_wish_items);
        wishList_recyclerView.setAdapter(wishListAdapter);
        wishListAdapter.notifyDataSetChanged();


        // Inflate the layout for this fragment

//      String bookAuthor = book_wish_item.getBookWishAuthor();
//      String bookTitle = book_wish_item.getBookWishTitle();
//        String bookImage = book_wish_item.getBookWishImage();
//        Log.e(TAG, "onCreateView: " + bookTitle + bookAuthor + bookImage);


        return view;
    }

//    public void getWishList(ArrayList<Book_Wish_Item> book_wish_items){
//        wishListAdapter = new WishListAdapter(book_wish_items);
//        wishList_recyclerView.setAdapter(wishListAdapter);
//        wishListAdapter.notifyDataSetChanged();
//        Log.e(TAG, "getWishList: " + book_wish_items );
//
//    }

}
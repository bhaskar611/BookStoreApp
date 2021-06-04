package com.example.bridgelabz.bookstore.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;

public class Book_View_Fragment extends Fragment {


    ImageView bookImage;
    TextView bookTitle,bookAuthor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book__view_, container, false);

        // Inflate the layout for this fragment

        String BookTitle = getArguments().getString("BookTitle");
        String BookAuthor = getArguments().getString("BookAuthor");
        String BookImage = getArguments().getString("docID");

        bookImage = view.findViewById(R.id.BookView_Image);
        bookTitle = view.findViewById(R.id.BookView_Title);
        bookAuthor = view.findViewById(R.id.BookView_Author);

        bookTitle.setText(BookTitle);
        bookAuthor.setText(BookAuthor);
        Glide.with(getContext())
                .load(BookImage)
                .into(bookImage);



        return view;
    }
}
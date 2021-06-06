package com.example.bridgelabz.bookstore.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.BookRepository;
import com.example.bridgelabz.bookstore.adapter.BookListAdapter;
//import com.example.bridgelabz.bookstore.dataManager.BookListManager;
import com.example.bridgelabz.bookstore.adapter.OnBookListener;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.ui.Authentication.LoginActivity;
import com.example.bridgelabz.bookstore.util.CallBack;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class bookListFragment extends Fragment {

    private BookListAdapter booksListAdapter;
    private static final String TAG = "BooksListFragment";
    private ArrayList<Book> bookList = new ArrayList<>();
    private RecyclerView recyclerView;
    private int spanCount;
    Book_View_Fragment bookFragment;
    private BookRepository bookRepository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booklist, container, false);
//        final GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2);
        int orientation = getResources().getConfiguration().orientation;
        bookRepository = new BookRepository(getContext());
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            spanCount = 2;
        } else {
            // In portrait
            spanCount = 2;
        }
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = view.findViewById(R.id.bookList_RecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {

        ArrayList<Book> bookArrayList = bookRepository.getBookList();
        booksListAdapter = new BookListAdapter(bookArrayList, new OnBookListener() {
            @Override
            public void onBookClick(int position, View viewHolder) {
                int bookId = booksListAdapter.getItem(position).getBookID();
                String bookTitle = booksListAdapter.getItem(position).getBookTitle();
                String bookAuthor = booksListAdapter.getItem(position).getBookAuthor();
                String bookImage = booksListAdapter.getItem(position).getBookImage();
                float bookPrice = booksListAdapter.getItem(position).getBookPrice();

                bookFragment = new Book_View_Fragment();
                Bundle bundle = new Bundle();

                bundle.putInt("BookID", bookId);
                bundle.putString("BookTitle",bookTitle);
                bundle.putString("BookAuthor",bookAuthor);
                bundle.putString("BookAuthor",bookImage);
                bundle.putFloat("BookPrice",bookPrice);


                bookFragment.setArguments(bundle);

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, bookFragment)
                        .addToBackStack(null).commit();
            }
        });
        recyclerView.setAdapter(booksListAdapter);
        booksListAdapter.notifyDataSetChanged();
    }

}
package com.example.bridgelabz.bookstore.fragments;

import android.content.Context;
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
import com.example.bridgelabz.bookstore.adapter.BookListAdapter;
//import com.example.bridgelabz.bookstore.dataManager.BookListManager;
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

    private BookListAdapter bookListAdapter;
    private static final String TAG = "FragmentBooks";
    private ArrayList<Book> bookList = new ArrayList<>();
//    private RecyclerView.LayoutManager layoutManager;
//    BookListManager bookListManager;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_booklist, container, false);
        final GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView = view.findViewById(R.id.bookList_RecyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        getBooks();
//        bookListManager = new BookListManager(this.getContext());
//        bookListManager.getAllBooks(new CallBack<ArrayList<Book>>() {
//            @Override
//            public void onSuccess(ArrayList<Book> data) {
//                bookList = data;
//                bookListAdapter = new BookListAdapter(bookList);
//                recyclerView.setAdapter(bookListAdapter);
//                bookListAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Exception exception) {
//                Toast.makeText(getContext(), "failed to load Books!", Toast.LENGTH_SHORT).show();
//
//            }
//        });
        return view;

    }

    private void getBooks() {

        try {
            String data = loadJSONFromAsset();
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Book> bookArrayList = mapper.readValue(data, new TypeReference<List<Book>>(){} );
            bookListAdapter = new BookListAdapter(bookArrayList);
            recyclerView.setAdapter(bookListAdapter);
            bookListAdapter.notifyDataSetChanged();

        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("Books.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Log.e(TAG, "loadJSONFromAsset: " + json );
        return json;
    }
}

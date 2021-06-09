package com.example.bridgelabz.bookstore.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.BookRepository;
import com.example.bridgelabz.bookstore.adapter.BookListAdapter;
import com.example.bridgelabz.bookstore.adapter.OnBookListener;
import com.example.bridgelabz.bookstore.model.Book;

import java.util.ArrayList;
import java.util.Objects;

public class WishListFragment extends Fragment {

    private BookListAdapter booksListAdapter;
    private static final String TAG = "FavouriteFragment";
    private RecyclerView recyclerView;
    private int spanCount;
    private BookRepository bookRepository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);
        bookRepository = new BookRepository(getContext());
        ArrayList<Book> favourites = bookRepository.getFavoriteBooks();
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            spanCount = 2;
        } else {
            // In portrait
            spanCount = 1;
        }
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = view.findViewById(R.id.Wish_RecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        booksListAdapter = new BookListAdapter(favourites, new OnBookListener() {
            @Override
            public void onBookClick(int position, View viewHolder) {
                Toast.makeText(getContext(), "Book is in Favourite List", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(booksListAdapter);
        booksListAdapter.notifyDataSetChanged();
        onBackPressed(view);
        return view;
    }

    private void onBackPressed(View view) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.WishList_toolbar);
        toolbar.setTitle("Wish List");
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
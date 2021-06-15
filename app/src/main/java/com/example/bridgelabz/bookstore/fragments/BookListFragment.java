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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.BookRepository;
import com.example.bridgelabz.bookstore.Repository.ReviewRepository;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.adapter.BookListAdapter;
//import com.example.bridgelabz.bookstore.dataManager.BookListManager;
import com.example.bridgelabz.bookstore.adapter.OnBookListener;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.ReviewModel;
import com.example.bridgelabz.bookstore.model.User;
import com.example.bridgelabz.bookstore.ui.Authentication.LoginActivity;
import com.example.bridgelabz.bookstore.ui.dashBoard.DashBoardActivity;
import com.example.bridgelabz.bookstore.util.CallBack;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BookListFragment extends Fragment {

    private BookListAdapter booksListAdapter;
    private static final String TAG = "BooksListFragment";
    private ArrayList<Book> bookList = new ArrayList<>();
    private RecyclerView recyclerView;
    private int spanCount;
    Book_View_Fragment bookFragment;
    private BookRepository bookRepository;
    SharedPreference sharedPreference;
    DashBoardActivity dashBoardActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booklist, container, false);
//        final GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2);
        int orientation = getResources().getConfiguration().orientation;
        File reviewsFile = new File(getContext().getFilesDir(), "reviews.json");
        bookRepository = new BookRepository(getContext(),new ReviewRepository(reviewsFile));
        sharedPreference = new SharedPreference(this.getContext());
        dashBoardActivity = new DashBoardActivity();

        //dashBoardActivity.setupBadge();
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            spanCount = 2;
        } else {
            // In portrait
            spanCount = 1;
        }
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = view.findViewById(R.id.bookList_RecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        initRecyclerView();
        createReviewFile();
        return view;
    }

    private void createReviewFile() {
        String userName = null;
        String jsonStr = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(),
                    "Users.json"), new TypeReference<List<User>>() {
            });
            userName = userList1.get(sharedPreference.getPresentUserId()).getUserName();
        } catch (IOException jsonParseException) {
            jsonParseException.printStackTrace();
        }

        try {
            String userReview = "good book";
            float userRating =4.0f;
            int BookId = 1;
            long reviewID = System.currentTimeMillis();
            File file = new File(getContext().getFilesDir(), "reviews.json");
            List<ReviewModel> reviewList = new ArrayList<ReviewModel>();

            int userID = sharedPreference.getPresentUserId();
            ReviewModel review = new ReviewModel(userName, userID, reviewID, BookId, userRating, userReview);
            reviewList.add(review);
            Random random = new Random();
            int max = 5;
            int min = 1;
            //int randomInt = random.nextInt((max - min) + 1) + min;
            for (int i =1;i<8;i++){
                for (int j=1;j<11;j++){

                    userName ="random user " + j;
                    userID = j;
                    reviewID = System.currentTimeMillis();
                    BookId = i;
                    userRating = min + random.nextFloat() * (max - min);
                    userReview = "Excellent Book";
                    review = new ReviewModel(userName, userID, reviewID, BookId, userRating, userReview);
                    reviewList.add(review);

                }
            }
            if (file.exists()) {

            } else {
                jsonStr = mapper.writeValueAsString(reviewList);
                FileOutputStream fos = getContext().openFileOutput("reviews.json", Context.MODE_PRIVATE);
                fos.write(jsonStr.getBytes());
                fos.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void initRecyclerView() {

        ArrayList<Book> bookArrayList = bookRepository.getBookList();
        Log.e(TAG, "initRecyclerView: " + bookArrayList );
        booksListAdapter = new BookListAdapter(bookArrayList, new OnBookListener() {
            @Override
            public void onBookClick(int position, View viewHolder) {
                int bookId = booksListAdapter.getItem(position).getBookID();
                String bookTitle = booksListAdapter.getItem(position).getBookTitle();
                String bookAuthor = booksListAdapter.getItem(position).getBookAuthor();
                String bookImage = booksListAdapter.getItem(position).getBookImage();
                float bookPrice = booksListAdapter.getItem(position).getBookPrice();
                float bookRating = booksListAdapter.getItem(position).getRating();

                bookFragment = new Book_View_Fragment();
                Bundle bundle = new Bundle();

                bundle.putInt("BookID", bookId);
                bundle.putString("BookTitle",bookTitle);
                bundle.putString("BookAuthor",bookAuthor);
                bundle.putString("BookImage",bookImage);
                bundle.putFloat("BookPrice",bookPrice);
                bundle.putFloat("BookRating",bookRating);


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
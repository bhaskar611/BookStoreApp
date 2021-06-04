package com.example.bridgelabz.bookstore.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.fragments.WishListFragment;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.Book_Wish_Item;
import com.example.bridgelabz.bookstore.model.Book_Wish_List;
import com.example.bridgelabz.bookstore.model.User;
import com.example.bridgelabz.bookstore.util.CallBack;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    SharedPreference sharedPreference;
    private static final String TAG = "BookListAdapter";
    private ArrayList<Book> booksList = new ArrayList<Book>();
    private Context context;
    Book_Wish_Item book_wish_item = new Book_Wish_Item();
    private ArrayList<Book_Wish_Item> book_wish_items = new ArrayList<Book_Wish_Item>();
    private OnBookListener onBookListener;

    WishListFragment wishListFragment = new WishListFragment();

    public BookListAdapter(ArrayList<Book> booksList,Context context,OnBookListener onBookListener) {

        this.booksList = booksList;
        this.context = context;
        this.onBookListener=onBookListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.booklist,parent,false);
        return new MyViewHolder(view,onBookListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = booksList.get(position);
        holder.bookTitle.setText(book.getBookTitle());
        holder.bookAuthor.setText(book.getBookAuthor());
//        holder.bookImage.setText(book.getBookImage());
        String imageUri =book.getBookImage();
        Glide.with(holder.itemView.getContext())
                .load(imageUri)
                .into(holder.bookImage);

//        holder.bookWishItem.setText((CharSequence) booksList.get(position));
        holder.bookWishItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreference = new SharedPreference(context);
                if  (holder.bookWishItem.isChecked()) {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        int bookID = booksList.get(position).getBookID();
//                    String bookTitle = booksList.get(position).getBookTitle();
//                    String bookAuthor = booksList.get(position).getBookAuthor();
//                    String bookImage = booksList.get(position).getBookImage();
//                    sharedPreference.setBookTitle(bookTitle);
//                    sharedPreference.setBookAuthor(bookAuthor);
//                    sharedPreference.setBookImage(bookImage);
//
//                    book_wish_item.setBookWishAuthor(bookTitle);
//                    book_wish_item.setBookWishAuthor(bookAuthor);
//                    book_wish_item.setBookWishImage(bookImage);
//                    Book_Wish_Item book_wish_item = new Book_Wish_Item(bookTitle, bookAuthor, bookImage);
//                    book_wish_items.add(book_wish_item);
//                    Log.e(TAG, "onBindViewHolder: " + bookTitle + bookAuthor + bookImage
//                    );


//                    book_wish_list.setBook_wish_lists();
//                    book_wish_item.setBookWishList(book_wish_items);
//                    wishListFragment.getWishList(book_wish_items);
                        User user = new User();
                        ArrayList book_IDS = new ArrayList();
                        book_IDS.add(bookID);
                        user.setBookID(book_IDS);
                        List<User> userList1 = mapper.readValue(new File(context.getFilesDir(), "Users.json"),new TypeReference<List<User>>(){} );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
    public Book getItem(int position) {
        try{
            return booksList.get(position);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return null;
    }

}

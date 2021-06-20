package com.example.bridgelabz.bookstore.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView bookTitle,bookAuthor,bookPrice,bookrating,bookmrp,bookdiscount;
    ImageView bookImage;
    CheckBox isFavourite;
    OnBookListener onBookListener;
    OnFavoriteChangeListener onFavoriteChangeListener;
    SharedPreference sharedPreference;

    public MyViewHolder(@NonNull View itemView,OnBookListener onBookListener, OnFavoriteChangeListener onFavoriteChangeListener) {
        super(itemView);
        bookTitle = itemView.findViewById(R.id.bookTitle);
        bookAuthor = itemView.findViewById(R.id.bookAuthor);
        bookImage = itemView.findViewById(R.id.bookImage);
        isFavourite = itemView.findViewById(R.id.cartcheckbox);
        bookPrice = itemView.findViewById(R.id.bookPrice);
        bookrating = itemView.findViewById(R.id.textView2);
        bookmrp = itemView.findViewById(R.id.textView18);
        bookdiscount = itemView.findViewById(R.id.textView19);

        sharedPreference = new SharedPreference(itemView.getContext());
        this.onBookListener = onBookListener;
        this.onFavoriteChangeListener = onFavoriteChangeListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBookListener.onBookClick(getBindingAdapterPosition(),v);
    }

    public void bind(Book book) {
        isFavourite.setChecked(book.isFavourite());
        bookTitle.setText(book.getBookTitle());
        bookAuthor.setText(book.getBookAuthor());
        bookPrice.setText(String.valueOf(book.getBookPrice()));
        bookrating.setText(String.format(Locale.getDefault(),"%.1f", book.getRating()));
        bookmrp.setPaintFlags(bookmrp.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        bookmrp.setText(String.valueOf(book.getBookMRP()));
        bookdiscount.setText(String.valueOf(book.getDiscount()));
        String imageUri = book.getBookImage();
        Glide.with(itemView.getContext())
                .load(imageUri)
                .into(bookImage);

    }

    public void favouriteChanged(Book book, boolean isChecked,int position) {
        ObjectMapper mapper = new ObjectMapper();
        if(isChecked) {
            try {
                List<User> userList1 = mapper.readValue(new File(itemView.getContext().getFilesDir(),
                        "Users.json"), new TypeReference<List<User>>(){});
                List<Integer> favoriteBooks = userList1.get(sharedPreference.getPresentUserId()).getFavouriteItemsList();
                favoriteBooks.add(book.getBookID());
                userList1.get(sharedPreference.getPresentUserId()).setFavouriteItemsList(favoriteBooks);
                String updatedFile = mapper.writeValueAsString(userList1);
                FileOutputStream fos = itemView.getContext().openFileOutput("Users.json", Context.MODE_PRIVATE);
                fos.write(updatedFile.getBytes());
                fos.close();

            } catch (IOException jsonParseException) {
                jsonParseException.printStackTrace();
            }
        } else {
            List<User> userList1 = null;
            try {
                userList1 = mapper.readValue(new File(itemView.getContext().getFilesDir(),
                        "Users.json"), new TypeReference<List<User>>() {
                });
                List<Integer> favoriteBooks = userList1.get(sharedPreference.getPresentUserId()).getFavouriteItemsList();
                favoriteBooks.remove(Integer.valueOf(book.getBookID()) );
                userList1.get(sharedPreference.getPresentUserId()).setFavouriteItemsList(favoriteBooks);
                String updatedFile = mapper.writeValueAsString(userList1);
                FileOutputStream fos = itemView.getContext().openFileOutput("Users.json", Context.MODE_PRIVATE);
                fos.write(updatedFile.getBytes());
                fos.close();
            } catch (IOException | IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            onFavoriteChangeListener.onUnchecked(book, position);
        }
    }



    }

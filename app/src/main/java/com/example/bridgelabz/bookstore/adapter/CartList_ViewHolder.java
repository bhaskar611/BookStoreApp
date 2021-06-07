package com.example.bridgelabz.bookstore.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.fragments.CartFragment;
import com.example.bridgelabz.bookstore.fragments.bookListFragment;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CartList_ViewHolder extends RecyclerView.ViewHolder {
    TextView bookCartTitle, bookCartAuthor, bookCartPrice,itemCount;
    ImageView bookCartImage,addBook,removeBook;
    int ItemCount = 0;
    float bookPrice;
    SharedPreference sharedPreference;


    public CartList_ViewHolder(@NonNull View itemView) {
        super(itemView);
        bookCartTitle = itemView.findViewById(R.id.CartList_bookTitle);
        bookCartAuthor = itemView.findViewById(R.id.CartList_bookAuthor);
        bookCartImage = itemView.findViewById(R.id.CartList_bookImage);
        bookCartPrice =itemView.findViewById(R.id.CartList_bookPrice);
        itemCount = itemView.findViewById(R.id.textView3);
        addBook = itemView.findViewById(R.id.imageView);
        removeBook = itemView.findViewById(R.id.imageView2);
        sharedPreference = new SharedPreference(itemView.getContext());

    }
    public void bind(Book book) {
        bookCartTitle.setText(book.getBookTitle());
        bookCartAuthor.setText(book.getBookAuthor());
        bookCartPrice.setText(String.valueOf(book.getBookPrice()));
        String imageUri = book.getBookImage();
        Glide.with(itemView.getContext())
                .load(imageUri)
                .into(bookCartImage);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemCount = ItemCount + 1;
                itemCount.setText(String.valueOf(ItemCount));
                bookPrice = book.getBookPrice() * ItemCount;
                bookCartPrice.setText(String.valueOf(bookPrice));
            }
        });

        removeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemCount = ItemCount - 1;
                itemCount.setText(String.valueOf(ItemCount));
                bookPrice = book.getBookPrice() * ItemCount;
                bookCartPrice.setText(String.valueOf(bookPrice));
                if (ItemCount == 0){
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        List<User> userList1 = mapper.readValue(new File(v.getContext().getFilesDir(),
                                "Users.json"), new TypeReference<List<User>>(){});
                        List<Integer> cartItems = userList1.get(sharedPreference.getPresentUserId()).getCartItems();
                        cartItems.remove(Integer.valueOf(book.getBookID()));
                        userList1.get(sharedPreference.getPresentUserId()).setCartItems(cartItems);
                        String updatedFile = mapper.writeValueAsString(userList1);
                        FileOutputStream fos = v.getContext().openFileOutput("Users.json", Context.MODE_PRIVATE);
                        fos.write(updatedFile.getBytes());
                        fos.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new CartFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();


            }
        });
    }
}

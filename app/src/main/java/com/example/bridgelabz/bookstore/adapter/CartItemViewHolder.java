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
import com.example.bridgelabz.bookstore.Repository.BookRepository;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.fragments.CartFragment;
import com.example.bridgelabz.bookstore.fragments.bookListFragment;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.CartModel;
import com.example.bridgelabz.bookstore.model.CartResponseModel;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CartItemViewHolder extends RecyclerView.ViewHolder {
    TextView bookCartTitle, bookCartAuthor, bookCartPrice,itemCount;
    ImageView bookCartImage,addBook,removeBook;
    int ItemCount = 0;
    public static float bookPrice;
    public float totalBookPrice = 1;
    SharedPreference sharedPreference;
//    public static float totalPrice ;
    BookRepository bookRepository;
    private int cartPosition = 0;
    private CartModel cart;
    private CartBookClickListener cartBookClickListener;


    public CartItemViewHolder(@NonNull View itemView, CartBookClickListener cartBookClickListener) {
        super(itemView);
        bookCartTitle = itemView.findViewById(R.id.CartList_bookTitle);
        bookCartAuthor = itemView.findViewById(R.id.CartList_bookAuthor);
        bookCartImage = itemView.findViewById(R.id.CartList_bookImage);
        bookCartPrice =itemView.findViewById(R.id.CartList_bookPrice);
        itemCount = itemView.findViewById(R.id.textView3);
        addBook = itemView.findViewById(R.id.imageView2);
        removeBook = itemView.findViewById(R.id.imageView);
        sharedPreference = new SharedPreference(itemView.getContext());
        bookRepository = new BookRepository(itemView.getContext());
        this.cartBookClickListener = cartBookClickListener;

    }
    public void bind(CartModel cart, int cartPosition) {
        this.cart = cart;
        this.cartPosition = cartPosition;
        this.ItemCount = cart.getQuantites();
        totalBookPrice = displayPrices(ItemCount);
        bookCartTitle.setText(cart.getBook().getBookTitle());
        bookCartAuthor.setText(cart.getBook().getBookAuthor());
        bookCartPrice.setText(String.valueOf(cart.getBook().getBookPrice()));
        String imageUri = cart.getBook().getBookImage();
        Glide.with(itemView.getContext())
                .load(imageUri)
                .into(bookCartImage);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // bookRepository.addBookToCart(cart.getBook().getBookID());
                ItemCount++;
                displayPrices(ItemCount);
                itemCount.setText(String.valueOf(ItemCount));
               //    bookPrice = cart.getBook().getBookPrice() * ItemCount;
                bookCartPrice.setText(String.valueOf(totalBookPrice));
                cartBookClickListener.onAddItemQuantity(cart);
            }
        });

        removeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bookRepository.removeBookToCart(cart.getBook().getBookID());
                ItemCount--;
                displayPrices(ItemCount);
                itemCount.setText(String.valueOf(ItemCount));
                //bookPrice = cart.getBook().getBookPrice() * ItemCount;
//                totalPrice = bookPrice;
                bookCartPrice.setText(String.valueOf(totalBookPrice));

                cartBookClickListener.onMinusItemQuantity(cart, cartPosition);
                if (ItemCount == 0){
//                    ObjectMapper mapper = new ObjectMapper();
//                    try {
//                        List<User> userList1 = mapper.readValue(new File(v.getContext().getFilesDir(),
//                                "Users.json"), new TypeReference<List<User>>(){});
//                        List<CartResponseModel> cartItems = userList1.get(sharedPreference.getPresentUserId()).getCartItemList();
//                        try {
//                            cartItems.remove(cart.getBook().getBookID());
//                        }catch (IndexOutOfBoundsException e){
//                            e.printStackTrace();
//                        }
//                        userList1.get(sharedPreference.getPresentUserId()).setCartItemList(cartItems);
//                        String updatedFile = mapper.writeValueAsString(userList1);
//                        FileOutputStream fos = v.getContext().openFileOutput("Users.json", Context.MODE_PRIVATE);
//                        fos.write(updatedFile.getBytes());
//                        fos.close();
//                    } catch (IOException e){
//                        e.printStackTrace();
//                    }
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new CartFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
               }

            }

        });
    }

    private float displayPrices(int item) {
        itemCount.setText(String.valueOf(item));
        totalBookPrice = cart.getBook().getBookPrice() * item;
        return totalBookPrice;
    }

}

package com.example.bridgelabz.bookstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.CartModel;

import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartItemViewHolder> {

    private List<CartModel> book_cart_items = new ArrayList<>();
    private CartBookClickListener cartBookClickListener;

    public CartListAdapter(List<CartModel> book_cart_items,CartBookClickListener cartBookClickListener) {
        this.book_cart_items = book_cart_items;
        this.cartBookClickListener = cartBookClickListener;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.cart_list_item, parent, false);
        return new CartItemViewHolder(view,cartBookClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartModel cartModel = book_cart_items.get(position);
        holder.bind(cartModel, position);
    }




    @Override
    public int getItemCount() {
        return book_cart_items.size();
    }

    public Book getItem(int position) {
        try{
            return book_cart_items.get(position).getBook();
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<CartModel> getCartBooksList() {
        return book_cart_items;
    }

    public void setCartBooksList(List<CartModel> cartBooksList) {
        this.book_cart_items = cartBooksList;
    }

    public void removeAt(int position) {
        try{
            book_cart_items.remove(position);
            notifyItemRemoved(position);
        } catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

}
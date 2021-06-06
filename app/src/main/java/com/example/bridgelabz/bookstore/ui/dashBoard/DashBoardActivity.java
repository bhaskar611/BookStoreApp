package com.example.bridgelabz.bookstore.ui.dashBoard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.fragments.CartFragment;
import com.example.bridgelabz.bookstore.fragments.WishListFragment;
import com.example.bridgelabz.bookstore.fragments.bookListFragment;
import com.example.bridgelabz.bookstore.ui.Authentication.LoginActivity;
import com.example.bridgelabz.bookstore.ui.Authentication.RegisterActivity;

public class DashBoardActivity extends AppCompatActivity {
    SharedPreference sharedPreference;
    bookListFragment booklistFragment;
    WishListFragment wishListFragment;
    CartFragment cartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        sharedPreference = new SharedPreference(this);
        booklistFragment = new bookListFragment();
        wishListFragment = new WishListFragment();
        cartFragment = new CartFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    booklistFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_menu, menu);
        MenuItem logout = menu.findItem(R.id.sign_out);
        MenuItem wishList = menu.findItem(R.id.favourites);
        MenuItem cartList = menu.findItem(R.id.cart);
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                sharedPreference.setLoggedIN(false);
                startActivity(new Intent(DashBoardActivity.this, LoginActivity.class));
                return false;
            }
        });

        wishList.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        wishListFragment).addToBackStack(null).commit();
                return false;
            }
        });

        cartList.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        cartFragment).addToBackStack(null).commit();
                return false;
            }
        });
        return true;
    }
}
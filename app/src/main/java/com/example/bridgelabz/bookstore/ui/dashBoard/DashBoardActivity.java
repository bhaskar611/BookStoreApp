package com.example.bridgelabz.bookstore.ui.dashBoard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.CartRepository;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.fragments.CartFragment;
import com.example.bridgelabz.bookstore.fragments.Order_Fragment;
import com.example.bridgelabz.bookstore.fragments.ProfileFragment;
import com.example.bridgelabz.bookstore.fragments.WishListFragment;
import com.example.bridgelabz.bookstore.fragments.bookListFragment;
import com.example.bridgelabz.bookstore.ui.Authentication.LoginActivity;
import com.example.bridgelabz.bookstore.ui.Authentication.RegisterActivity;

public class DashBoardActivity extends AppCompatActivity implements AddBadge {
    SharedPreference sharedPreference;
    bookListFragment booklistFragment;
    WishListFragment wishListFragment;
    CartFragment cartFragment;
    CartRepository cartRepository;
    Order_Fragment order_fragment;
    ProfileFragment profileFragment;
    TextView textCartItemCount;
    int badges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        sharedPreference = new SharedPreference(this);
        booklistFragment = new bookListFragment();
        wishListFragment = new WishListFragment();
        cartFragment = new CartFragment();
        order_fragment = new Order_Fragment();
        profileFragment = new ProfileFragment();
        cartRepository = new CartRepository(this);

         badges = cartRepository.getCartList().size();

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
        MenuItem orderList = menu.findItem(R.id.orders);
        MenuItem profilePage = menu.findItem(R.id.profile);
        //final MenuItem menuItem = menu.findItem(R.id.cart);
        View actionView = cartList.getActionView();
        View actionView1 = logout.getActionView();
        View actionView2 = wishList.getActionView();
        View actionView3 = orderList.getActionView();
        View actionView4 = profilePage.getActionView();
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);
//
       //setupBadge();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(cartList);
            }
        });
//        actionView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onOptionsItemSelected(logout);
//            }
//        });
//        actionView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onOptionsItemSelected(wishList);
//            }
//        });
//        actionView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onOptionsItemSelected(orderList);
//            }
//        });
//        actionView4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onOptionsItemSelected(profilePage);
//            }
//        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.cart: {
                // Do something
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                       cartFragment).addToBackStack(null).commit();
//
                return true;
            }
            case R.id.sign_out: {
                // Do something
                sharedPreference.setLoggedIN(false);
                startActivity(new Intent(DashBoardActivity.this, LoginActivity.class));
//
                return true;
            }
            case R.id.favourites: {
                // Do something
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        wishListFragment).addToBackStack(null).commit();
//
                return true;
            }
            case R.id.orders: {
                // Do something
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                       order_fragment).addToBackStack(null).commit();
//
                return true;
            }
            case R.id.profile: {
                // Do something
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                       profileFragment).addToBackStack(null).commit();
//
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }


//        profilePage.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        profileFragment).addToBackStack(null).commit();
//                return false;
//            }
//        });
//
//        orderList.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        order_fragment).addToBackStack(null).commit();
//                return false;
//            }
//        });
//        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                sharedPreference.setLoggedIN(false);
//                startActivity(new Intent(DashBoardActivity.this, LoginActivity.class));
//                return false;
//            }
//        });
//
//        wishList.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        wishListFragment).addToBackStack(null).commit();
//                return false;
//            }
//        });
//
//        cartList.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        cartFragment).addToBackStack(null).commit();
//                return false;
//            }
//        });
//        return true;
//    }

    public void setupBadge() {
        if (textCartItemCount != null) {
            if (badges == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(badges, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onAddCart(int count) {

        if (textCartItemCount != null) {
            if (count == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(count, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }

    }
}
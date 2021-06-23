package com.example.bridgelabz.bookstore.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.CartRepository;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.adapter.OnOrderListner;
import com.example.bridgelabz.bookstore.adapter.OrderAdapter;
import com.example.bridgelabz.bookstore.model.CartModel;
import com.example.bridgelabz.bookstore.model.Order;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OrderFragment extends Fragment {

    private static final String TAG = "OrderFragment";
    Order order;
    long orderID;
    CartRepository cartRepository;
    RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    int spanCount;
    Date date;
    Calendar calendar;
    SharedPreference sharedPreference;
    OrderViewFragment orderViewFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_, container, false);
        sharedPreference = new SharedPreference(this.getContext());
//        orderID = Order_Placed_Fragment.orderNo;
//        calendar.getTime();
//        cartRepository = new CartRepository(this.getContext());
//        List<CartModel> cartList = cartRepository.getCartList();
//        date =calendar.getTime();
//        order = new Order(orderID,cartList,date);
//        List<Order> orderList = new ArrayList<>();
//        orderList.add(order);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            spanCount = 2;
        } else {
            // In portrait
            spanCount = 1;
        }
       List<Order> orderList = getAllOrders();
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = view.findViewById(R.id.order_RecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
       orderAdapter = new OrderAdapter(orderList, new OnOrderListner() {
           @Override
           public void onOrderClick(int position, View viewHolder) {
             ArrayList<CartModel>  cartModelList = (ArrayList<CartModel>) getAllOrders().get(position).getCart_items();
               Log.e(TAG, "onOrderClick: " + cartModelList );
               orderViewFragment = new OrderViewFragment();
               Bundle bundle = new Bundle();
               bundle.putSerializable("list", cartModelList);
              // bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) cartModelList);
               orderViewFragment.setArguments(bundle);
               getParentFragmentManager().beginTransaction()
                       .replace(R.id.fragment_container, orderViewFragment)
                       .addToBackStack(null).commit();

           }
       });
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.notifyDataSetChanged();
        onBackPressed(view);
        return view;

    }

    private List<Order> getAllOrders() {
        ObjectMapper mapper = new ObjectMapper();
        List<Order> orderList = null;
        try {
            List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(), "Users.json"),new TypeReference<List<User>>(){} );
             orderList = userList1.get(sharedPreference.getPresentUserId()).getOrderList();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    private void onBackPressed(View view) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.order_toolbar);
        toolbar.setTitle("Orders");
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
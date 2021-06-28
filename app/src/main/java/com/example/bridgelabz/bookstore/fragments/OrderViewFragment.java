package com.example.bridgelabz.bookstore.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.adapter.OrderViewAdapter;
import com.example.bridgelabz.bookstore.model.Address;
import com.example.bridgelabz.bookstore.model.CartModel;
import com.example.bridgelabz.bookstore.model.Order;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class OrderViewFragment extends Fragment {

    RecyclerView recyclerView;
    private OrderViewAdapter orderViewAdapter;
    int spanCount;
    TextView orderDate,orderNo,totalPrice, deliveredAddressTextView;
    String date;
    long orderID;
    float orderTotal;
    SharedPreference sharedPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_view, container, false);
        sharedPreference = new SharedPreference(this.getContext());
        orderDate = view.findViewById(R.id.textView39);
        orderNo = view.findViewById(R.id.textView42);
        totalPrice = view.findViewById(R.id.textView44);
        deliveredAddressTextView = view.findViewById(R.id.order_detail_delivered_address_text_view);
        ArrayList<CartModel> list = (ArrayList<CartModel>) getArguments().getSerializable("list");
       // ArrayList<OrderFragment> list = bundle.getParcelableArrayList("list");
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            date = getArguments().getString("date");
            orderID = getArguments().getLong("orderID");
            orderTotal = getArguments().getFloat("totalPrice");
        }
        orderDate.setText(date);
        orderNo.setText(String.valueOf(orderID));
        totalPrice.setText(String.valueOf(orderTotal));
        Order order = getOrderById(orderID);
        Address deliveredAddress = getAddressById(order.getDeliveryAddressId());
        String deliveredAddressString = deliveredAddress.getHouseNo() + ", \n"
                + deliveredAddress.getStreet() + ", \n"
                + deliveredAddress.getCity() + ", \n"
                + deliveredAddress.getState() + ", "
                + deliveredAddress.getPincode();
        deliveredAddressTextView.setText(deliveredAddressString);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            spanCount = 2;
        } else {
            // In portrait
            spanCount = 1;
        }
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = view.findViewById(R.id.order_View_recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        orderViewAdapter = new OrderViewAdapter(list);
        recyclerView.setAdapter(orderViewAdapter);
        orderViewAdapter.notifyDataSetChanged();

        onBackPressed(view);

        return view;
    }

    private Order getOrderById(long orderId) {

        User user = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(),
                    "Users.json"), new TypeReference<List<User>>(){});
            user = userList1.get(sharedPreference.getPresentUserId());
        } catch (IOException jsonParseException) {
            jsonParseException.printStackTrace();
        }
        List<Order> orderList = user.getOrderList();
        for(Order order : orderList) {
            if (order.getOrderID() == orderId){
                return order;
            }
        }
        return null;
    }

    public Address getAddressById(long addressId) {

        User user = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(),
                    "Users.json"), new TypeReference<List<User>>(){});
            user = userList1.get(sharedPreference.getPresentUserId());
        } catch (IOException jsonParseException) {
            jsonParseException.printStackTrace();
        }
        List<Address> userAddressList = user.getUserAddress();
        for(Address addressModel : userAddressList) {
            if (addressModel.getAddressID() == addressId){
                return addressModel;
            }
        }
        return null;    }

    private void onBackPressed(View view) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.orderView_ToolBar);
        toolbar.setTitle( "Order List");
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
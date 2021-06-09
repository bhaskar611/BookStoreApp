package com.example.bridgelabz.bookstore.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.CartRepository;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.model.CartModel;
import com.example.bridgelabz.bookstore.model.CartResponseModel;
import com.example.bridgelabz.bookstore.model.Order;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Order_Placed_Fragment extends Fragment {

    TextView orderPlaced,dateTime;
    Button cont_Shopping;
    SharedPreference sharedPreference;
    Order order;
    public static String date;
    public static long orderNo;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    CartRepository cartRepository;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       order = new Order();
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        orderPlaced = view.findViewById(R.id.textView10);
        dateTime = view.findViewById(R.id.textView22);
        cont_Shopping = view.findViewById(R.id.button2);
        calendar = Calendar.getInstance();
        //orderid + date + time
        sharedPreference = new SharedPreference(this.getContext());
        cartRepository = new CartRepository(this.getContext());
        orderNo = System.currentTimeMillis();
        orderPlaced.setText(String.valueOf(orderNo) );
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        dateTime.setText(date);

//        ObjectMapper mapper = new ObjectMapper();
//        try {
//                List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(),
//                        "Users.json"), new TypeReference<List<User>>(){});
//                List<Order> orderID = userList1.get(sharedPreference.getPresentUserId()).getOrderList();
//                orderID.add(order.setOrderID((orderNo)));
//                userList1.get(sharedPreference.getPresentUserId()).setOrderList();
//                String updatedFile = mapper.writeValueAsString(userList1);
//                FileOutputStream fos = getContext().openFileOutput("Users.json", Context.MODE_PRIVATE);
//                fos.write(updatedFile.getBytes());
//                fos.close();
//
//            } catch (IOException jsonParseException) {
//                jsonParseException.printStackTrace();
//            }
        cont_Shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaddata();
                Fragment fragment = new bookListFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        onBackPressed(view);


        return view;
    }

    private void loaddata() {
        ObjectMapper mapper = new ObjectMapper();
//        String date = Order_Placed_Fragment.date;
//        long orderID = Order_Placed_Fragment.orderNo;
        List<CartModel> cartList = cartRepository.getCartList();
        float price = cartRepository.calculateTotalPrice(cartList);
        order = new Order(orderNo,price
                ,cartList,date);
        try {
            List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(),
                    "Users.json"), new TypeReference<List<User>>(){});
            List<Order> orderList = userList1.get(sharedPreference.getPresentUserId()).getOrderList();
            orderList.add(order);
            userList1.get(sharedPreference.getPresentUserId()).setOrderList(orderList);
            List<CartResponseModel> newCartItemList = new ArrayList<>();
            userList1.get(sharedPreference.getPresentUserId()).setCartItemList(newCartItemList);
            String updatedFile = mapper.writeValueAsString(userList1);
            FileOutputStream fos = getContext().openFileOutput("Users.json", Context.MODE_PRIVATE);
            fos.write(updatedFile.getBytes());
            fos.close();

        } catch (IOException jsonParseException) {
            jsonParseException.printStackTrace();
        }
    }

    private void onBackPressed(View view) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.Order_Placed_ToolBar);
        toolbar.setTitle("Cart List");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle any click event
                loaddata();
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
package com.example.bridgelabz.bookstore.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.CartRepository;
import com.example.bridgelabz.bookstore.Repository.ReviewRepository;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.model.CartModel;
import com.example.bridgelabz.bookstore.model.CartResponseModel;
import com.example.bridgelabz.bookstore.model.Order;
import com.example.bridgelabz.bookstore.model.User;
import com.example.bridgelabz.bookstore.ui.dashBoard.AddBadge;
import com.example.bridgelabz.bookstore.ui.dashBoard.DashBoardActivity;
import com.example.bridgelabz.bookstore.workManager.MyWorker;
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

public class OrderPlacedFragment extends Fragment {

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
        File reviewsFile = new File(getContext().getFilesDir(), "reviews.json");
        cartRepository = new CartRepository(getContext(),new ReviewRepository(reviewsFile));
        orderNo = System.currentTimeMillis();
        orderPlaced.setText(String.valueOf(orderNo) );
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        dateTime.setText(date);

        final OneTimeWorkRequest.Builder workRequest =
                new OneTimeWorkRequest.Builder(MyWorker.class);
        Data.Builder data = new Data.Builder();
        data.putString(MyWorker.NOTIFICATION_CHANNEL_ID, "order");
        data.putString(MyWorker.NOTIFICATION_CHANNEL, "ORDERS");
        data.putString(MyWorker.NOTIFICATION_TITLE, "Order Placed Success");
        data.putString(MyWorker.NOTIFICATION_MESSAGE, "Track the Order with Order Id : " + orderNo );
        workRequest.setInputData(data.build());
        WorkManager.getInstance(getContext()).enqueue(workRequest.build());

        cont_Shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ((AddBadge) requireActivity()).onAddCart(0);
                    //This will invoke the implemented method in your activity class. You
                    //can pass any type of value through to your activity. Just add the
                    //parameter in your interface declaration.
                }catch (ClassCastException e){
                    e.printStackTrace();
                }
                loaddata();
                AppCompatActivity activity = ((AppCompatActivity) getActivity());
                if(activity != null) {
                    activity.getSupportFragmentManager()
                            .popBackStack(DashBoardActivity.BACK_STACK_TAG_CART_FLOW,
                                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            }
        });




        return view;
    }


    private void loaddata() {
        ObjectMapper mapper = new ObjectMapper();
//        String date = Order_Placed_Fragment.date;
//        long orderID = Order_Placed_Fragment.orderNo;
        List<CartModel> cartList = cartRepository.getCartList();
        float price = cartRepository.calculateTotalPrice(cartList);
        Bundle bundle = this.getArguments();
        long deliveryAddressId = bundle.getLong("deliveryAddressId");
        order = new Order(orderNo,price
                ,cartList,date,deliveryAddressId);
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
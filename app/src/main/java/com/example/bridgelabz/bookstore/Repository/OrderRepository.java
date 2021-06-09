//package com.example.bridgelabz.bookstore.Repository;
//
//import android.content.Context;
//
//import com.example.bridgelabz.bookstore.SharedPreference;
//import com.example.bridgelabz.bookstore.fragments.Order_Fragment;
//import com.example.bridgelabz.bookstore.fragments.Order_Placed_Fragment;
//import com.example.bridgelabz.bookstore.model.CartModel;
//import com.example.bridgelabz.bookstore.model.Order;
//import com.example.bridgelabz.bookstore.model.User;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//public class OrderRepository {
//
//    private Context context;
//    SharedPreference sharedPreference;
//    Order order;
//    CartRepository cartRepository;
//
//    public OrderRepository (Context context) {
//        this.context = context;
//        sharedPreference = new SharedPreference(context);
//        cartRepository = new CartRepository(context);
//    }
//
//    public List<Order> OrderList(){
//        ObjectMapper mapper = new ObjectMapper();
//        String date = Order_Placed_Fragment.date;
//        long orderID = Order_Placed_Fragment.orderNo;
//        List<CartModel> cartList = cartRepository.getCartList();
//        order = new Order(orderID,cartList,date);
//        try {
//            List<User> userList1 = mapper.readValue(new File(context.getFilesDir(),
//                    "Users.json"), new TypeReference<List<User>>(){});
//            List<Order> orderList = userList1.get(sharedPreference.getPresentUserId()).getOrderList();
//            orderList.add(order);
//            userList1.get(sharedPreference.getPresentUserId()).setOrderList(orderList);
//            String updatedFile = mapper.writeValueAsString(userList1);
//            FileOutputStream fos = context.openFileOutput("Users.json", Context.MODE_PRIVATE);
//            fos.write(updatedFile.getBytes());
//            fos.close();
//
//        } catch (IOException jsonParseException) {
//            jsonParseException.printStackTrace();
//        }
//
//
//    }
//}

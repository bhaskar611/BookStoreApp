//package com.example.bridgelabz.bookstore.dataManager;
//
//import android.content.Context;
//import android.content.res.AssetManager;
//import android.util.Log;
//import android.widget.Adapter;
//import android.widget.Toast;
//
//import androidx.cardview.widget.CardView;
//
//import com.example.bridgelabz.bookstore.model.Book;
//import com.example.bridgelabz.bookstore.model.User;
//import com.example.bridgelabz.bookstore.ui.dashBoard.DashBoardActivity;
//import com.example.bridgelabz.bookstore.util.CallBack;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BookListManager {
//    public Context context;
//    public BookListManager(Context context){
//        this.context = context;
//    }
//
//
//    public void getAllBooks(CallBack listener) {
////        try {
////
////            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
////            String responseCode = jsonObject.getString("responseCode");
////            String responseMessage = jsonObject.getString("responseMessage");
////            String responseTime = jsonObject.getString("responseTime");
////
////            Log.e("keshav", "responseCode -->" + responseCode);
////            Log.e("keshav", "responseMessage -->" + responseMessage);
////            Log.e("keshav", "responseTime -->" + responseTime);
////
////
////            if(responseCode.equals("200")){
////
////            }else{
//////                Toast.makeText(this, "No Receord Found ", Toast.LENGTH_SHORT).show();
////            }
////
////            JSONArray jsonArray = jsonObject.getJSONArray("employeesList");                  //TODO pass array object name
////            Log.e("keshav", "m_jArry -->" + jsonArray.length());
////
////
////            for (int i = 0; i < jsonArray.length(); i++)
////            {
////                EmployeeModel employeeModel = new EmployeeModel();
////
////                JSONObject jsonObjectEmployee = jsonArray.getJSONObject(i);
////
////
////                String empId = jsonObjectEmployee.getString("empId");
////                String empName = jsonObjectEmployee.getString("empName");
////                String empDesignation = jsonObjectEmployee.getString("empDesignation");
////                String empSalary = jsonObjectEmployee.getString("empSalary");
////                String empFatherName = jsonObjectEmployee.getString("empFatherName");
////
////                employeeModel.setEmpId(""+empId);
////                employeeModel.setEmpName(""+empName);
////                employeeModel.setEmpDesignation(""+empDesignation);
////                employeeModel.setEmpSalary(""+empSalary);
////                employeeModel.setEmpFatherNamer(""+empFatherName);
////
////                employeeModelArrayList.add(employeeModel);
////
////            }       // for
////
////            if(employeeModelArrayList!=null) {
////                employeeAdapter.dataChanged(employeeModelArrayList);
////            }
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }t
//        ObjectMapper mapper = new ObjectMapper();
//
//        try{
//            List<Book>  userList1 = mapper.readValue(new File(Context.getAssets().open("Books.json")),new TypeReference<List<Book>>(){} );
////            for (int i = 0; i < userList1.size(); i++){
////                String bookName = userList1.get(i).getBookAuthor();
////                String bookAuthor = userList1.get(i).getBookAuthor();
////                String bookImage = userList1.get(i).getBookImage();
////                Book book =new Book(bookName,bookAuthor,bookImage);
////
////            }
//            listener.onSuccess(userList1);
//        }catch (IOException e) {
//            listener.onFailure(e);
//            e.printStackTrace();
//        }
//
////        ObjectMapper mapper = new ObjectMapper();
////        List<Book>  userList1 = mapper.readValue(new File(context.getAssets().open("Books.json")),new TypeReference<List<Book>>(){} );
//
//    }
//
////    private String loadJSONFromAsset() {
////        String json = null;
////        try {
////            InputStream is = context.getAssets().open("Books.json");  //TODO Json File  name from assets folder
////            int size = is.available();
////            byte[] buffer = new byte[size];
////
////            is.close();
////            json = new String(buffer, "UTF-8");
////        } catch (IOException ex) {
////            ex.printStackTrace();
////            return null;
////        }
////        return json;
////    }
//
//}

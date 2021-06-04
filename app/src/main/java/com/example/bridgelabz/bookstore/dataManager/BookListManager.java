package com.example.bridgelabz.bookstore.dataManager;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.bridgelabz.bookstore.adapter.BookListAdapter;
import com.example.bridgelabz.bookstore.model.Book;
import com.example.bridgelabz.bookstore.model.User;
import com.example.bridgelabz.bookstore.ui.dashBoard.DashBoardActivity;
import com.example.bridgelabz.bookstore.util.CallBack;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BookListManager {
    public Context context;
    public BookListManager(Context context){
        this.context = context;
    }


//    private void getBooks(CallBack listener) {
//
//
//        try {
//            String data = loadJSONFromAsset();
//            ObjectMapper mapper = new ObjectMapper();
//            ArrayList<Book> bookArrayList = mapper.readValue(data, new TypeReference<List<Book>>(){} );
//            bookListAdapter = new BookListAdapter(bookArrayList);
//            recyclerView.setAdapter(bookListAdapter);
//            bookListAdapter.notifyDataSetChanged();
//
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//        private String loadJSONFromAsset() {
//            String json = null;
//            try {
//                InputStream is = .getAssets().open("Books.json");
//                int size = is.available();
//                byte[] buffer = new byte[size];
//                is.read(buffer);
//                is.close();
//                json = new String(buffer, "UTF-8");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//                return null;
//            }
//            Log.e(TAG, "loadJSONFromAsset: " + json );
//            return json;
//
//        }


}


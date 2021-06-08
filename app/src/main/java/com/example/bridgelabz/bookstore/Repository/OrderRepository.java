package com.example.bridgelabz.bookstore.Repository;

import android.content.Context;

import com.example.bridgelabz.bookstore.model.Order;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class OrderRepository {

    public List<Order> OrderList(){
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(),
                    "Users.json"), new TypeReference<List<User>>(){});
            List<Integer> favoriteBooks = userList1.get(sharedPreference.getPresentUserId()).getFavouriteItemsList();
            favoriteBooks.add(book.getBookID());
            userList1.get(sharedPreference.getPresentUserId()).setFavouriteItemsList(favoriteBooks);
            String updatedFile = mapper.writeValueAsString(userList1);
            FileOutputStream fos = itemView.getContext().openFileOutput("Users.json", Context.MODE_PRIVATE);
            fos.write(updatedFile.getBytes());
            fos.close();

        } catch (IOException jsonParseException) {
            jsonParseException.printStackTrace();
        }

    }
}

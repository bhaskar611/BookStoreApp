package com.example.bridgelabz.bookstore.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ProfileFragment extends Fragment {
    TextView userName,userEmail;
    Button userAddress;
    ImageView userPic;
    SharedPreference sharedPreference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        userName = view.findViewById(R.id.profileName);
        userEmail = view.findViewById(R.id.profileEmail);
        userPic = view.findViewById(R.id.view4);
        sharedPreference = new SharedPreference(this.getContext());
        String username = null;
        String useremail = null;
        String userImage = null;
        userPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });


        ObjectMapper mapper = new ObjectMapper();

        try {
            List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(), "Users.json"),new TypeReference<List<User>>(){} );
            username = userList1.get(sharedPreference.getPresentUserId()).getUserName();
            useremail = userList1.get(sharedPreference.getPresentUserId()).getEmail();
            userImage = userList1.get(sharedPreference.getPresentUserId()).getUserImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userName.setText(username);
        userEmail.setText(useremail);
        Glide.with(getContext()).load(userImage).into(userPic);
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                Glide.with(getContext()).load(imageUri).into(userPic);
                String uriImage = imageUri.toString();
                //profileImage.setImageURI(imageUri);
                uploadImageToUserFile(uriImage);
            }
        }

    }

    private void uploadImageToUserFile(String imageUri) {
        // uplaod image to firebase storage
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<User> userList1 = mapper.readValue(new File(getContext().getFilesDir(),
                    "Users.json"), new TypeReference<List<User>>(){});
//            List<Order> orderList = userList1.get(sharedPreference.getPresentUserId()).getOrderList();
//            orderList.add(order);
            userList1.get(sharedPreference.getPresentUserId()).setUserImage(imageUri);
            String updatedFile = mapper.writeValueAsString(userList1);
            FileOutputStream fos = getContext().openFileOutput("Users.json", Context.MODE_PRIVATE);
            fos.write(updatedFile.getBytes());
            fos.close();

        } catch (IOException jsonParseException) {
            jsonParseException.printStackTrace();
        }

    }

}
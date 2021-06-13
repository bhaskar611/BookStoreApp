package com.example.bridgelabz.bookstore.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.adapter.Address_Pick_Adapter;
import com.example.bridgelabz.bookstore.adapter.OnAddressListener;
import com.example.bridgelabz.bookstore.model.Address;
import com.example.bridgelabz.bookstore.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfileFragment extends Fragment {
    TextView userName,userEmail;
    Button userAddress;
    ImageView userPic;
    SharedPreference sharedPreference;
    RecyclerView recyclerView;
    int spanCount;
    private Address_Pick_Adapter address_pick_adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<Address> addressList = new ArrayList<>();
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            spanCount = 2;
        } else {
            // In portrait
            spanCount = 1;
        }
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
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
            int i;
            for (i=0;i<userList1.size();i++) {
                if(userList1.get(i).getUserID() ==  sharedPreference.getPresentUserId()) {
//                    isLoggedIN = true;
                    addressList = userList1.get(i).getUserAddress();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView = view.findViewById(R.id.Profile_Address);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        address_pick_adapter = new Address_Pick_Adapter(addressList, new OnAddressListener() {
            @Override
            public void onAddressClick(int position, View viewHolder) {
                Toast.makeText(getContext(), "Address is in profile", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(address_pick_adapter);
        address_pick_adapter.notifyDataSetChanged();
        userName.setText(username);
        userEmail.setText(useremail);
        Glide.with(getContext()).load(userImage).into(userPic);
        onBackPressed(view);
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
    private void onBackPressed(View view) {

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.profile_toolbar);
        toolbar.setTitle("Profile");
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
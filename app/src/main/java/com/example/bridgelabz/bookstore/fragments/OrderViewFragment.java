package com.example.bridgelabz.bookstore.fragments;

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

import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.adapter.OrderViewAdapter;
import com.example.bridgelabz.bookstore.model.CartModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class OrderViewFragment extends Fragment {

    RecyclerView recyclerView;
    private OrderViewAdapter orderViewAdapter;
    int spanCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_view, container, false);
        Bundle bundle = this.getArguments();
        ArrayList<CartModel> list = (ArrayList<CartModel>) getArguments().getSerializable("list");
       // ArrayList<OrderFragment> list = bundle.getParcelableArrayList("list");
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
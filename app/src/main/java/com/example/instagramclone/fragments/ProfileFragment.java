package com.example.instagramclone.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.instagramclone.EndlessRecyclerViewScrollListener;
import com.example.instagramclone.LoginActivity;
import com.example.instagramclone.Post;
import com.example.instagramclone.PostsAdapter;
import com.example.instagramclone.databinding.FragmentPostsBinding;
import com.example.instagramclone.databinding.FragmentProfileBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends BasePostsFragment {

    public static final String TAG = "ProfileFragment";
    FragmentProfileBinding binding;
    protected EndlessRecyclerViewScrollListener scrollListener;
    protected Integer offset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Allow user to logout
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

        super.onViewCreated(view, savedInstanceState);

        offset = 0;

        // initialize the array that will hold posts and create a PostsAdapter
        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts, true);

        // set the adapter on the recycler view
        binding.rvProfile.setAdapter(adapter);
        // set the layout manager on the recycler view
        binding.rvProfile.setLayoutManager(layoutManager);

        // Lookup the swipe container view

        // Setup refresh listener which triggers new data loading
        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTimelineAsync(0);
            }
        });
        // Configure the refreshing colors
        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                Log.i(TAG, "onLoadMore called");
                queryPosts(adapter.getItemCount() );
            }
        };
        // Adds the scroll listener to RecyclerView
        binding.rvProfile.addOnScrollListener(scrollListener);

        // query posts from Parstagram
        queryPosts(0);
    }


    @Override
    protected String getLocalTag() {
        return TAG;
    }
    @Override
    protected Boolean isUserProfile() {
        return true;
    }

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        adapter.clear();
        queryPosts(0);
        binding.swipeContainer.setRefreshing(false);
        scrollListener.resetState();
    }

    private void logoutUser() {
        ParseUser.logOut();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}

package com.example.instagramclone.fragments;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.instagramclone.Post;
import com.example.instagramclone.PostsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

abstract public class BasePostsFragment extends Fragment {

    abstract protected String getLocalTag();
    abstract protected Boolean isUserProfile();


    protected PostsAdapter adapter;
    protected List<Post> allPosts;


    protected void queryPosts(int offset) {
        // specify what type of data we want to query - Post.class
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // include data referred by user key
        query.include(Post.KEY_USER);
        // limit posts shown to only that of current user
        if (isUserProfile()) {
            query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        }
        // limit query to latest 20 items
        query.setLimit(20);
        // get page specified by skipping offset items
        query.setSkip(offset);
        // order posts by creation date (newest first)
        query.addDescendingOrder("createdAt");
        // start an asynchronous call for posts
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                // check for errors
                if (e != null) {
                    Log.e(getLocalTag(), "Issue with getting posts", e);
                    return;
                }

                // for debugging purposes let's print every post description to logcat
                for (Post post : posts) {
                    Log.i(getLocalTag(), "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }

                // save received posts to list and notify adapter of new data
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }

}

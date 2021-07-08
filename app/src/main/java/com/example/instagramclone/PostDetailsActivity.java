package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.instagramclone.databinding.ActivityPostDetailsBinding;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PostDetailsActivity extends AppCompatActivity {
    private Post post;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPostDetailsBinding binding = ActivityPostDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        //setContentView(R.layout.activity_post_details);
        setContentView(view);
        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));
        binding.tvDescriptionDet.setText(post.getDescription());
        binding.tvUsernameDet.setText(post.getUser().getUsername());
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(binding.ivImageDet);
        }
        Date createdAt = post.getCreatedAt();
        String timeAgo = Post.calculateTimeAgo(createdAt);
        binding.tvTimeStampDet.setText(timeAgo);

    }


}


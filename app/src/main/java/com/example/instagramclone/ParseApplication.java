package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("kKvnZRoo4Gl7mkMAvY2uuwruAMsktVhU8BfygVQ7")
                .clientKey("JEbNJW3AEX7wyaP1bDsNdTtyUpQX4dKwqqftX5KB")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}

package com.example.littleBIG.myapplication.backend;

import com.example.jokes;

/**
 * Created by littleBIG on 12/16/2015.
 */

public class MyJoke {

    String mJoke;

    public String getJoke() {
        jokes java_joke = new jokes();
        mJoke = java_joke.getJoke();
        return mJoke;
    }

    public void setJoke(String joke) {
        mJoke = joke;
    }
}
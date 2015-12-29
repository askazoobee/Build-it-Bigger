package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jokes;
import com.example.littlebig.androidjokes.MainJokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.JokeAsyncTask;
import com.udacity.gradle.builditbigger.R;


public class MainActivity extends ActionBarActivity {

    String joke_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        setupAd();
    }


    public void setupAd(){
        AdView mAdView = (AdView)findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    public void tellJoke(View view){

        JokeAsyncTask task = new JokeAsyncTask();
        task.execute(getApplicationContext());

/*        jokes java_joke = new jokes();
        joke_text = java_joke.getJoke();

        Intent myIntent = new Intent(this, MainJokeActivity.class);
        myIntent.putExtra(Intent.EXTRA_TEXT,joke_text);
        startActivity(myIntent);*/

        // Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();

    }

}

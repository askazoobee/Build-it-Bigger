package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jokes;
import com.example.littlebig.androidjokes.MainJokeActivity;

public class MainActivity extends ActionBarActivity {

String joke_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
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

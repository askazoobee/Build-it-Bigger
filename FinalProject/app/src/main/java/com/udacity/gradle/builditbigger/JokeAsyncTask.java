package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.example.littlebig.androidjokes.MainJokeActivity;
import com.example.littlebig.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by littleBIG on 12/16/2015.
 */
 public class JokeAsyncTask extends AsyncTask<Context, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private JokeAsyncTaskListener mListener = null;
    private Exception mError = null;


    public interface JokeAsyncTaskListener {
        void onComplete(String jsonString, Exception e);
    }

    public JokeAsyncTask setListener(JokeAsyncTaskListener listener) {
        this.mListener = listener;
        return this;
    }

        @Override
        protected String doInBackground(Context... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0];

            try {
                return myApiService.tellJoke().execute().getJoke();
            } catch (IOException e) {
                return e.getMessage();
            }
        }



    @Override
    protected void onCancelled() {
        if (this.mListener != null) {
            mError = new InterruptedException("AsyncTask cancelled");
            this.mListener.onComplete(null, mError);
        }
    }



        @Override
        protected void onPostExecute(String result) {
            if (this.mListener != null) {
                this.mListener.onComplete(result, mError);
            } else {
                Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(context.getApplicationContext(), MainJokeActivity.class);
                myIntent.putExtra(Intent.EXTRA_TEXT, result);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);
            }
        }

    }


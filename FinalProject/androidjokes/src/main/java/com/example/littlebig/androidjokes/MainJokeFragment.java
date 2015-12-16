package com.example.littlebig.androidjokes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainJokeFragment extends Fragment {
    public static String joke_extra;
    public MainJokeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.joke_main, container, false);
        //Inspect the intent for joke data.
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            joke_extra = intent.getStringExtra(Intent.EXTRA_TEXT);
        }

        TextView textView = (TextView)root.findViewById(R.id.android_joke);
        textView.setText(joke_extra);

        // Inflate the layout for this fragment
        return root;
    }

}

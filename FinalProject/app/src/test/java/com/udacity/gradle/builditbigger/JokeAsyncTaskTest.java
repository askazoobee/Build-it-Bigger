package com.udacity.gradle.builditbigger;

import android.text.TextUtils;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by littleBIG on 12/21/2015.
 */
public class JokeAsyncTaskTest {

    String mresult;
    JokeAsyncTask async;


    //does not seem signal helps at all in this case...
    //CountDownLatch signal;

    Exception mError = null;

    @Before
    public void setUp() throws Exception {
      //  signal = new CountDownLatch(1);
        async = new JokeAsyncTask();

    }

    @Test
    public void testGetTask() throws InterruptedException {

        JokeAsyncTask task = new JokeAsyncTask();
        task.setListener(new JokeAsyncTask.JokeAsyncTaskListener() {
            @Override
            public void onComplete(String result, Exception e) {
                mresult = result;
                mError = e;
               // signal.countDown();
            }
        }).execute();

      //  signal.await(5, TimeUnit.SECONDS);

        //async ran successfully..
        assertNull(mError);

        //async retrieve joke success/not empty string...
        assertFalse(TextUtils.isEmpty(mresult));

    }

    @After
    public void tearDown() throws Exception {
       //signal.countDown();
    }
}
package com.example.madcw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
/*
 * References:
 * https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-2-activities-and-intents/2-1-c-activities-and-intents/2-1-c-activities-and-intents.html
 * https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-2-activities-and-intents/2-2-c-activity-lifecycle-and-state/2-2-c-activity-lifecycle-and-state.html
 * Lecture 3 - Materials
 */
public class MenuScreen extends AppCompatActivity {
    private static final String LOG_TAG =
            MenuScreen.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchRegister(View view) {
        Log.d(LOG_TAG, "Register Button clicked!");
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void launchDisplay(View view) {
        Log.d(LOG_TAG, "Display Button clicked!");
        Intent intent = new Intent(this, Display.class);
        startActivity(intent);
    }

    public void launchEdit(View view) {
        Log.d(LOG_TAG, "Edit Button clicked!");
        Intent intent = new Intent(this, Edit.class);
        startActivity(intent);
    }

    public void launchFavourites(View view) {
        Log.d(LOG_TAG, "Favourites Button clicked!");
        Intent intent = new Intent(this, Favourites.class);
        startActivity(intent);
    }

    public void launchSearch(View view) {
        Log.d(LOG_TAG, "Search Button clicked!");
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    public void launchRatings(View view) {
        Log.d(LOG_TAG, "Ratings Button clicked!");
        Intent intent = new Intent(this, Ratings.class);
        startActivity(intent);
    }
}
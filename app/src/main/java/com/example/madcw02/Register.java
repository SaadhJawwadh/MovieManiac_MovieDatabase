package com.example.madcw02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    MovieDataBase db;
    EditText titleOfTheMovie;
    EditText year;
    EditText director;
    EditText cast;
    EditText ratings;
    EditText review;
    Button saveButton;
    int minYear = 1895;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new MovieDataBase(this);
        titleOfTheMovie = findViewById(R.id.et_title2);
        year = findViewById(R.id.et_Year2);
        director = findViewById(R.id.et_Director2);
        cast = findViewById(R.id.et_Cast2);
        ratings = findViewById(R.id.et_Rating2);
        review = findViewById(R.id.et_Review2);
        saveButton = findViewById(R.id.savebutton);



        //Rating Validation
        int minRatingValue = 1;
        int maxRatingValue = 10;

        ratings.setInputType(InputType.TYPE_CLASS_NUMBER);
        ratings.setFilters(new InputFilter[]{
                new InputFilterMinMax(minRatingValue, maxRatingValue),
                new InputFilter.LengthFilter(String.valueOf(maxRatingValue).length())
        });
        ratings.setKeyListener(DigitsKeyListener.getInstance("0123456789"));




    }

    public void save(View v) {

        String title = titleOfTheMovie.getText().toString();
        String year = this.year.getText().toString();
        String director = this.director.getText().toString();
        String cast = this.cast.getText().toString();
        String rating = ratings.getText().toString();
        String review = this.review.getText().toString();
        titleOfTheMovie.getText().clear();
        this.year.getText().clear();
        this.director.getText().clear();
        this.cast.getText().clear();
        ratings.getText().clear();
        this.review.getText().clear();

        ArrayList<String> movieArrayList = new ArrayList<>();

        String addedMovieName = String.valueOf(titleOfTheMovie.getText());
        //checking if the movie is added or not
        boolean foundMovie = movieArrayList.contains(addedMovieName);
        if(foundMovie){
            Toast.makeText(Register.this, "Already this movie is added", Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(year) < minYear){
            this.year.setTextColor(Color.RED);
            Toast.makeText(Register.this, "Movies should be released after 1895", Toast.LENGTH_LONG).show();
        }
        else{
            boolean checkInsertData  = db.insertData(title, year, director,cast, rating, review);
            if(checkInsertData)
                Toast.makeText(Register.this, "New Movie Details are Added", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Register.this, "New Entry is not added, TRY AGAIN", Toast.LENGTH_SHORT).show();
        }
    }

    // Validation References: https://stackoverflow.com/questions/41505465/making-edittext-accept-a-range-of-values-without-post-validation
    private static class InputFilterMinMax implements InputFilter {

        private final int min;
        private final int max;

        public InputFilterMinMax(int minRatingValue, int maxRatingValue) {
            this.min = minRatingValue;
            this.max = maxRatingValue;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
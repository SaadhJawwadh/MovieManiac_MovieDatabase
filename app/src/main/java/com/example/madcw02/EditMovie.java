package com.example.madcw02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditMovie extends AppCompatActivity {



    private MovieDataBase db;

    ArrayList<String> selectedItems = new ArrayList<>();
    ListView listView;

    EditText et_name;
    EditText year;
    EditText director;
    EditText cast;
    EditText ratings;
    EditText review;
    EditText favorite;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        db = new MovieDataBase(this);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("Title");
        Toast.makeText(EditMovie.this, name, Toast.LENGTH_LONG).show();

        et_name = findViewById(R.id.et_title2);
        year = findViewById(R.id.et_Year2);
        director = findViewById(R.id.et_Director2);
        cast = findViewById(R.id.et_Cast2);
        ratings = findViewById(R.id.et_Rating2);
        review = findViewById(R.id.et_Review2);
        favorite= findViewById(R.id.et_fav);
        saveButton = findViewById(R.id.savebutton);
        fillFields(name);

    }

    //display list of items

    @SuppressLint("SetTextI18n")
    public void fillFields(String name){
        Cursor res = db.search(name);

        if (res.getCount() == 0) {
            Toast.makeText(EditMovie.this, "Nothing to show", Toast.LENGTH_LONG).show();
        } else {
            while (res.moveToNext()) {
                et_name.append(res.getString(0));
                year.append(res.getString(1));
                director.append(res.getString(2));
                cast.append(res.getString(3));
                ratings.append(res.getString(4));
                review.append(res.getString(5));
                favorite.append(res.getString(6));

                if(res.getString(6).equals("true")) {
                    favorite.setText("Favourite");
                }else{
                    favorite.setText("Not Favourite");
                }
            }
        }
    }
    //update data
    public void save(View v){
        boolean isUpdate = db.updateEditData(
                et_name.getText().toString(),
                year.getText().toString(),
                director.getText().toString(),
                cast.getText().toString(),
                ratings.getText().toString(),
                review.getText().toString(),
                favorite.getText().toString());

        if (isUpdate) {
            Toast.makeText(EditMovie.this, "Data Updated", Toast.LENGTH_LONG).show();
            finish();

        } else {
            Toast.makeText(EditMovie.this, "Data Is Not Updated", Toast.LENGTH_LONG).show();

        }
    }
    @Override
    public void finish() {
        Intent intent = new Intent(this,Edit.class);
        super.finish();
        startActivity(intent);
    }

}
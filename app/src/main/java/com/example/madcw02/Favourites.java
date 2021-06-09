package com.example.madcw02;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Favourites extends AppCompatActivity {
    ArrayList<String> checkedItems1 = new ArrayList<>();

    ListView FavouriteList;
    Button btSave;
    CheckedTextView checked;
    MovieDataBase db;
    Display input=new Display();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        FavouriteList = findViewById(R.id.List_View_Favorite);
        btSave = findViewById(R.id.bt_saveDisplay);
        FavouriteList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        db = new MovieDataBase(this);
        populateListView();
    }

    //display available
    public void populateListView(){

        Cursor data = db.displlayFavorite();
        ArrayList<String> listData1 = new ArrayList<>();
        while (data.moveToNext()) {
            listData1.add(data.getString(0));
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.checkbox, R.id.txt_Availability, listData1);
        FavouriteList.setAdapter(adapter);

        for(int x=0; x<listData1.size(); x++)
            FavouriteList.setItemChecked(x, true);
        FavouriteList.setOnItemClickListener((parent, view, position, id) -> {

            // selected item
            String selectedItem = ((TextView) view).getText().toString();
            if (checkedItems1.contains(selectedItem))
                checkedItems1.remove(selectedItem);
            else
                checkedItems1.add(selectedItem);

        });


    }
    //add checked items to database
    public void saveFavorite(View view){

        StringBuilder checkItems = new StringBuilder();
        for (String item : checkedItems1) {
            db.updateFavorite(item);

            if (checkItems.toString().equals("")) {
                checkItems = new StringBuilder(item);
            }
            else
                checkItems.append("/").append(item);
        }
        Toast.makeText(this, checkItems+" "+"Removed from Favourite", Toast.LENGTH_LONG).show();


    }


}
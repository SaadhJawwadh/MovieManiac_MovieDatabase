package com.example.madcw02;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Display extends AppCompatActivity {

    ArrayList<String> checkedItems = new ArrayList<>();

    ListView listView;
    Button addToKitchen;
    CheckBox checked;

    MovieDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        listView = findViewById(R.id.List_View_Favorite);
        addToKitchen = findViewById(R.id.bt_saveDisplay);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        db = new MovieDataBase(this);
        populateListView();
    }

    //display Movie list
    private void populateListView() {
        Cursor data = db.display();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(0));
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_view_display, R.id.txt_title, listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            // selected item
            String selectedItem = ((TextView) view).getText().toString();
            if (checkedItems.contains(selectedItem))
                checkedItems.remove(selectedItem);
            else
                checkedItems.add(selectedItem);

        });
    }
    //add selected items to data  base
    public void addToFav(View view) {

        StringBuilder checkItems = new StringBuilder();
        for (String item : checkedItems) {
            db.addFavorite(item);

            if (checkItems.toString().equals(""))
                checkItems = new StringBuilder(item);

            else
                checkItems.append("/").append(item);

        }
        Toast.makeText(this, checkItems+" "+"added to Favourite", Toast.LENGTH_LONG).show();

    }


}
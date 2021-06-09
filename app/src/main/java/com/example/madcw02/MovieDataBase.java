package com.example.madcw02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Arrays;
/*
* References:
* https://www.tutorialspoint.com/android/android_sqlite_database.htm
* https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-4-saving-user-data/lesson-10-storing-data-with-room/10-0-c-sqlite-primer/10-0-c-sqlite-primer.html
* https://developer.android.com/training/data-storage/sqlite#java
*- Lecture 5 -  Materials
*/
public class MovieDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movie.db";
    public static final String TABLE_NAME = "MovieDetails";
    public static final String COLUMN_1 = "Title";
    public static final String COLUMN_2 = "Year";
    public static final String COLUMN_3 = "Director";
    public static final String COLUMN_4 = "movieCast";
    public static final String COLUMN_5 = "Rating";
    public static final String COLUMN_6 = "Review";
    public static final String COLUMN_7 = "Favorite";

    public MovieDataBase(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MovieDetails(Title Text PRIMARY KEY , Year Text, Director Text, movieCast Text, Rating Text, Review Text, Favorite Text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAME );
        onCreate(db);
    }
    public boolean insertData(String Title, String Year, String Director, String movieCast, String Rating, String Review ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COLUMN_1,Title);
        contentValues.put(COLUMN_2,Year);
        contentValues.put(COLUMN_3,Director);
        contentValues.put(COLUMN_4,movieCast);
        contentValues.put(COLUMN_5,Rating);
        contentValues.put(COLUMN_6,Review);
        contentValues.put(COLUMN_7,"Null"); // * Added null to assign


        long result=db.insert(TABLE_NAME,null,contentValues);
        return result != -1;

    }
    public Cursor display(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result=db.rawQuery("SELECT * FROM "+MovieDataBase.TABLE_NAME+ " ORDER BY "+MovieDataBase.COLUMN_1 +" ASC"
                , new String[] {} );
        System.out.println(Arrays.toString(result.getColumnNames()));
        return result;
    }


    public Cursor displlayFavorite(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from  "+TABLE_NAME+" WHERE "+COLUMN_7+"= 'true'",null);
    }


    public void addFavorite(String Title){
        SQLiteDatabase db = this.getWritableDatabase();
        String test = "UPDATE "+TABLE_NAME+" SET "+COLUMN_7+" = 'true' WHERE Title = '"+Title+"'";
        System.out.println(test);
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COLUMN_7+" = 'true' WHERE Title = '"+Title+"'");

    }

    public void updateFavorite(String Title){
        SQLiteDatabase db = this.getWritableDatabase();
        String test = "UPDATE "+TABLE_NAME+" SET "+COLUMN_7+" = 'false' WHERE Title = '"+Title+"'";
        System.out.println(test);
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COLUMN_7+" = 'false' WHERE Title = '"+Title+"'");

    }

    public boolean updateEditData(String Title, String Year, String Director, String movieCast, String Rating, String Review, String Favorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1,Title);
        contentValues.put(COLUMN_2,Year);
        contentValues.put(COLUMN_3,Director);
        contentValues.put(COLUMN_4,movieCast);
        contentValues.put(COLUMN_5,Rating);
        contentValues.put(COLUMN_6,Review);
        contentValues.put(COLUMN_7,Favorite);
        db.update(TABLE_NAME, contentValues, "Title = ?",new String[] { Title });
        return true;


    }

    public Cursor search(String letters){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE TITLE OR DIRECTOR OR movieCast LIKE " +
                "'"+letters+"%' OR TITLE LIKE '%"+letters+"%'  OR DIRECTOR LIKE '%"+letters+"%'  OR  movieCast LIKE '%"+letters+"'",null);

    }
}

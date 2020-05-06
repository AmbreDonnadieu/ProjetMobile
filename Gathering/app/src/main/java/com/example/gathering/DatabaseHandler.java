package com.example.gathering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "events_db";

    // Contact table name
    private static final String TABLE_EVENTS = "events";

    // Contact Table Columns names
    private static final String KEY_ID      = "id";
    private static final String KEY_TITRE   = "Title";
    private static final String KEY_DATE   = "Date";
    private static final String KEY_HEURE   = "Heure";
    private static final String KEY_LIEU   = "Lieu";

    private final ArrayList<ExampleItem> eventList = new ArrayList<ExampleItem>();

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DatabaseHandler", "creating database");
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITRE + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_HEURE + " TEXT,"
                + KEY_LIEU + " TEXT" + ")";
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d("DatabaseHandler", "upgrading database");

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        // Create tables again
        onCreate(db);
    }


    public long addEvent(ExampleItem event) {
        long insertId = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITRE, event.getTitre());
        values.put(KEY_DATE, event.getDateEvent());
        values.put(KEY_HEURE, event.getHeureEvent());
        values.put(KEY_LIEU, event.getLieuEvent());

        // Inserting Row
        insertId = db.insert(TABLE_EVENTS, null, values);
        db.close(); // Closing database connection
        return insertId;
    }


    public ExampleItem getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EVENTS,
                new String[] {KEY_ID, KEY_TITRE, KEY_DATE, KEY_HEURE, KEY_LIEU },
                KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ExampleItem event = new ExampleItem(
                Integer.parseInt(cursor.getString(0)), //id
                0, // image
                cursor.getString(1), //titre
                cursor.getString(2),//date
                cursor.getString(3),//heure
                cursor.getString(4));//lieu

        // return event
        cursor.close();
        db.close();

        return event;
    }

    public ArrayList<ExampleItem> getEventList() {
        try {
            eventList.clear();

            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    ExampleItem event = new ExampleItem();
                    //event.(Integer.parseInt(cursor.getString(0)));
                    event.setID(Integer.parseInt(cursor.getString(0)));
                    event.setTitre(cursor.getString(1));
                    event.setDateEvent(cursor.getString(2));
                    event.setHeureEvent(cursor.getString(3));
                    event.setLieuEvent(cursor.getString(4));
                    // Adding contact to list
                    eventList.add(event);
                } while (cursor.moveToNext());
            }
            // return contact list
            cursor.close();
            db.close();
            return eventList;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("getContactList", "" + e);
        }
        return eventList;
    }


    public int updateEvent(ExampleItem event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITRE, event.getTitre());
        values.put(KEY_DATE, event.getDateEvent());
        values.put(KEY_HEURE, event.getHeureEvent());
        values.put(KEY_LIEU, event.getLieuEvent());

        // updating row
        return db.update(TABLE_EVENTS,
                values,
                KEY_ID + " = ?",
                new String[] { String.valueOf(event.getId()) });
    }

    public void removeEvent(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public int getEventSize() {
        String countQuery = "SELECT count(*) FROM " + TABLE_EVENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = 0;
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            count = cursor.getInt(0);
        }

        cursor.close();
        // return count
        return count;
    }

}

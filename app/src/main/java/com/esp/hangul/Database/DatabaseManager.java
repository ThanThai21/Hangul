package com.esp.hangul.Database;

import android.database.Cursor;
import android.media.AudioFormat;

import com.esp.hangul.Home.StudyItem;
import com.esp.hangul.StudyDetail.Item;

import java.sql.Blob;
import java.util.ArrayList;

public class DatabaseManager {

    public static Database database;

    public static ArrayList<Item> colorList() {
        ArrayList<Item> itemArrayList = new ArrayList<>();
        database.opendatabase();
        Cursor cursor = database.myDataBase.rawQuery("SELECT * FROM Color", null);
        if (cursor == null || cursor.getCount() <= 0) {
            return itemArrayList;
        }
        cursor.moveToFirst();
        do {
            String korean = cursor.getString(0);
            String pronuncation = cursor.getString(1);
            String vietnamese = cursor.getString(2);
            byte[] sound = cursor.getBlob(3);
            itemArrayList.add(new Item(korean, pronuncation, vietnamese, sound));
        } while (cursor.moveToNext());
        cursor.close();
        database.close();
        return itemArrayList;
    }

}

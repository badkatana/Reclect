package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import Model.Conspect;
import Utils.Util;

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(Context context) {
        super(context,
                Util.DATABASE_NAME,
                null,
                Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createConspect = "CREATE TABLE " + Util.TABLE_NAME + " ("
                + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.CONSPECT_NAME + " TEXT)";

        sqLiteDatabase.execSQL(createConspect);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void AddConspect(Conspect conspect) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.CONSPECT_NAME, conspect.getConspectName());
        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    public Conspect getConspect(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.KEY_ID, Util.CONSPECT_NAME},
                Util.CONSPECT_NAME + "=?", new String[] {name}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return new Conspect(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        }
        return null;
    }

    public ArrayList<Conspect> getAllConspects() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Conspect> conspects = new ArrayList<>();
        String query = "Select * from " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                Conspect conspect = new Conspect(id, name);
                conspects.add(conspect);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return conspects;
    }
}

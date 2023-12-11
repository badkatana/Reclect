package Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}

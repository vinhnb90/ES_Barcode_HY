package esolutions.com.barcodehungyenpc.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class SqlHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "BARCODE_HUNGYEN_PC";
    private static final int DB_VER = 5;

    public SqlHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SqlQuery.getCreateTblCongTo());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        if (newVer > oldVer) {
            sqLiteDatabase.execSQL(SqlQuery.getDropTblCongTo());
            onCreate(sqLiteDatabase);
        }
    }
}

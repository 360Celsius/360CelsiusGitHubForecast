package forcast.celsius.com.forcast.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import forcast.celsius.com.forcast.jsonobject.IpInfoObject;

/**
 * Created by dennisshar on 13/01/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ForcastWeatherData.db";
    private static DatabaseHelper sInstance;
    private Context context;

    public static synchronized DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DataBaseHelperContract.SQL_CREATE_ENTRIES_EXTERNAL_IP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL(DataBaseHelperContract.SQL_CREATE_ENTRIES_EXTERNAL_IP);
        }
    }

    //======================================================   External IP data   ===========================================================

    public void bulkExternalIPdata(IpInfoObject ipInfoObject){
        deleteExternalIPdataTable();
        ContentValues[] ipInfoObjectArr = new ContentValues[1];;
        for(int i=0;i<ipInfoObjectArr.length;i++) {
            ContentValues values = new ContentValues();
            values.put(DataBaseHelperContract.ExternalIP.DATABASE_TABLE_EXTERNAL_IP_COLUMN_ID_KEY, ipInfoObject.getIp());
            values.put(DataBaseHelperContract.ExternalIP.DATABASE_TABLE_EXTERNAL_CITY_COLUMN_ID_KEY, ipInfoObject.getCity());
            values.put(DataBaseHelperContract.ExternalIP.DATABASE_TABLE_EXTERNAL_REGION_COLUMN_ID_KEY, ipInfoObject.getRegion());
            values.put(DataBaseHelperContract.ExternalIP.DATABASE_TABLE_EXTERNAL_COUNTRY_COLUMN_ID_KEY, ipInfoObject.getCountry());
            values.put(DataBaseHelperContract.ExternalIP.DATABASE_TABLE_EXTERNAL_LOC_COLUMN_ID_KEY, ipInfoObject.getLoc());
            values.put(DataBaseHelperContract.ExternalIP.DATABASE_TABLE_EXTERNAL_ORG_COLUMN_ID_KEY, ipInfoObject.getOrg());
            ipInfoObjectArr[i] = values;
        }
        context.getContentResolver().bulkInsert(DataBaseHelperContract.ExternalIP.CONTENT_URI, ipInfoObjectArr);
    }

    private void deleteExternalIPdataTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(DataBaseHelperContract.SQL_DELETE_ENTRIES_EXTERNAL_IP, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public IpInfoObject getExternalIPdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(DataBaseHelperContract.SQL_SELECT_ENTRIES_EXTERNAL_IP, null);

        IpInfoObject ipInfoObject = new IpInfoObject();
        try {
            if (cursor.moveToFirst()) {
                cursor.getString(cursor.getColumnIndex(DataBaseHelperContract.ExternalIP.DATABASE_TABLE_EXTERNAL_IP_COLUMN_ID_KEY));
            }
        }catch (Exception e){
            e.printStackTrace();;
        }finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return null;
    }
}

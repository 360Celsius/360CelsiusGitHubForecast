package forcast.celsius.com.forcast.dbhelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by dennisshar on 20/01/2018.
 */

public class ForcastProvider extends ContentProvider {

    public static DatabaseHelper helper = null;

    private static final int IP_INFO_OBJECT = 1;

    private static final UriMatcher mMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mMatcher.addURI(DataBaseHelperContract.AUTHORITY, DataBaseHelperContract.ExternalIP.URI_SUFFIX, IP_INFO_OBJECT);
    }

    @Override
    public boolean onCreate() {
        helper  = DatabaseHelper.getInstance(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String nullColumnHack = null;
        long id = db.insert(DataBaseHelperContract.ExternalIP.DATABASE_TABLE_EXTERNAL_IP_TABLE_NAME_KEY, nullColumnHack, contentValues);

        if(id > -1){
            Uri insertedId = ContentUris.withAppendedId(DataBaseHelperContract.ExternalIP.CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(insertedId, null);
            return insertedId;
        }
        else
            return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}

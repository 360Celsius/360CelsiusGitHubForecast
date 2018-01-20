package forcast.celsius.com.forcast.dbhelper;

import android.net.Uri;
import android.provider.BaseColumns;

import java.util.Locale;

/**
 * Created by dennisshar on 13/01/2018.
 */

public class DataBaseHelperContract {

    public static final String packageName = "forcast.celsius.com.forcast";
    public static final String AUTHORITY = packageName + ".provider";
    public static final String CONTENT_BASE = "content://"+AUTHORITY+"/%s";


    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DataBaseHelperContract() {
    }

    /////////////////////////////////////////////////// External IP /////////////////////////////////////////////
    public static class ExternalIP implements BaseColumns {

        public static final String URI_SUFFIX = "external_ip";
        public static final Uri CONTENT_URI = Uri.parse(String.format(Locale.US,CONTENT_BASE, URI_SUFFIX));

        public static final String DATABASE_TABLE_EXTERNAL_IP_TABLE_NAME_KEY = "external_ip";

        public static final String DATABASE_TABLE_EXTERNAL_IP_COLUMN_ID_KEY = "ip";
        public static final String DATABASE_TABLE_EXTERNAL_CITY_COLUMN_ID_KEY = "city";
        public static final String DATABASE_TABLE_EXTERNAL_REGION_COLUMN_ID_KEY = "region";
        public static final String DATABASE_TABLE_EXTERNAL_COUNTRY_COLUMN_ID_KEY = "country";
        public static final String DATABASE_TABLE_EXTERNAL_LOC_COLUMN_ID_KEY = "loc";
        public static final String DATABASE_TABLE_EXTERNAL_ORG_COLUMN_ID_KEY = "org";
    }


    public static final String SQL_CREATE_ENTRIES_EXTERNAL_IP =
            "CREATE TABLE " + ExternalIP.DATABASE_TABLE_EXTERNAL_IP_TABLE_NAME_KEY + " (" +
                    ExternalIP._ID + " INTEGER PRIMARY KEY," +
                    ExternalIP.DATABASE_TABLE_EXTERNAL_IP_COLUMN_ID_KEY + " TEXT," +
                    ExternalIP.DATABASE_TABLE_EXTERNAL_CITY_COLUMN_ID_KEY + " TEXT," +
                    ExternalIP.DATABASE_TABLE_EXTERNAL_REGION_COLUMN_ID_KEY + " TEXT," +
                    ExternalIP.DATABASE_TABLE_EXTERNAL_COUNTRY_COLUMN_ID_KEY + " TEXT," +
                    ExternalIP.DATABASE_TABLE_EXTERNAL_LOC_COLUMN_ID_KEY + " TEXT," +
                    ExternalIP.DATABASE_TABLE_EXTERNAL_ORG_COLUMN_ID_KEY + " TEXT)";

    public static final String SQL_DELETE_ENTRIES_EXTERNAL_IP =
            "DROP TABLE IF EXISTS " + ExternalIP.DATABASE_TABLE_EXTERNAL_IP_TABLE_NAME_KEY;

}

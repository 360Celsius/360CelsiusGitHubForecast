package forcast.celsius.com.forcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import forcast.celsius.com.forcast.dbhelper.DatabaseHelper;
import forcast.celsius.com.forcast.services.ForcastPullService;
import forcast.celsius.com.forcast.services.PullServiceUtils;

/**
 * Created by dennisshar on 13/01/2018.
 */

public class BaseActivity extends AppCompatActivity {

    public static DatabaseHelper helper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = DatabaseHelper.getInstance(getApplicationContext());
    }
}

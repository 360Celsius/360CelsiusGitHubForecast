package forcast.celsius.com.forcast.recivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import forcast.celsius.com.forcast.BaseActivity;
import forcast.celsius.com.forcast.dbhelper.DatabaseHelper;
import forcast.celsius.com.forcast.services.PullServiceUtils;

/**
 * Created by dennisshar on 13/01/2018.
 */

public class ResponseReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        try{
            switch (intent.getStringExtra(PullServiceUtils.GET_WEATHER_ACTION_KEY)) {
                case PullServiceUtils.GET_EXTERNAL_IP_DATA_FINISHED:
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
            //TODO load error screen
        }
    }

}

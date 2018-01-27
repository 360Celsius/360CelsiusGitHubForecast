package forcast.celsius.com.forcast.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import forcast.celsius.com.forcast.BaseActivity;
import forcast.celsius.com.forcast.dbhelper.DatabaseHelper;
import forcast.celsius.com.forcast.jsonoparser.JSONparser;
import forcast.celsius.com.forcast.network.NetworkHTTPRequests;

/**
 * Created by dennisshar on 13/01/2018.
 */

public class ForcastPullService extends IntentService {


    private String responce = null;
    private static NetworkHTTPRequests networkHTTPRequests = null;
    private static DatabaseHelper helper = null;
    private static JSONparser jsonParser = null;

    @Override
    public void onCreate() {
        super.onCreate();
        networkHTTPRequests = NetworkHTTPRequests.getInstance();
        helper = BaseActivity.helper;
        jsonParser = JSONparser.getInstance();
    }

    public ForcastPullService(String name) {
        super(name);
    }

    public ForcastPullService() {
        super("ForcastPullService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            switch (intent.getStringExtra(PullServiceUtils.GET_WEATHER_ACTION_KEY)) {
                case PullServiceUtils.GET_EXTERNAL_IP_DATA:

                    responce = networkHTTPRequests.getExternalIP();
                    helper.bulkExternalIPdata(jsonParser.getIpInfoObject(responce));
                    helper.getExternalIPdata();


                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction(PullServiceUtils.GET_WEATHER_DATA);
                    broadcastIntent.putExtra(PullServiceUtils.GET_WEATHER_ACTION_KEY, PullServiceUtils.GET_EXTERNAL_IP_DATA_FINISHED);
                    sendBroadcast(broadcastIntent);

                    Log.e("ServiceTest","PullServiceUtils.GET_EXTERNAL_IP_DATA - >"+responce);

                    break;

                case PullServiceUtils.GET_LOCATION_BY_EXTERNAL_IP_DATA:
                    responce = networkHTTPRequests.getLocationByExternalIP("72.229.28.185");
                    jsonParser.getGeoLocationObject(responce);
                    Log.e("ServiceTest","PullServiceUtils.GET_LOCATION_BY_EXTERNAL_IP_DATA - >"+responce);

                    break;

                case PullServiceUtils.GET_CURRENT_WEATHER_DATA:
                    responce = networkHTTPRequests.currentWeatherData("Rehovot","metric");
                    Log.e("ServiceTest","PullServiceUtils.GET_CURRENT_WEATHER_DATA - >"+responce);

                    break;

                case PullServiceUtils.GET_FIVE_DAYS_WEATHER_DATA:
                    responce = networkHTTPRequests.fiveDayWeatherForecast("Rehovot","metric");
                    Log.e("ServiceTest","PullServiceUtils.GET_FIVE_DAYS_WEATHER_DATA - >"+responce);

                    break;

                case PullServiceUtils.GET_TEN_DAYS_WEATHER_DATA:
                    responce = networkHTTPRequests.tenDayWeatherForecast("Rehovot","metric");
                    Log.e("ServiceTest","PullServiceUtils.GET_TEN_DAYS_WEATHER_DATA - >"+responce);

                    break;
                case PullServiceUtils.GET_SIXTEEN_DAYS_WEATHER_DATA:
                    responce = networkHTTPRequests.sixTeenDayWeatherForecast("Rehovot","metric");
                    Log.e("ServiceTest","PullServiceUtils.GET_SIXTEEN_DAYS_WEATHER_DATA - >"+responce);

                    break;
                default:
                    //TODO error
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
            //TODO load error screen
        }
    }
}

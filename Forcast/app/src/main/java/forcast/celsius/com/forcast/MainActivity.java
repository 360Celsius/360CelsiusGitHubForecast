package forcast.celsius.com.forcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import forcast.celsius.com.forcast.services.ForcastPullService;
import forcast.celsius.com.forcast.services.PullServiceUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent msgIntentGetExternalIP = new Intent(getApplicationContext(), ForcastPullService.class);

        msgIntentGetExternalIP.putExtra(PullServiceUtils.GET_WEATHER_ACTION_KEY, PullServiceUtils.GET_EXTERNAL_IP_DATA);
        startService(msgIntentGetExternalIP);

        msgIntentGetExternalIP.putExtra(PullServiceUtils.GET_WEATHER_ACTION_KEY, PullServiceUtils.GET_LOCATION_BY_EXTERNAL_IP_DATA);
        startService(msgIntentGetExternalIP);

        msgIntentGetExternalIP.putExtra(PullServiceUtils.GET_WEATHER_ACTION_KEY, PullServiceUtils.GET_CURRENT_WEATHER_DATA);
        startService(msgIntentGetExternalIP);

        msgIntentGetExternalIP.putExtra(PullServiceUtils.GET_WEATHER_ACTION_KEY, PullServiceUtils.GET_FIVE_DAYS_WEATHER_DATA);
        startService(msgIntentGetExternalIP);

        msgIntentGetExternalIP.putExtra(PullServiceUtils.GET_WEATHER_ACTION_KEY, PullServiceUtils.GET_TEN_DAYS_WEATHER_DATA);
        startService(msgIntentGetExternalIP);

        msgIntentGetExternalIP.putExtra(PullServiceUtils.GET_WEATHER_ACTION_KEY, PullServiceUtils.GET_SIXTEEN_DAYS_WEATHER_DATA);
        startService(msgIntentGetExternalIP);
    }
}

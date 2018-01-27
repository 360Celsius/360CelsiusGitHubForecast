package forcast.celsius.com.forcast;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import forcast.celsius.com.datamodel.SideMenuItemDataModel;
import forcast.celsius.com.forcast.listadapter.SideMenuListCustomAdapter;
import forcast.celsius.com.forcast.services.ForcastPullService;
import forcast.celsius.com.forcast.services.PullServiceUtils;

public class MainActivity extends BaseActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private RelativeLayout mainView;
    private Toolbar toolbar;
    private ArrayList<SideMenuItemDataModel> dataModels;
    private ListView listView;
    private View sideMenuShadow;
    private static SideMenuListCustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mainView = (RelativeLayout) findViewById(R.id.main_view);
        sideMenuShadow = (View) findViewById(R.id.shadow);
        mainView = (RelativeLayout) findViewById(R.id.main_view);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name){

            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mainView.setTranslationX(slideOffset * drawerView.getWidth());
                toolbar.setTranslationX(slideOffset * drawerView.getWidth());
                drawerLayout.bringChildToFront(drawerView);
                drawerLayout.requestLayout();
                drawerLayout.setScrimColor(Color.TRANSPARENT);
            }
        };



        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        //toolbar.setLogo(R.drawable.logo);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        listView=(ListView)findViewById(R.id.side_menu_list);

        dataModels= new ArrayList<>();
        //dataModels.add(new SideMenuItemDataModel(getResources().getString(R.string.side_menu_financials), R.drawable.financials,getResources().getColor(R.color.sliding_menu_financials_bg),1));


        adapter= new SideMenuListCustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SideMenuItemDataModel dataModel= dataModels.get(position);
                //setSideMenuSeperatorColor(dataModel);
                //sendServiceRequestBySelectedCategory(dataModel);
                //setMainViewColor(dataModel);

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO remove --- for tests only
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

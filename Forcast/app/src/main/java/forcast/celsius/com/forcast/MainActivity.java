package forcast.celsius.com.forcast;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import java.util.ArrayList;

import forcast.celsius.com.datamodel.SideMenuItemDataModel;
import forcast.celsius.com.forcast.listadapter.SideMenuListCustomAdapter;
import forcast.celsius.com.forcast.services.ForcastPullService;
import forcast.celsius.com.forcast.services.PullServiceUtils;
import forcast.celsius.com.forcast.viewhelpers.BottomNavigationViewHelper;

public class MainActivity extends BaseActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private RelativeLayout mainView;
    private Toolbar toolbar;
    private ArrayList<SideMenuItemDataModel> dataModels;
    private ListView listView;
    private View sideMenuShadow;
    private SideMenuListCustomAdapter adapter;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mainView = (RelativeLayout) findViewById(R.id.main_view);
        sideMenuShadow = (View) findViewById(R.id.shadow);
        mainView = (RelativeLayout) findViewById(R.id.main_view);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

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
        dataModels.add(new SideMenuItemDataModel(getResources().getString(R.string.today), R.drawable.ic_drawer,getResources().getColor(R.color.colorPrimary),1));
        dataModels.add(new SideMenuItemDataModel(getResources().getString(R.string.tomorrow), R.drawable.ic_drawer,getResources().getColor(R.color.colorPrimary),1));
        dataModels.add(new SideMenuItemDataModel(getResources().getString(R.string.five_days), R.drawable.ic_drawer,getResources().getColor(R.color.colorPrimary),1));
        dataModels.add(new SideMenuItemDataModel(getResources().getString(R.string.ten_days), R.drawable.ic_drawer,getResources().getColor(R.color.colorPrimary),1));
        dataModels.add(new SideMenuItemDataModel(getResources().getString(R.string.sixteen_days), R.drawable.ic_drawer,getResources().getColor(R.color.colorPrimary),1));
        dataModels.add(new SideMenuItemDataModel(getResources().getString(R.string.settings), R.drawable.ic_drawer,getResources().getColor(R.color.colorPrimary),1));
        dataModels.add(new SideMenuItemDataModel(getResources().getString(R.string.credits), R.drawable.ic_drawer,getResources().getColor(R.color.colorPrimary),1));


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
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                //selectedFragment = ItemOneFragment.newInstance();
                                break;
                            case R.id.action_item2:
                                //selectedFragment = ItemTwoFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                //selectedFragment = ItemThreeFragment.newInstance();
                                break;
                            case R.id.action_item4:
                                //selectedFragment = ItemTwoFragment.newInstance();
                                break;
                            case R.id.action_item5:
                                //selectedFragment = ItemThreeFragment.newInstance();
                                break;
                        }
//                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.list_view_placeholder, selectedFragment);
//                        transaction.commit();
                        return true;
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

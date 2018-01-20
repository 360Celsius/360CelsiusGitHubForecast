package forcast.celsius.com.forcast.network;

import android.util.Log;

/**
 * Created by dennisshar on 13/01/2018.
 */

public class NetworkHTTPRequests {

    private static NetworkHTTPConnection networkHTTPConnection = null;

    private static NetworkHTTPRequests instance = null;

    protected NetworkHTTPRequests() {
        networkHTTPConnection = NetworkHTTPConnection.getInstance();
    }
    public static NetworkHTTPRequests getInstance() {
        if(instance == null) {
            instance = new NetworkHTTPRequests();
        }
        return instance;
    }


    private static String urlBuilder (String serverDomain,String apiAction, String cityName,String apiKey,String tempUnits){
        return serverDomain+apiAction+"?"+"q="+cityName+"&APPID="+apiKey+"&units="+tempUnits;
    }

    private static String urlBuilder (String serverDomain,String apiAction, String cityName,String days,String apiKey,String tempUnits){
        return serverDomain+apiAction+"?"+"q="+cityName+"&cnt="+days+"&APPID="+apiKey+"&units="+tempUnits;
    }

    public static String currentWeatherData(String cityName,String tempUnits){
        Log.e("ServiceTest","NetworkHTTPRequests currentWeatherData");
        String url =  urlBuilder(NetworkHTTPUtills.SERVER_DOMAIN,NetworkHTTPUtills.ACTION_WEATHER,cityName,NetworkHTTPUtills.API_KEY,tempUnits);
        Log.e("ServiceTest","NetworkHTTPRequests currentWeatherData - >"+url);
        return networkHTTPConnection.getHttp(url);
    }

    public static String fiveDayWeatherForecast(String cityName,String tempUnits){
        Log.e("ServiceTest","NetworkHTTPRequests fiveDayWeatherForecast");
        String url =  urlBuilder(NetworkHTTPUtills.SERVER_DOMAIN,NetworkHTTPUtills.ACTION_5_DAY_FORCAST,cityName,NetworkHTTPUtills.API_KEY,tempUnits);
        Log.e("ServiceTest","NetworkHTTPRequests fiveDayWeatherForecast - >"+url);
        return networkHTTPConnection.getHttp(url);
    }

    public static String tenDayWeatherForecast(String cityName,String tempUnits){
        Log.e("ServiceTest","NetworkHTTPRequests tenDayWeatherForecast");
        String url =  urlBuilder(NetworkHTTPUtills.SERVER_DOMAIN,NetworkHTTPUtills.ACTION_16_DAY_FORCAST,cityName,"10",NetworkHTTPUtills.API_KEY,tempUnits);
        Log.e("ServiceTest","NetworkHTTPRequests tenDayWeatherForecast - >"+url);
        return networkHTTPConnection.getHttp(url);
    }


    public static String sixTeenDayWeatherForecast(String cityName,String tempUnits){
        Log.e("ServiceTest","NetworkHTTPRequests sixTeenDayWeatherForecast");
        String url =  urlBuilder(NetworkHTTPUtills.SERVER_DOMAIN,NetworkHTTPUtills.ACTION_16_DAY_FORCAST,cityName,"16",NetworkHTTPUtills.API_KEY,tempUnits);
        Log.e("ServiceTest","NetworkHTTPRequests sixTeenDayWeatherForecast - >"+url);
        return networkHTTPConnection.getHttp(url);
    }


    public static String getExternalIP(){
        Log.e("ServiceTest","NetworkHTTPRequests getExternalIP");
        String url =  NetworkHTTPUtills.EXTERNAL_IP_SERVER_DOMAIN;
        Log.e("ServiceTest","NetworkHTTPRequests getExternalIP - >"+url);
        return networkHTTPConnection.getHttp(url);
    }

    public static String getLocationByExternalIP(String externalIP){
        Log.e("ServiceTest","NetworkHTTPRequests getLocationByExternalIP");
        String url =  NetworkHTTPUtills.LOCATION_BY_EXTERNAL_IP_SERVER_DOMAIN+externalIP;
        Log.e("ServiceTest","NetworkHTTPRequests getLocationByExternalIP - >"+url);
        return networkHTTPConnection.getHttp(url);
    }
}

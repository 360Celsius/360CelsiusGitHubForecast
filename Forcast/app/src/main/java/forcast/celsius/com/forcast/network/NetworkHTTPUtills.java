package forcast.celsius.com.forcast.network;

/**
 * Created by dennisshar on 13/01/2018.
 */

public class NetworkHTTPUtills {
    public static final int TIMEOUT_SOCKET = 160000;
    public static final int TIMEOUT_CONNECTION = 100000;
    public static final String REQUEST_METHOD = "GET";
    public static final String REQUEST_PROPERTY_USER_AGENT_KEY = "User-Agent";
    public static final String REQUEST_PROPERTY_USER_AGENT_VALUE = "Android";
    public static final String REQUEST_PROPERTY_ACCEPT_KEY = "accept";
    public static final String REQUEST_PROPERTY_ACCEPT_VALUE = "application/json";
    public static final String SYSTEM_PROPERTY_KEEPALIVE_KEY = "http.keepAlive";
    public static final String REQUEST_PROPERTY_KEEPALIVE_VALUE = "false";


    public static final String API_KEY = "aa2df23d347d91a01f286584e35f2b7e";
    public static final String SERVER_DOMAIN = "http://api.openweathermap.org/data/2.5/";
    public static final String EXTERNAL_IP_SERVER_DOMAIN = "https://ipinfo.io/json";
    public static final String LOCATION_BY_EXTERNAL_IP_SERVER_DOMAIN = "http://ip-api.com/json/";
    public static final String ACTION_WEATHER = "weather";
    public static final String ACTION_5_DAY_FORCAST = "forecast";
    public static final String ACTION_16_DAY_FORCAST = "forecast/daily";

}

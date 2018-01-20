package forcast.celsius.com.forcast.jsonoparser;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import forcast.celsius.com.forcast.dbhelper.DatabaseHelper;
import forcast.celsius.com.forcast.jsonnode.JSONnode;
import forcast.celsius.com.forcast.jsonobject.GeoLocationObject;
import forcast.celsius.com.forcast.jsonobject.IpInfoObject;

/**
 * Created by dennisshar on 20/01/2018.
 */

public class JSONparser {

    private static JSONparser sIntstance;

    public JSONparser() {
        super();
    }

    public static synchronized JSONparser getInstance() {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sIntstance == null) {
            sIntstance = new JSONparser();
        }
        return sIntstance;
    }

    public IpInfoObject getIpInfoObject (String requestResponce){
        JSONObject reader = null;
        IpInfoObject ipInfoObject = null;
        try {
            reader = new JSONObject(requestResponce);
            ipInfoObject = new IpInfoObject();

            ipInfoObject.setIp( reader.has(JSONnode.IP_KEY)&&!reader.isNull(JSONnode.IP_KEY) ?  reader.getString(JSONnode.IP_KEY):""   );
            ipInfoObject.setCity( reader.has(JSONnode.CITY_KEY)&&!reader.isNull(JSONnode.CITY_KEY) ? reader.getString(JSONnode.CITY_KEY):"" );
            ipInfoObject.setRegion( reader.has(JSONnode.REGION_KEY)&&!reader.isNull(JSONnode.REGION_KEY) ? reader.getString(JSONnode.REGION_KEY):"");
            ipInfoObject.setCountry( reader.has(JSONnode.COUNTRY_KEY)&&!reader.isNull(JSONnode.COUNTRY_KEY) ? reader.getString(JSONnode.COUNTRY_KEY):"");
            ipInfoObject.setLoc( reader.has(JSONnode.LOC_KEY)&&!reader.isNull(JSONnode.LOC_KEY) ? reader.getString(JSONnode.LOC_KEY):"");
            ipInfoObject.setOrg( reader.has(JSONnode.ORG_KEY)&&!reader.isNull(JSONnode.ORG_KEY) ? reader.getString(JSONnode.ORG_KEY):"");

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return ipInfoObject;
    }

    public GeoLocationObject getGeoLocationObject(String requestResponce){
        JSONObject reader = null;
        GeoLocationObject geoLocationObject = null;

        try {
            reader = new JSONObject(requestResponce);
            geoLocationObject = new GeoLocationObject();

            geoLocationObject.setAs( reader.has(JSONnode.AS_KEY)&&!reader.isNull(JSONnode.AS_KEY) ?  reader.getString(JSONnode.AS_KEY):""   );
            geoLocationObject.setCity( reader.has(JSONnode.CITY_KEY)&&!reader.isNull(JSONnode.CITY_KEY) ?  reader.getString(JSONnode.CITY_KEY):""   );
            geoLocationObject.setCountry( reader.has(JSONnode.COUNTRY_KEY)&&!reader.isNull(JSONnode.COUNTRY_KEY) ?  reader.getString(JSONnode.COUNTRY_KEY):""   );
            geoLocationObject.setCountryCode( reader.has(JSONnode.COUNTRY_CODE_KEY)&&!reader.isNull(JSONnode.COUNTRY_CODE_KEY) ?  reader.getString(JSONnode.COUNTRY_CODE_KEY):""   );
            geoLocationObject.setIsp( reader.has(JSONnode.ISP_KEY)&&!reader.isNull(JSONnode.ISP_KEY) ?  reader.getString(JSONnode.ISP_KEY):""   );
            geoLocationObject.setLat( reader.has(JSONnode.LAT_KEY)&&!reader.isNull(JSONnode.LAT_KEY) ?  reader.getLong(JSONnode.LAT_KEY):0   );
            geoLocationObject.setLon( reader.has(JSONnode.LON_KEY)&&!reader.isNull(JSONnode.LON_KEY) ?  reader.getLong(JSONnode.LON_KEY):0   );
            geoLocationObject.setOrg( reader.has(JSONnode.ORG_KEY)&&!reader.isNull(JSONnode.ORG_KEY) ?  reader.getString(JSONnode.ORG_KEY):""   );
            geoLocationObject.setQuery( reader.has(JSONnode.QUERY_KEY)&&!reader.isNull(JSONnode.QUERY_KEY) ?  reader.getString(JSONnode.QUERY_KEY):""   );
            geoLocationObject.setRegion( reader.has(JSONnode.REGION_KEY)&&!reader.isNull(JSONnode.REGION_KEY) ?  reader.getString(JSONnode.REGION_KEY):""   );
            geoLocationObject.setRegionName( reader.has(JSONnode.REGION_NAME_KEY)&&!reader.isNull(JSONnode.REGION_NAME_KEY) ?  reader.getString(JSONnode.REGION_NAME_KEY):""   );
            geoLocationObject.setStatus( reader.has(JSONnode.STATUS_KEY)&&!reader.isNull(JSONnode.STATUS_KEY) ?  reader.getString(JSONnode.STATUS_KEY):""   );
            geoLocationObject.setTimezone( reader.has(JSONnode.TIMEZONE_KEY)&&!reader.isNull(JSONnode.TIMEZONE_KEY) ?  reader.getString(JSONnode.TIMEZONE_KEY):""   );
            geoLocationObject.setZip( reader.has(JSONnode.ZIP_KEY)&&!reader.isNull(JSONnode.ZIP_KEY) ?  reader.getString(JSONnode.ZIP_KEY):""   );


        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return geoLocationObject;
    }
}

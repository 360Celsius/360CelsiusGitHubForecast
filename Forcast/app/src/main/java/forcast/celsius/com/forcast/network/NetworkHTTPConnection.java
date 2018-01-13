package forcast.celsius.com.forcast.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by dennisshar on 13/01/2018.
 */

public class NetworkHTTPConnection {

    private static NetworkHTTPConnection instance = null;

    protected NetworkHTTPConnection() {
        // Exists only to defeat instantiation.
    }
    public static NetworkHTTPConnection getInstance() {
        if(instance == null) {
            instance = new NetworkHTTPConnection();
        }
        return instance;
    }

    public static String getHttp(String apiAction){

        try {
            URL obj = null;
            obj = new URL(apiAction);
            HttpURLConnection HttpConnection = (HttpURLConnection) obj.openConnection();
            HttpConnection.setReadTimeout(NetworkHTTPUtills.TIMEOUT_SOCKET);
            HttpConnection.setConnectTimeout(NetworkHTTPUtills.TIMEOUT_CONNECTION);
            HttpConnection.setRequestMethod(NetworkHTTPUtills.REQUEST_METHOD);
            HttpConnection.setRequestProperty(NetworkHTTPUtills.REQUEST_PROPERTY_USER_AGENT_KEY, NetworkHTTPUtills.REQUEST_PROPERTY_USER_AGENT_VALUE);
            HttpConnection.setRequestProperty(NetworkHTTPUtills.REQUEST_PROPERTY_ACCEPT_KEY, NetworkHTTPUtills.REQUEST_PROPERTY_ACCEPT_VALUE);
            System.setProperty(NetworkHTTPUtills.SYSTEM_PROPERTY_KEEPALIVE_KEY, NetworkHTTPUtills.REQUEST_PROPERTY_KEEPALIVE_VALUE);

            int responseCode = HttpConnection.getResponseCode();
            //Log.e("ServiceTest","NetworkHTTPConnection getHttp responseCode"+responseCode);

            if(responseCode==HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(HttpConnection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                String s = new String(response.toString());


                //Log.e("ServiceTest","NetworkHTTPConnection getHttp "+s);
                return s;
            }else{
                //Log.e("ServiceTest","NetworkHTTPConnection getHttp "+"NULL");
                return null;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            //Log.e("ServiceTest","NetworkHTTPConnection getHttp "+"MalformedURLException");
            return null;
        } catch (ProtocolException e) {
            e.printStackTrace();
            //Log.e("ServiceTest","NetworkHTTPConnection getHttp "+"ProtocolException");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            //Log.e("ServiceTest","NetworkHTTPConnection getHttp "+"IOException");
            return null;
        }

    }
}

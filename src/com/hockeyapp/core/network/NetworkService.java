package com.hockeyapp.core.network;

import com.google.gson.Gson;
import com.hockeyapp.core.network.models.crashreasons.CrashGroups;
import com.hockeyapp.core.network.models.listapps.ListApps;
import com.hockeyapp.plugin.preferences.PreferenceService;
import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by tsaravana on 6/19/2015.
 */
public class NetworkService {

    public static final int RESPONSE_CODE_OK = 200;

    public static Gson gson;

    @Nullable
    public static ListApps getListApps() {
        final String response = getResponse(Urls.getListApps());
        if (response != null) {
            return getGson().fromJson(response, ListApps.class);
        }
        return null;
    }

    @Nullable
    public static CrashGroups getCrashReasons(String appId, Map<String,String> params){
        final String response = getResponse(Urls.getCrashReasons(appId), params);
        if (response != null) {
            return getGson().fromJson(response, CrashGroups.class);
        }
        return null;
    }

    @NotNull
    private static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    @Nullable
    public static String getResponse(String url){
        return getResponse(url, null);
    }

    @Nullable
    public static String getResponse(@NotNull String url, @Nullable Map<String,String> params) {
        final HttpsURLConnection urlConnection;
        BufferedReader in = null;
        try {
            if(params != null && !params.isEmpty()){
                StringBuilder builder = new StringBuilder(url);
                builder.append("?");
                for (String key : params.keySet()) {
                    builder.append(key).append("=").append(params.get(key)).append("&");
                }
                builder.deleteCharAt(builder.length()-1);
                url = builder.toString();
            }

            URL requestURL = new URL(url);
            urlConnection = (HttpsURLConnection) requestURL.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("X-HockeyAppToken", getApiToken());


            final int responseCode = urlConnection.getResponseCode();

            System.out.println("Request URL : "+ urlConnection.getURL());
            System.out.println("Response code : " + responseCode);
            if (responseCode != RESPONSE_CODE_OK) {
                return null;
            }

            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine;
            StringBuilder builder = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                builder.append(inputLine);
            }

            System.out.println("Response : " + builder.toString());
            return builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String getApiToken() {
        final PreferenceService service = ServiceManager.getService(PreferenceService.class);
        //return service.getState();
        return "54ab859f56eb4836ad6f4b6663e859cd";
    }
}

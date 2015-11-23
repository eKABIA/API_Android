package com.example.ekabia.meteokabiaeouard.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.ekabia.meteokabiaeouard.data.Chaine;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by cepc on 22/11/2015.
 */
public class YahouWeatherService
{
    private WeatherServiceCallback callback ;
    private String location ;
    private Exception error;

    public YahouWeatherService(WeatherServiceCallback callback)
    {
        this.callback = callback ;
    }
    public void refreshWeather( String l)
    {
        this.location= l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params)
            {
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'",params[0]);
                String endPoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
                try
                {
                    URL url = new URL(endPoint);
                    URLConnection connexion = url.openConnection();
                    InputStream inputStream = connexion.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine())!= null)
                    {
                        result.append(line);
                    }
                    return result.toString();
                } catch (MalformedURLException e) {
                    error = e ;
                } catch (IOException e)
                {
                    error = e ;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s)
            {
                if(s== null && error != null)
                {
                    callback.serviceFailure(error);
                    return;
                }
                try
                {
                    JSONObject data = new JSONObject(s);
                    JSONObject queryResult = data.optJSONObject("query");
                    int count = queryResult.optInt("count");
                    if(count == 0)
                    {
                        callback.serviceFailure(new LocationWeatherException("Pas d'information météo trouvé pour "+location));
                        return;
                    }
                    Chaine chaine = new Chaine();
                    chaine.populate(queryResult.optJSONObject("results").optJSONObject("channel"));
                    callback.serviceSucces(chaine);
                }
                catch (JSONException e)
                {
                    callback.serviceFailure(e);
                }
            }
        }.execute(location);
    }
    public String getLocation()
    {
        return location;
    }
    public class LocationWeatherException extends Exception
    {
        public LocationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }
}

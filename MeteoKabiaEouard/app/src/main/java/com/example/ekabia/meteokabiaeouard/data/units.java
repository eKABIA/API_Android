package com.example.ekabia.meteokabiaeouard.data;

import org.json.JSONObject;

/**
 * Created by cepc on 22/11/2015.
 */
public class units implements Json
{
    private String temprature ;

    public String getTemprature()
    {
        return temprature;
    }

    @Override
    public void populate(JSONObject data)
    {
        temprature = data.optString("temperature");

    }
}

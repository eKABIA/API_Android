package com.example.ekabia.meteokabiaeouard.data;

import org.json.JSONObject;

/**
 * Created by cepc on 22/11/2015.
 */
public class Condition implements Json
{
    private int code;
    private int temperature;
    private String description;

    // accessseur


    public int getCode()
    {
        return code;
    }

    public int getTemperature()
    {
        return temperature;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public void populate(JSONObject data)
    {
        code = data.optInt("code");
        temperature = data.optInt("temp");
        description = data.optString("text");
    }
}

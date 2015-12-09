package com.example.ekabia.meteokabiaeouard.data;

import android.content.ClipData;

import org.json.JSONObject;


/**
 * Created by cepc on 22/11/2015.
 */
public class Chaine implements Json
{
    private item iteme ;
    private units unit ;

    public item getItem()
    {
        return iteme;
    }

    public units getUnit() {
        return unit;
    }

    @Override
    public void populate(JSONObject data)
    {
        unit = new units();
        unit.populate(data.optJSONObject("units"));
        iteme = new item();
        iteme.populate(data.optJSONObject("item"));
    }
}

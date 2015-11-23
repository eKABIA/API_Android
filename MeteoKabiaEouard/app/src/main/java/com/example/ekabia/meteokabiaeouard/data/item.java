package com.example.ekabia.meteokabiaeouard.data;

import org.json.JSONObject;

/**
 * Created by cepc on 22/11/2015.
 */
public class item implements Json
{
    private Condition condition;

    public Condition getCondition()
    {
        return condition;
    }

    @Override
    public void populate(JSONObject data)
    {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }
}

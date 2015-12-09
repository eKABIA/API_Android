package com.example.ekabia.meteokabiaeouard.BDD;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by ke511541 on 01/12/2015.
 */
public class maBDD extends RealmObject
{
    private String villePays ;
    private String temperature;
    private String conditions;
    private int idImage;

    @Ignore
    private int        id;

    //==================   Accesseurs ==============================================================

    public String getVillePays()
    {
        return villePays;
    }

    public void setVillePays(String villePays)
    {
        this.villePays = villePays;
    }

    public String getTemperature()
    {
        return temperature;
    }

    public void setTemperature(String temperature)
    {
        this.temperature = temperature;
    }

    public String getConditions()
    {
        return conditions;
    }

    public void setConditions(String conditions)
    {
        this.conditions = conditions;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage)
    {
        this.idImage = idImage;
    }


    //==============================================================================================
}

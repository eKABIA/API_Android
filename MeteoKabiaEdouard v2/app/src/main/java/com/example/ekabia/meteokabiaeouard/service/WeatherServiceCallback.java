package com.example.ekabia.meteokabiaeouard.service;

import com.example.ekabia.meteokabiaeouard.data.Chaine;

/**
 * Created by cepc on 22/11/2015.
 */
public interface WeatherServiceCallback
{
    void serviceSucces(Chaine channel);
    void serviceFailure(Exception exception);
}

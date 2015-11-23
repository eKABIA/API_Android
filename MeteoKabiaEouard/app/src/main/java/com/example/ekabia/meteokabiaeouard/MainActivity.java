package com.example.ekabia.meteokabiaeouard;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ekabia.meteokabiaeouard.data.Chaine;
import com.example.ekabia.meteokabiaeouard.data.item;
import com.example.ekabia.meteokabiaeouard.service.WeatherServiceCallback;
import com.example.ekabia.meteokabiaeouard.service.YahouWeatherService;
import com.squareup.okhttp.Response;

import java.util.List;

import retrofit.Callback;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity implements WeatherServiceCallback
{
    // on declare les different elements present sur notre interface
    private ImageView imageMeteo ;
    private TextView temperature;
    private TextView condition;
    private TextView location ;
    private Button btnMeteo ;
    private EditText ville;
    private EditText pays;
    private YahouWeatherService service;
    // on cree une boite de dialogue pour le indiquer que le chargement est een cour
    private ProgressDialog dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // on les ralie a notre interface grace a la methode findviewbyid
        imageMeteo = (ImageView)findViewById(R.id.imageView_Icon_meteo);
        temperature = (TextView)findViewById(R.id.textView_temperature);
        condition = (TextView)findViewById(R.id.textView_Condition);
        location = (TextView)findViewById(R.id.textView_Location);
        btnMeteo = (Button)findViewById(R.id.button_Afficher_meteo);
        ville= (EditText)findViewById(R.id.editText_Ville);
        pays = (EditText)findViewById(R.id.editText_pays);




        // on declare nottre yahou service
        service = new YahouWeatherService(this);
        //on initialise note objet dialogue
        dialog = new ProgressDialog(this);
        dialog.setMessage("Chargement...");
        dialog.show();
        service.refreshWeather("sophia,france");
        // la on lui donne le nom de la ville et le pays
        btnMeteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final    String country = pays.getText().toString();
                final    String city = ville.getText().toString();
                service.refreshWeather(""+city+","+country+"");
            }
        });


    }

    @Override
    public void serviceSucces(Chaine channel)
    {
        // une fois le chargement terminé on cache cette boite de dialogue
        dialog.hide();
        item iteme = channel.getItem();
        int ressourceid = getResources().getIdentifier("drawable/icon_"+channel.getItem().getCondition().getCode(),null,getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(ressourceid);
        imageMeteo.setImageDrawable(weatherIconDrawable);
        location.setText(service.getLocation());
        temperature.setText(iteme.getCondition().getTemperature()+"\u00B0"+channel.getUnit().getTemprature());
        condition.setText(iteme.getCondition().getDescription());

    }

    @Override
    public void serviceFailure(Exception exception)
    {
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_SHORT).show();
    }
}

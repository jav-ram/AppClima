package com.example.javier.clima;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityResultados extends AppCompatActivity {

    NetworkUtils network;
    String city;
    String back;
    TextView txtTemperatura;
    TextView txtHumedad;
    TextView txtPresion;
    TextView txtCity;
    TextView txtMax;
    TextView txtMin;
    ProgressBar prgBar;
    JSONObject res;
    JSONObject main;

    String actualCity;
    double temp;
    double pressure;
    double humidity;
    double tempMax;
    double tempMin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados2);
        city = getIntent().getStringExtra("city");
        network = new NetworkUtils();
        city = getIntent().getExtras().getString("city");
        txtTemperatura = (TextView) findViewById(R.id.txtTemperatura);
        txtHumedad = (TextView) findViewById(R.id.txtHumedad);
        txtPresion = (TextView) findViewById(R.id.txtPresion);
        txtCity = (TextView) findViewById(R.id.txtCity);
        txtMax = (TextView) findViewById(R.id.txtMax);
        txtMin = (TextView) findViewById(R.id.txtMin);
        prgBar = (ProgressBar) findViewById(R.id.circularProgressbar);

        URL SearchUrl = network.buildUrl("Guatemala");
        new QueryTask().execute(SearchUrl);

    }

    public class QueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params) {

            String resultado = "";
            try {
                resultado = network.getResponseFromHttpUrl(params[0]);
            }catch (Exception e){
                e.printStackTrace();
            }

            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null && !s.equals("")){
                back = s;
                String name="";
                try {
                    res = new JSONObject(back);
                    main = res.getJSONObject("main");
                    temp = Double.parseDouble(main.getString("temp"));
                    pressure = Double.parseDouble(main.getString("pressure"));
                    pressure = (double)Math.round(pressure*0.0986923266716)/100;
                    humidity = Double.parseDouble(main.getString("humidity"));
                    tempMax = Double.parseDouble(main.getString("temp_max"));
                    tempMin = Double.parseDouble(main.getString("temp_min"));

                    name = res.getString("name");



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                txtCity.setText(name);
                txtTemperatura.setText((temp-273.15)+" C\n"+temp+" K");
                txtHumedad.setText(""+humidity);
                txtPresion.setText(""+pressure);
                txtMax.setText((tempMax-273.15)+" C\n"+tempMax+" K");
                txtMin.setText((tempMin-273.15)+" C\n"+tempMin+" K");
                prgBar.setProgress((int)humidity);

            }

            super.onPostExecute(s);
        }
    }

}

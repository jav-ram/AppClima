package com.example.javier.clima;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnIngresar;
    private EditText txtCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);
        txtCity = (EditText) findViewById(R.id.txtCity);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnIngresar:{
                Intent next = new Intent(this, ActivityResultados.class);
                next.putExtra("city", ""+txtCity.getText());
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage(txtCity.getText());
                startActivity(next);
                break;
            }
        }
    }
}

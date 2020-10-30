package com.example.productionmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.lang.*;

import static java.lang.Math.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText totalMachine, workingHour, allowance, frontPart, backPart, sleevePart, neckPart;
    private Button result;
    private RadioButton radioButton;
    int machine, pcs;
    double front, back, sleeve, neck;
    double f, b, s, n, hour;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //actionBar.setTitle(Html.fromHtml("<font color='#ff0000'> 'Production Manager' </font>"));
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.production);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        totalMachine = (EditText) findViewById(R.id.totalMachineId);
        workingHour = (EditText) findViewById(R.id.workingHourId);
       // allowance = (EditText) findViewById(R.id.allowanceId);
        frontPart = (EditText) findViewById(R.id.frontPartId);
        backPart = (EditText) findViewById(R.id.backPartId);
        sleevePart = (EditText) findViewById(R.id.sleevePartId);
        neckPart = (EditText) findViewById(R.id.neckPartId);

        //radioButton = (RadioButton) findViewById(R.id.radioButtonId);
        result = (Button) findViewById(R.id.resultButtonId);

        result.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.resultButtonId)
        {
            calculateResult();
        }
    }

    public void calculateResult()
    {
        if(!totalMachine.getText().toString().matches(""))
           machine = Integer.parseInt(totalMachine.getText().toString());
        else{
            Toast.makeText(MainActivity.this, "Enter quantity of machines", Toast.LENGTH_SHORT).show();
            return;}
        if(!frontPart.getText().toString().matches(""))
        {front = Double.parseDouble(frontPart.getText().toString());} // per parts time
        else{
            front = 0;
        }
        if(!backPart.getText().toString().matches(""))
         back = Double.parseDouble(backPart.getText().toString());
        else back = 0;
        if(!sleevePart.getText().toString().matches(""))
         sleeve = Double.parseDouble(sleevePart.getText().toString());
        else sleeve = 0;
        if(!neckPart.getText().toString().matches(""))
         neck = Double.parseDouble(neckPart.getText().toString());
        else neck = 0;
        if(!workingHour.getText().toString().matches(""))
         hour = Double.parseDouble(workingHour.getText().toString());
        else{
            Toast.makeText(MainActivity.this, "Enter working hour", Toast.LENGTH_SHORT).show();
            return;}

        double totalMinute = front+back+sleeve+neck; // total of per parts
        double dayTime = hour*60*machine; // whole working day
        if(front != 0)
        {
            pcs = (int) (((front/totalMinute) * dayTime)/front); // production of pcs in a day
        }
        else if(back!=0)
            pcs = (int) (((back/totalMinute) * dayTime) / back); // production of pcs in a day
        else if(sleeve !=0 )
            pcs = (int) (((sleeve/totalMinute) * dayTime) / sleeve);
        else
            pcs = (int) (((neck/totalMinute) * dayTime) / neck);



        double frontTime = (dayTime *(front/totalMinute))/60; // time for front part of a day in hour
        double backTime = (dayTime *(back/totalMinute)) /60;
        double sleeveTime = (dayTime *(sleeve/totalMinute))/60;
        double neckTime = (dayTime *(neck/totalMinute))/60;

        int highestPcs = (int) ((frontTime*60)/front);
        double workingHour = machine*hour;

         f = (dayTime *(front/totalMinute))/(hour*60); // total machine needed for front part production
         b = (dayTime *(back/totalMinute))/(hour*60); // total machine needed for back part production
         s = (dayTime *(sleeve/totalMinute))/(hour*60); // total machine needed for sleeve part production
         n = (dayTime *(neck/totalMinute))/(hour*60); // total machine needed for neck part production

        if(b==0 && s==0 && n==0 && f!=0)
            f = machine;
        else if(f-Math.floor(f) >= .5)
        {
            f = Math.ceil(f);
        }
        else
        {
            f = Math.floor(f);
        }

        if(s==0 && n==0 && b!=0)
            b = (machine - f);
        else if(b-Math.floor(b) >= .5)
        {
            b = Math.ceil(b);
        }
        else
        {
            b = Math.floor(b);
        }

        if(n==0 && s!=0)
            s = machine - (f+b);
        if(s-Math.floor(s) >= .5)
        {
            s = Math.ceil(s);
        }
        else
        {
            s = Math.floor(s);
        }

        if(n!=0)
            n = machine - (f+b+s);

        int ff = (int) f; // double to int convert
        int bb = (int) b;
        int ss = (int) s;
        int nn = (int) n;

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("front", String.valueOf(ff) /* int to string convert*/);
        intent.putExtra("back", String.valueOf(bb));
        intent.putExtra("sleeve", String.valueOf(ss));
        intent.putExtra("neck", String.valueOf(nn));
        intent.putExtra("frontTime", String.format("%.2f", frontTime));
        intent.putExtra("backTime", String.format("%.2f", backTime));
        intent.putExtra("sleeveTime", String.format("%.2f", sleeveTime));
        intent.putExtra("neckTime", String.format("%.2f", neckTime));
        intent.putExtra("pcs", String.valueOf(pcs));
        intent.putExtra("hour", String.format("%.2f", workingHour));
        startActivity(intent);
    }
}
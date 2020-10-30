package com.example.productionmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView f, b, sl, n;
    private TextView ft, bt, st, nt, pcs, hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().hide();

        f = (TextView) findViewById(R.id.fId);
        b = (TextView) findViewById(R.id.bId);
        sl = (TextView) findViewById(R.id.sId);
        n = (TextView) findViewById(R.id.nId);
        ft = (TextView) findViewById(R.id.frontTimeId);
        bt = (TextView) findViewById(R.id.backTimeId);
        st = (TextView) findViewById(R.id.sleeveTimeId);
        nt = (TextView) findViewById(R.id.neckTimeId);
        pcs = (TextView) findViewById(R.id.pcsId);
        hour = (TextView) findViewById(R.id.hourId);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            String s = bundle.getString("front");
            f.setText(s);
            s = bundle.getString("back");
            b.setText(s);
            s = bundle.getString("sleeve");
            sl.setText(s);
            s = bundle.getString("neck");
            n.setText(s);
            s = bundle.getString("frontTime");
            ft.setText(s);
            s = bundle.getString("backTime");
            bt.setText(s);
            s = bundle.getString("sleeveTime");
            st.setText(s);
            s = bundle.getString("neckTime");
            nt.setText(s);
            s = bundle.getString("pcs");
            pcs.setText(s);
            s = bundle.getString("hour");
            hour.setText(s);
        }

    }
}
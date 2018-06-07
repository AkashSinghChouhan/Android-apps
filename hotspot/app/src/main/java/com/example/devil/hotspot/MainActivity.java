package com.example.devil.hotspot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn= (Button) findViewById(R.id.buton);
        btn.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(){


            }
        });
    }
}

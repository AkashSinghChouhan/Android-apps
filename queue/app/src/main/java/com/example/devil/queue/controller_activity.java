package com.example.devil.queue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class controller_activity extends AppCompatActivity {
    ListView listView=(ListView)findViewById(R.id.list);
    ArrayAdapter list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller_activity);

        ArrayList<String> users=new ArrayList<>();
        String[] names=new String[]{"ram","shyam"};
        users.addAll(Arrays.asList(names));
        list = new ArrayAdapter(this,R.layout.list_item,names);
        listView.setAdapter( list );
       // ApManager.isApOn(controller_activity.this); // check Ap state :boolean
       // ApManager.configApState(controller_activity.this); // change Ap state :boolean
    }
}

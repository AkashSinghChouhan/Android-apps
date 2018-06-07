package com.example.devil.queue;

/**
 * Created by DEVIL on 27-03-2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.TextView;

public class SimpleAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public SimpleAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, true);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        Button button = (Button) rowView.findViewById(R.id.button);
        textView.setText(values[position]);

        return rowView;
    }
}

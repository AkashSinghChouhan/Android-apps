package training.sati.com.phonereader;

import android.content.Context;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

/**
 * Created by acer on 10/18/2016.
 */

public class ContactAdaptor extends BaseAdapter {
    List<contact> li;
    ListView list;
    List<contact> liSearchReasults;
    private Context context;

    public ContactAdaptor(Context context, List<contact> li) {
        this.context = context;
        this.li = li;
        liSearchReasults = new ArrayList<contact>();
        liSearchReasults.addAll(li);

    }

    public void setItemList(List<contact> li) {
        this.li = li;

    }

    @Override
    public int getCount() {
        if (li == null)
            return 0;
        else return li.size();
    }

    @Override
    public Object getItem(int position) {
        return li.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final contact co = li.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_list_item, parent, false);
        }
        TextView tvname = (TextView) convertView.findViewById(R.id.textView);
        TextView tvnumber = (TextView) convertView.findViewById(R.id.textView1);
        tvname.setText(co.getName());
        tvnumber.setText(co.getNumber());
        return convertView;


    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        li.clear();
        if (charText.length() == 0) {
            li.addAll(liSearchReasults);
        } else {
            for (contact co : liSearchReasults) {
                if (charText.length() != 0 && co.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    li.add(co);
                }
                // else if (charText.length()!=0&&co.getName().toLowerCase(Locale.getDefault()).contains(charText)){li.add(co);}

                //}
            }
            notifyDataSetChanged();
        }
    }
}
package training.sati.com.newsreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by acer on 10/19/2016.
 */

public class NewsAdaptor extends BaseAdapter {
    List <News> li;
    ListView list;
    private Context context;
    public NewsAdaptor(Context context,List<News> li)
    {

        this.context=context;
        this.li=li;

    }
    public void setItem(List<News> li)
    {
        this.li=li;
    }

    @Override
    public int getCount() {
        if (li==null)
        return 0;

        else
            return li.size();
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
        final News news=li.get(position);
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.layout_list_item,parent,false);
        }
        TextView tvname=(TextView)convertView.findViewById(R.id.textView);
        TextView tvnumber=(TextView) convertView.findViewById(R.id.textView2);
        tvname.setText(news.getHeadline());
        tvnumber.setText(news.getSummery());

        return convertView;
    }
}

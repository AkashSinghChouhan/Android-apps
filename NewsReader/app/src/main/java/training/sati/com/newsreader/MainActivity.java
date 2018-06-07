package training.sati.com.newsreader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Activity activity;
    ListView lvNews;
    NewsAdaptor adaptor;
    List<News> listNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity=this;
        lvNews=(ListView) findViewById(R.id.listtv);

    lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news=(News) parent.getAdapter().getItem(position);
                Uri uriUrl=Uri.parse(news.getNewslink());
                Intent  launchBrowser=new Intent(Intent.ACTION_VIEW,uriUrl);
                startActivity(launchBrowser);
            }
        });

    (new AsynchListViewLoader()).execute();
    }


    URL url;
    List<News> lstNews;
    public List<News> readNewsFromFeed()throws Exception{


         lstNews=new ArrayList<>();

      url =new URL("http://timesofindia.indiatimes.com/rssfeedstopstories.cms");
        HttpURLConnection httpCon=(HttpURLConnection)url.openConnection();
        SyndFeedInput input=new SyndFeedInput();
        SyndFeed feed=input.build(new XmlReader(httpCon));
        List<SyndFeedInput> entries=feed.getEntries();
        Iterator<SyndFeedInput> iterator=entries.iterator();


while (iterator.hasNext())
{

    SyndEntry entry= (SyndEntry) iterator.next();
    String s=entry.getDescription().getValue();
  //  String sunString=new String("lakh");
    //if(s.contains(sunString)) {

        News news = new News();
        news.setHeadline(entry.getTitle());
        news.setNewslink(entry.getLink());
        news.setSummery(entry.getDescription().getValue());
        lstNews.add(news);
   //}
}
        return lstNews;
    }

    private class AsynchListViewLoader extends AsyncTask<String, Void, List<News>> {
        private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);


        @Override
        protected List<News> doInBackground(String... params) {
            List<News> newses = new ArrayList<News>();
            try {

                newses = readNewsFromFeed();


            } catch (Exception e) {
                System.out.println("e.setStackTrace()");
            }
            return newses;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Downloading");
            dialog.show();

        }

        @Override
        protected void onPostExecute(List<News> linews) {
            super.onPostExecute(linews);
            if (linews != null) {
                adaptor=new NewsAdaptor(activity,linews);
                lvNews.setAdapter(adaptor);
                adaptor.setItem(linews);

               adaptor.notifyDataSetChanged();
            }
        dialog.dismiss();
        }
    }

}
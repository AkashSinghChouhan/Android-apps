package training.sati.com.myserverdataconnection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
//import android.support.test.espresso.core.deps.guava.reflect.TypeToken;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Activity activity;
    ListView lvStudent;
    StudentAdaptor adaptor;
    List<Student> li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity=this;
        lvStudent = (ListView) findViewById(R.id.lv_activity_main);
        (new AsynchListViewLoader()).execute();

    }
    private class AsynchListViewLoader extends AsyncTask<String, Void, List<Student>> {
        private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        @Override
        protected List<Student> doInBackground(String... params) {
           try {
               URL url=new URL("http://172.16.12.79/MainService/MainService?cmd=1");
               String charSet="UTF-8";
            HttpURLConnection conn=((HttpURLConnection)url.openConnection());
             conn.setConnectTimeout(10000);
               conn.setDoInput(true);
               conn.setRequestProperty("Accept-Charset",charSet);
               InputStream response=conn.getInputStream();
               BufferedReader reader=new BufferedReader(new InputStreamReader(response,charSet));
               String readeData=reader.readLine();
               Type type=new TypeToken<List<Student>>() {}.getType();
               Gson gason=new GsonBuilder().create();
               li=gason.fromJson(readeData,type);
               if(response!=null)response.close();
               if(reader!=null)reader.close();
               if(conn!=null)conn.disconnect();


            } catch (Exception e) {

               e.printStackTrace();
           }
          return li;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //super.onPreExecute();
            dialog.setMessage("Downloading");
            dialog.show();
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
           // super.onPostExecute(students);
            if (students != null) {
                 adaptor=new StudentAdaptor(activity,students);
               lvStudent.setAdapter(adaptor);
                adaptor.setItem(students);

                adaptor.notifyDataSetChanged();
            }
           dialog.dismiss();
        }
        }
    }

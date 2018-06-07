package training.sati.com.phonereader;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Activity activity;
    ContactAdaptor adapter;
    ListView lvContact;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_search,menu);
        MenuItem searchIteam=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView)MenuItemCompat.getActionView(searchIteam);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener(){@Override public void onFocusChange(View v,boolean hasFocus){}
    });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){



            @Override
            public boolean onQueryTextSubmit(String query){return false;
        }
            @Override
            public boolean onQueryTextChange(String searchQuery){
            adapter.filter(searchQuery.toString().trim());
                lvContact.invalidate();
                return true;
            }
        });
        MenuItemCompat.setOnActionExpandListener(searchIteam,new MenuItemCompat.OnActionExpandListener(){
           @Override
                   public boolean onMenuItemActionCollapse(MenuItem item){
                return true;
            }
        @Override
                public boolean onMenuItemActionExpand(MenuItem menuItem){
                return true;
            }


        });
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
        setContentView(R.layout.activity_main);
        lvContact = (ListView) findViewById(R.id.listv);
        List<contact> ListCont = readContact();
        adapter = new ContactAdaptor(activity, ListCont);
        lvContact.setAdapter(adapter);
        adapter.setItemList(ListCont);
        adapter.notifyDataSetChanged();

    }



    public List<contact>readContact()
    {
        List<contact>lst=new ArrayList<contact>();

        Cursor phone=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while (phone.moveToNext())
        {
            String name= phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number= phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contact record=new contact();
            record.setName(name);
            record.setNumber(number);
            lst.add(record);
        }
        phone.close();


        return lst;
    }}


package training.sati.com.studentdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//public class MainActivity extends AppCompatActivity {

   public class MainActivity extends AppCompatActivity {
        DBHelper db;
        Spinner spinnerbranch;
        EditText edtName;
       EditText edtSem;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            edtName = (EditText) findViewById(R.id.edt_activity_main_name);
            edtSem = (EditText) findViewById(R.id.edt_activity_main_sem);
            spinnerbranch = (Spinner) findViewById(R.id.spnr_activity_main_branch);
            loadSpinnerBranch();

            if(getDBHandler().isRecordAvailable()){
                String ret[] = getDBHandler().getRecord();
                spinnerbranch.setSelection(getIndex(spinnerbranch, ret[1]));
                edtName.setText(ret[0]);
                edtSem.setText(ret[2]);
            }
        }

        private void loadSpinnerBranch(){
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getBranchList());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerbranch.setAdapter(dataAdapter);
        }
        private List<String> getBranchList(){
            List<String> lstBranch = new ArrayList<String>();
            lstBranch.add("Computer");
            lstBranch.add("Electronics");
            lstBranch.add("Electrical");
            lstBranch.add("Mechanical");
            lstBranch.add("Civil");
            return lstBranch;
        }
        private DBHelper getDBHandler()	{
            if (db == null)
            {
                db = new DBHelper(this);
                db.getWritableDatabase();//call upgrade db to cater to the application upgrade
            }
            return db;
        }

        private void updateTable(String name, String branch,String sem){
            if (db.isRecordAvailable()){
                db.deleteRecord();
            }
            db.insertRecord(name, branch,sem);
        }

        private int getIndex(Spinner spinner, String myString)
        {
            int index = 0;

            for (int i=0;i<spinner.getCount();i++){
                if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                    index = i;
                    break;
                }
            }
            return index;
        }

        public void onClick(View view){
            if (view.getId() == R.id.btn_activity_main_save){
                if(!edtName.getText().toString().trim().equals("")) {
                    updateTable(edtName.getText().toString(), spinnerbranch.getSelectedItem().toString(),edtSem.getText().toString());
                    Toast.makeText(this, "Record inserted",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this, "Name is empty",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }



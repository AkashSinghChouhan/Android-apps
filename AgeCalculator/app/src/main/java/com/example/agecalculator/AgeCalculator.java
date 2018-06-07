package com.example.agecalculator;








import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

import org.joda.time.LocalDate;
import org.joda.time.Period;

public class AgeCalculator extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculator);
        
      final EditText et_year= (EditText) findViewById(R.id.et_year);
       final EditText et_month= (EditText) findViewById(R.id.et_month);
      final EditText et_day= (EditText) findViewById(R.id.et_day);
       
        Button bt=(Button) findViewById(R.id.bt);
        
        final TextView tv_age=(TextView) findViewById(R.id.tv_age);
        
        bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				int month= Integer.parseInt(et_month.getText().toString());
				int day= Integer.parseInt(et_day.getText().toString());
				int year= Integer.parseInt(et_year.getText().toString());
				
				LocalDate birthdate= new LocalDate(year,month,day);
				LocalDate today = new LocalDate();
				Period period = new Period(birthdate,today);
				
				tv_age.setText("You are "+period.getYears()+" Years "+period.getMonths()+" Months "+period.getDays()+" Days old !");
				
				
				 
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.age_calculator, menu);
        return true;
    }
    
}

package training.sati.com.myserverdataconnection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 10/21/2016.
 */

public class StudentAdaptor extends BaseAdapter  {
    List<Student> li;
    ListView list;
    List<Student> studentList;
    private Context context;
StudentAdaptor(Context context,List<Student> li)
{
    this.context=context;
    this.li=li;
studentList=new ArrayList<Student>();
    studentList.addAll(li);

}
    public void setItem(List<Student> li)
    {
        this.li=li;
    }
    @Override
    public int getCount() {
        if (li == null) return 0;
        else return (li.size());
    }

    @Override
    public Object getItem(int position) {
        return (li.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

final Student student=li.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.student_item, parent, false);
        }
        TextView editFirstName=(TextView) convertView.findViewById(R.id.edit_FirstName);
        TextView  editLastName=(TextView) convertView.findViewById(R.id.edit_LastName);
        TextView editsem=(TextView) convertView.findViewById(R.id.edit_sem);
        TextView editstate=(TextView) convertView.findViewById(R.id.edit_state);
        TextView  editCity=(TextView) convertView.findViewById(R.id.edit_city);

        editLastName.setText(student.getLastName());
        editFirstName.setText(student.getFirstName());
        editCity.setText(student.getHomeCity());
        editsem.setText( student.getSemester()+" ");
        editstate.setText(student.getHomeState());

        return convertView;
    }
}

package training.sati.com.studentdata;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by acer on 10/20/2016.
 */


public class DBHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        SQLiteDatabase db;
        public static final String DATABASE_NAME = "mydb.db";
        public static final String STUDENT_TABLE_NAME = "student_table";
        public static final String STUDENT_COLUMN_NAME = "name";
        public static final String STUDENT_COLUMN_BRANCH = "branch";
    public static final String STUDENT_COLUMN_SEM= "sem";

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    "create table " + STUDENT_TABLE_NAME +
                            " ( " +
                            STUDENT_COLUMN_NAME + " text , " +

                            STUDENT_COLUMN_BRANCH + " text, " +
                            STUDENT_COLUMN_SEM + " text " +
                            " ) ");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME );
            db.execSQL(
                    "create table " + STUDENT_TABLE_NAME +
                            " ( " +
                            STUDENT_COLUMN_NAME + " text , " +
                            STUDENT_COLUMN_SEM + " text , " +
                            STUDENT_COLUMN_BRANCH + " text " +
                            " ) ");
        }

        private void getDatabaseHandler()
        {
            if (db==null)
                db = this.getWritableDatabase();
        }

        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public boolean isRecordAvailable(){
            if (db == null) getDatabaseHandler();
            ContentValues contentValues = null;
            String qry = " select count(*)  from  " + STUDENT_TABLE_NAME + " ";
            Cursor res = db.rawQuery(qry, null);
            res.moveToFirst();
            int count = res.getInt(0);
            if (count == 1)
                return true;
            else
                return false;

        }

        public void insertRecord(String name, String branch,String sem){
            if (db == null) getDatabaseHandler();
            ContentValues contentValues = null;
            contentValues = new ContentValues();
            contentValues.put(STUDENT_COLUMN_NAME,name);
            contentValues.put(STUDENT_COLUMN_BRANCH, branch);
            contentValues.put(STUDENT_COLUMN_SEM, sem);
            db.insert(STUDENT_TABLE_NAME, null, contentValues);
        }

        public int deleteRecord(){
            if (db == null) getDatabaseHandler();
            return db.delete(STUDENT_TABLE_NAME, null, null);
        }

        public String[] getRecord(){
            String retArray[] = new String[3];
            if (db == null) getDatabaseHandler();
            Cursor res =  db.rawQuery( "select " + STUDENT_COLUMN_NAME + "," + STUDENT_COLUMN_SEM +"," + STUDENT_COLUMN_BRANCH + " from " + STUDENT_TABLE_NAME , null );
            res.moveToFirst();

            if(res.isAfterLast() == false){
                retArray[0] = res.getString(res.getColumnIndex(STUDENT_COLUMN_NAME));
                retArray[1] = res.getString(res.getColumnIndex(STUDENT_COLUMN_BRANCH));
                retArray[2] = res.getString(res.getColumnIndex(STUDENT_COLUMN_SEM));
            }

            return retArray;
        }

    }



package com.safeinvest.safetrax;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDBHelper {
    public static final String EmpId = "EmpId";
    public static final String EmpName ="EmpName";
    public static final String EmpPass= "EmpPass";
    public static final String EmpDept = "EmpDept";
    public static final String EmpCate = "EmpCate";
    public static final String DepartmentId="DepartmentId";
    public static final String EmpTask = "EmpTask";
    public static final String User = "User";
    public static final String EmpGroup = "EmpGroup";
    public static final String EmpPlans = "EmpPlans";
    public static final String EmpDetails = "EmpDetails";
    public static final String databasename = "EmployeeDB2";
    public static final String tablename="Employee";
    public static final String tablename2="UserTask";
    public static final String tablename3="Department";
    private static final int databaseversion = 1;
    private static final String create_table2 = "create table UserTask(EmpName text not null,EmpDept text,EmpCate text,EmpTask text,EmpGroup text,EmpPlans text,EmpDetails text,User text)";
    private static final String create_table = "create table Employee(EmpId integer primary key autoincrement,"+"EmpName text not null, EmpPass text not null,EmpDept text)";
    private static final String create_table3 ="create table Department(DepartmentId integer primary key autoincrement,"+"EmpDept text Unique not null)";
    private final Context ct;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;


    public EmployeeDBHelper(Context context) {
        this.ct = context;
        dbHelper = new DatabaseHelper(ct);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context c)
        {
            super(c, databasename, null, databaseversion);
        }
        public void onCreate(SQLiteDatabase database)
        {
            try
            {
                database.execSQL(create_table);
                database.execSQL(create_table2);
                database.execSQL(create_table3);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Employee");
            db.execSQL("Drop TABLE IF EXISTS UserTask");
            db.execSQL("Drop TABLE IF EXISTS Department");
            onCreate(db);
        }
    }

    public EmployeeDBHelper connect() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void disconnect()
    {
        dbHelper.close();
    }

    public void insertEmployee(String empname,String emppass,String dept01)
    {
        this.connect();
        ContentValues cv = new ContentValues();
        cv.put(EmpName,empname);
        cv.put(EmpPass,emppass);
        cv.put(EmpDept,dept01);
        database.insert(tablename,null,cv);
        //database.insert(tablename2,null,cv);
        ContentValues contentValues= new ContentValues();
        contentValues.put(EmpDept,dept01);
        database.insert(tablename3,null,contentValues);

    }

    public long addtask(String name,String dept,String cate,String task,String group,String plans,String details,String user){
       ContentValues cv= new ContentValues();
       cv.put(EmpDept,dept);
       cv.put(EmpCate,cate);
       cv.put(EmpTask,task);
       cv.put(EmpGroup,group);
       cv.put(EmpPlans,plans);
       cv.put(EmpDetails,details);
       cv.put(User,user);
       cv.put(EmpName,name);
       ContentValues contentValues=new ContentValues();
       contentValues.put(EmpDept,dept);
       this.connect();
       //database.insert(tablename3,null,contentValues);
       return database.insert(tablename2,null,cv);
    }
    public List<String> getAllLabels(String deptname){
        List<String> list = new ArrayList<String>();

        String selectQuery = "Select * from Employee Where EmpDept = ?";

       this.connect();
        Cursor cursor = database.rawQuery(selectQuery, new String[]{deptname});

        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }

    public Cursor getallemp(){
        this.connect();
        Cursor c=database.rawQuery("Select * From "+tablename2,null);
        return c;
    }

    public Cursor retrieveAllEmployees()
    {
        this.connect();
        return database.query(tablename2,new String[]
                {EmpId,EmpName,EmpPass,EmpDept,EmpCate,EmpTask,EmpGroup,EmpPlans,EmpDetails},null,null,null,null,null);
    }

    public Cursor retrieveEmployee(String name)throws SQLException
    {
        this.connect();
        Cursor c = database.query(true,tablename,new String[] {EmpName,EmpPass},EmpName+ "=" +
                name,null,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
        }
        return c;
    }

    public Boolean checkusername(String username){
        this.connect();
        Cursor cursor=database.rawQuery("Select * from Employee Where EmpName = ?",new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }
        else  return false;
    }

    public Boolean checkusernamepass(String username,String password){
        this.connect();
        Cursor cursor=database.rawQuery("Select * from Employee Where EmpName = ? and EmpPass= ?",new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }
        else  return false;
    }

    public boolean deleteEmployee(long id)
    {
        this.connect();
        return database.delete(tablename,EmpId + "=" +id,null)>0;
    }

    public boolean updateEmployee(long id,String empname,int emppass)
    {
        this.connect();
        ContentValues cvalues = new ContentValues();
        cvalues.put(EmpName,empname);
        cvalues.put(EmpPass,emppass);
        return database.update(tablename,cvalues,EmpId+"="+id,null)>0;
    }

}

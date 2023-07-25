package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.databaseapp.database.MyDatabase;

public class MainActivity extends AppCompatActivity {

    EditText Name,Address,Salary;
    Button b1;
    ListView lv;
    MyDatabase mdb=new MyDatabase(this);
    Cursor cursor;
    SimpleCursorAdapter sca;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name=findViewById(R.id.editTextName);
        Address=findViewById(R.id.editTextTAddress);
        Salary=findViewById(R.id.editTextSalary);
        b1=findViewById(R.id.buttonInsert);
        lv=findViewById(R.id.listEmp);
        eventHandler();
        showdata();

    }
    protected void eventHandler()
    {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stName=Name.getText().toString().trim();
                String stAddr=Address.getText().toString().trim();
                Integer intSal=Integer.parseInt(Salary.getText().toString().trim());

                //field validation
                if(stAddr.isEmpty()){
                    Address.setError("Address is not Empty");
                } else if (stName.isEmpty()) {

                    Name.setError("Name is can not be Empty");
                } else if (intSal==null||intSal.equals("")) {

                    Salary.setError("Salary is not Empty");

                }else {
                    ContentValues cv=new ContentValues();
                    cv.put("name",stName);
                    cv.put("address",stAddr);
                    cv.put("salary",intSal);

                    mdb.insertEmp(cv);
                    Toast.makeText(MainActivity.this, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                    Name.setText(null);
                    Address.setText(null);
                    Salary.setText(null);

                    //lets requry for new entry

                    cursor.requery();
                }


            }
        });
    }

    public void showdata()
    {
        cursor= mdb.getData();
        String[] fromDb={"name","address","salary"};
        int[] toTextview={R.id.textViewName,R.id.textViewAddress,R.id.textViewSalary};
        sca=new SimpleCursorAdapter(this,R.layout.row,cursor,fromDb,toTextview,1);
        lv.setAdapter(sca);
        sca.notifyDataSetChanged();
        cursor.requery();
    }
}
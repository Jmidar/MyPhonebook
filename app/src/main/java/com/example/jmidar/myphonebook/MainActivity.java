package com.example.jmidar.myphonebook;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnsave,btnview,btnupdate,btndelete;
    TextView etxtId,etxtName,etxtCell;

    MySqliteDB sqliteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsave = findViewById(R.id.btn_save);
        btnview = findViewById(R.id.btn_view);
        btnupdate = findViewById(R.id.btn_update);
        btndelete = findViewById(R.id.btn_delete);

        etxtId = findViewById(R.id.etxt_id);
        etxtName = findViewById(R.id.etxt_name);
        etxtCell = findViewById(R.id.etxt_cell);

        sqliteDB = new MySqliteDB(MainActivity.this);


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = etxtId.getText().toString();
                String name = etxtName.getText().toString();
                String cell = etxtCell.getText().toString();



                if(id.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter ID!", Toast.LENGTH_SHORT).show();
                }
                else if(name.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter name!", Toast.LENGTH_SHORT).show();
                }
                else if(cell.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter cell!",Toast.LENGTH_SHORT).show();
                }
                else{

                     int myid = Integer.parseInt(id);
                    boolean check = sqliteDB.addToTable(myid,name,cell);
                    if(check == true){
                        Toast.makeText(MainActivity.this,"Data Insert Successful!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Data not Insert Successful!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //for view data
    public void viewInfo(View v)
    {
        Cursor result = sqliteDB.viewDate();
        if (result.getCount() == 0)
        {
            Toast.makeText(this,"No Data Found", Toast.LENGTH_SHORT).show();
        }
        else{
            StringBuffer data = new StringBuffer();
            result.moveToFirst(); //for pointing first row
            do{
                data.append("ID: "+result.getString(0)+"\n");
                data.append("Name: "+result.getString(1)+"\n");
                data.append("Cell: "+result.getString(2)+"\n");
            }while (result.moveToNext());

            showInfo("My Contacts", data.toString());
        }
    }

    public  void showInfo(String title, String data){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setCancelable(true);
        dialog.setMessage(data);
        dialog.show();

    }

    public void updateInfo(View v){
                String id = etxtId.getText().toString();
                String name = etxtName.getText().toString();
                String cell = etxtCell.getText().toString();



                if(id.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter ID!", Toast.LENGTH_SHORT).show();
                }
                else if(name.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter name!", Toast.LENGTH_SHORT).show();
                }
                else if(cell.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter cell!",Toast.LENGTH_SHORT).show();
                }
                else{
                    int myid = Integer.parseInt(id);
                    boolean check = sqliteDB.addToTable(myid,name,cell);
                    if(check == true){
                        Toast.makeText(MainActivity.this,"Data Update Successful!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Data not Updated Successful!", Toast.LENGTH_SHORT).show();
                    }
                }
    }



    public  void deleteInfo(View v){
        String id = etxtId.getText().toString();
        int check = sqliteDB. deleteData(id);
        if (check>0)
        {
            Toast.makeText( this, "Data Delete Successful!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText( this, "Data not Delete Successful!", Toast.LENGTH_SHORT).show();
        }
    }

}

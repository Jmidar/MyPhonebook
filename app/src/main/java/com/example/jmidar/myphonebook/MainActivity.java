package com.example.jmidar.myphonebook;

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

                int myid = Integer.parseInt(id);

                if(id.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter name!", Toast.LENGTH_SHORT).show();
                }
                else if(cell.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter cell!",Toast.LENGTH_SHORT).show();
                }
                else{

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
}

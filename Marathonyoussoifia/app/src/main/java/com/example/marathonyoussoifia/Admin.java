package com.example.marathonyoussoifia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Modifier;

public class Admin extends AppCompatActivity {
    EditText Name ,Email,Contact;
    Button Ajouter, Modifier,Supprimer,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        Name = findViewById(R.id.Name);
        Contact = findViewById(R.id.Contact);
        Email = findViewById(R.id.Email);


        Ajouter = findViewById(R.id.btnAjout);
        Supprimer = findViewById(R.id.btnSupp);
        Modifier = findViewById(R.id.btnModif);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(this);

        Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NameTXT = Name.getText().toString();
                String ContactTXT = Contact.getText().toString();
                String EmailTXT = Email.getText().toString();


                Boolean checkinserdata = DB.insertuserdata(NameTXT, ContactTXT, EmailTXT);
                if (checkinserdata == true)
                    Toast.makeText(Admin.this, "AJOUTER", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(Admin.this, "ne pas ajouter", Toast.LENGTH_SHORT).show();
            }
        });


        Modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NameTXT = Name.getText().toString();
                String ContactTXT = Contact.getText().toString();
                String EmailTXT = Email.getText().toString();


                Boolean checkupdatedata = DB.updateuserdata(NameTXT, ContactTXT, EmailTXT);
                if (checkupdatedata == true)
                    Toast.makeText(Admin.this, "Modifier", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(Admin.this, "ne pas Modifier", Toast.LENGTH_SHORT).show();
            }
        });

        Supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NameTXT = Name.getText().toString();



                Boolean checkdeletedata = DB.deletedata(NameTXT);
                if (checkdeletedata == true)
                    Toast.makeText(Admin.this, "SUPPRIMER", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(Admin.this, "ne pas Supprimer", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(Admin.this, "No entry existe", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                   buffer.append("Name : " + res.getString(0)+"\n");
                    buffer.append("Contact : " + res.getString(1)+"\n");
                    buffer.append("Email: " + res.getString(2)+"\n\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin.this);
                builder.setCancelable(true);
                builder.setTitle("Condidat entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

    }
    }
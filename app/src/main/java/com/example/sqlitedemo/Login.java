package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username,password;
    Button signin;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username1);
        password=findViewById(R.id.password1);
        signin=findViewById(R.id.btnlogin1);
        DB=new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(Login.this, "Please Enter The DATA", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                    {
                        if(checkuserpass==true){
                            Toast.makeText(Login.this, "Sign in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),Home.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login.this, "Sign in Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }
}
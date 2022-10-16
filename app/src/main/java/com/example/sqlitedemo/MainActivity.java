package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password,repassword;
    Button signup,signin;

    DBHelper Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        signup=findViewById(R.id.btnsignup);
        signin=findViewById(R.id.btnlogin);
        Db=new DBHelper(this);
         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String user=username.getText().toString();
                 String pass=password.getText().toString();
                 String repass=repassword.getText().toString();
                 if (user.equals("")||pass.equals("")||repass.equals("")) {
                     Toast.makeText(MainActivity.this, "Please Enter All The DATA", Toast.LENGTH_SHORT).show();
                 }
                 else{
                     if(pass.equals(repass)) {
                         Boolean checkuser = Db.checkusername(user);
                         if (checkuser == false) {
                             Boolean insert = Db.insertData(user, pass);
                             if (insert == true) {
                                 Toast.makeText(MainActivity.this, "Resister Successfully", Toast.LENGTH_SHORT).show();
                                 Intent x = new Intent(getApplicationContext(), Home.class);
                                 startActivity(x);
                             } else {
                                 Toast.makeText(MainActivity.this, "Please Check Your UseLess DATA Once Again", Toast.LENGTH_SHORT).show();
                             }
                         }else{
                             Toast.makeText(MainActivity.this, "User Already have an account cant open his account", Toast.LENGTH_SHORT).show();
                         }
                     }else{
                         Toast.makeText(MainActivity.this, "PAssword is Not MAtching", Toast.LENGTH_SHORT).show();
                     }
                 }
             }
         });

         signin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(MainActivity.this, "Nicely Done", Toast.LENGTH_SHORT).show();
                 Intent intent =new Intent(getApplicationContext(),Login.class);
                 startActivity(intent);
             }
         });
    }
}
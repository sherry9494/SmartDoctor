package com.fyp.smartdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView logo;
    private static int splashTimeOut=4000;
    TextView txt;
    EditText name;
    LinearLayout next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.main_img);
        txt = findViewById(R.id.main_txt);
        name = findViewById(R.id.main_edt);
        next = findViewById(R.id.main_next);

        Log.d("SHERRY","HELLO");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

               txt.setVisibility(View.VISIBLE);
               name.setVisibility(View.VISIBLE);
               next.setVisibility(View.VISIBLE);


               next.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       String nameStr = name.getText().toString();
                       if(nameStr.isEmpty())
                       {
                           Toast.makeText(MainActivity.this, "Please Enter your Name", Toast.LENGTH_SHORT).show();
                       }else {

                           Intent i = new Intent(MainActivity.this, HomeActivity.class);
                           i.putExtra("NAME",nameStr);
                           startActivity(i);
                           finish();
                       }

                   }
               });


            }
        },splashTimeOut);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.animation);
        logo.startAnimation(myanim);


    }
}

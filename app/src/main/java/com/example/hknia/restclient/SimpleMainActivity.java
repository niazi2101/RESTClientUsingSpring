package com.example.hknia.restclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hknia.restclient.activities.DeleteActivity;
import com.example.hknia.restclient.activities.GetActivity;
import com.example.hknia.restclient.activities.GetAllActivity;
import com.example.hknia.restclient.activities.PostActivity;
import com.example.hknia.restclient.activities.PutActivity;

public class SimpleMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_main);
    }

    //Open related activities on button calls
    public void buttonClick(View view)
    {
        switch (view.getId()) {

            case R.id.btnGet:
                // do your code
                Intent i = new Intent(SimpleMainActivity.this, GetActivity.class);
                startActivity(i);
                break;

            case R.id.btnGetAll:
                // do your code
                Intent j = new Intent(SimpleMainActivity.this, GetAllActivity.class);
                startActivity(j);
                break;

            case R.id.btnPost:
                // do your code
                Intent k = new Intent(SimpleMainActivity.this, PostActivity.class);
                startActivity(k);
                break;

            case R.id.btnPut:
                // do your code
                Intent l = new Intent(SimpleMainActivity.this, PutActivity.class);
                startActivity(l);
                break;

            case R.id.btnDelete:
                // do your code
                Intent m = new Intent(SimpleMainActivity.this, DeleteActivity.class);
                startActivity(m);
                break;

            default:
                break;
        }
    }
}

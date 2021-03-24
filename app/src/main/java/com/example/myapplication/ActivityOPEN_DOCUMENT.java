package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ActivityOPEN_DOCUMENT extends Activity {

    private Socketmanager mSockManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.date);

        mSockManager=new Socketmanager(this);
        boolean printOK = false;
        //int g = 1/0;
        Intent intent = getIntent();

        String string = intent.getStringExtra("string");
        String printerIP = intent.getStringExtra("printerIP");

        //Toast.makeText(this, "OPEN_DOCUMENT", Toast.LENGTH_LONG).show();

        intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }



}

package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActivityCloseShift extends Activity {

    private Socketmanager mSockManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.date);

        mSockManager=new Socketmanager(this);
        boolean printOK = false;
        //int g = 1/0;
        Intent intent = getIntent();

        String string = intent.getStringExtra("TextString");
        String printerIP = intent.getStringExtra("printerIP");

        //Toast.makeText(this, "Toast Received with data: " + string + deviceID, Toast.LENGTH_LONG).show();
        intent = new Intent();
        intent.putExtra("string", "это работает!");
        setResult(RESULT_OK, intent);
        finish();
    }


}

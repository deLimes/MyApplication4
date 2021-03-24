package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class ActivityCUT extends Activity {

    private Socketmanager mSockManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.date);

        mSockManager=new Socketmanager(this);
        boolean cutOK = false;
        //int g = 1/0;
        Intent intent = getIntent();

        String string = intent.getStringExtra("string");
        String printerIP = intent.getStringExtra("printerIP");

        //Toast.makeText(this, "Toast Received with data: " + string + deviceID, Toast.LENGTH_LONG).show();

        mSockManager.mPort=9100;
        mSockManager.mstrIp=printerIP;
        mSockManager.threadconnect();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (mSockManager.getIstate()) {

            byte SendCut[]={0x0a,0x0a,0x1d,0x56,0x01};
            if (PrintfData(SendCut)) {
                //PrintfLog("cut paper successed...");
                cutOK = true;
            }
            else {
                //PrintfLog("cutt paper failed...");
            }

        }

        intent = new Intent();
        if (cutOK) {
            intent.putExtra("string", "это работает!");
            setResult(RESULT_OK, intent);
        }else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }

    public boolean PrintfData(byte[]data) {
        mSockManager.threadconnectwrite(data);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (mSockManager.getIstate()) {
            return true;
        }
        else {
            return false;
        }
    }
}

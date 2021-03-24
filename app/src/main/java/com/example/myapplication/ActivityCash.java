package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ActivityCash extends Activity {
    private Socketmanager mSockManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.date);

        mSockManager=new Socketmanager(this);
        boolean cashOK = false;
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

            byte SendCash[]={0x1b,0x70,0x00,0x1e,(byte)0xff,0x00};
            if (PrintfData(SendCash)) {
                //PrintfLog("open cash successed...");
                cashOK = true;
            }
            else {
                //PrintfLog("open cash failed");
            }

        }

        intent = new Intent();
        if (cashOK) {
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

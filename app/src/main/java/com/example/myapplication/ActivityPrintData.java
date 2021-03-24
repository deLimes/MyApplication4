package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class ActivityPrintData extends Activity {

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

            try {
                //if (PrintfData((string+"\nExample4WIFI-posPrinter\n").getBytes("Cp1251"))) {//"ISO-8859-1"))) {//"GBK"))) {
                if (PrintfData((string).getBytes("Cp1251"))) {//"ISO-8859-1"))) {//"GBK"))) {
                    //PrintfLog("send successed...");
                    printOK = true;
                }
                else {
                    //PrintfLog("send failed...");
                    //buttonPf.setEnabled(false);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                //PrintfLog("send data error...");
            }

        }

        intent = new Intent();
        if (printOK) {
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

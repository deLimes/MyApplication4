package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.widget.Toast;

public class ActivityConTest extends Activity {

    private Socketmanager mSockManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.date);

        mSockManager=new Socketmanager(this);
        boolean conTestOK = false;
        //int g = 1/0;
        Intent intent = getIntent();

        String string = intent.getStringExtra("string");
        String printerIP = intent.getStringExtra("printerIP");

        //Toast.makeText(this, "Toast Received with data: " + string + deviceID, Toast.LENGTH_LONG).show();

        if (conTest(printerIP)) {
            //PrintfLog("connected...");
            //buttonCon.setText(getString(R.string.connected));
            //buttonPf.setEnabled(true);
            //buttonCash.setEnabled(true);
            //buttonCut.setEnabled(true);
            conTestOK = true;
        }
        else {
            //PrintfLog("Not connected...");
            //buttonCon.setText(getString(R.string.disconnected));
            //buttonPf.setEnabled(false);
            //buttonCash.setEnabled(false);
            //buttonCut.setEnabled(false);
        }

        intent = new Intent();
        if (conTestOK) {
            intent.putExtra("string", "это работает!");
            setResult(RESULT_OK, intent);
        }else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }

    public boolean conTest(String printerIp) {
        mSockManager.mPort=9100;
        mSockManager.mstrIp=printerIp;
        mSockManager.threadconnect();
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

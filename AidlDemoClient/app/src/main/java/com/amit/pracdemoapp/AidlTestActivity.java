package com.amit.pracdemoapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amit.aidl.IMyAidlInterface;
import com.amit.aidl.StudentInfo;


/**
 * Created by PREM AMIT on 23-Jun-17.
 */

public class AidlTestActivity extends Activity implements View.OnClickListener {

    TextView txt_result;
    EditText edNumb1, edNumb2;
    Button btnAdd, btnStudent;
    IMyAidlInterface aidlInterfaceService;
    AddServiceConnect addServiceConnect;
    private static final String TAG = "AidlTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_test);

        edNumb1 = findViewById(R.id.edtxt_aidl_numb1);
        edNumb2 = findViewById(R.id.edtxt_aidl_numb2);
        txt_result = findViewById(R.id.txt_aidl_add_result);
        btnAdd = findViewById(R.id.btn_aidl_Add);
        btnStudent = findViewById(R.id.btn_aidl_Student);
        btnAdd.setOnClickListener(this);
        btnStudent.setOnClickListener(this);

        initService();
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btn_aidl_Add:
                int numb1 = Integer.parseInt(edNumb1.getText().toString().trim());
                int numb2 = Integer.parseInt(edNumb2.getText().toString().trim());

                if(aidlInterfaceService == null )
                    txt_result.setText( "AIDL Service not available" );
                else {
                    try {
                        int result = aidlInterfaceService.addNumbers(numb1,numb2);
                        txt_result.setText("Result: "+result);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        txt_result.setText( "AIDL Exception" );
                        Log.d(TAG,"****** Remote Exception: "+e.getMessage());
                    }
                }
                break;

            case R.id.btn_aidl_Student:
                String strName = edNumb1.getText().toString().trim();
                String strCourse = edNumb2.getText().toString().trim();

                if(aidlInterfaceService == null )
                    txt_result.setText( "AIDL Service not available" );
                else {
                    try {
                        StudentInfo stdInfo = new StudentInfo();
                        stdInfo.setName(strName);
                        stdInfo.setCourse(strCourse);
                        aidlInterfaceService.setName(stdInfo);
                        StudentInfo result = aidlInterfaceService.getName();
                        txt_result.setText("Result: " +"\n"+ "Name: " +result.getName()+"\n"+ "Course: " +result.getCourse());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        txt_result.setText( "AIDL Exception" );
                        Log.d(TAG,"****** Remote Exception: "+e.getMessage());
                    }
                }
                break;
        }
    }

    private void initService(){
        Toast.makeText(getBaseContext(),"Aidl ADD Service Called", Toast.LENGTH_LONG).show();
        addServiceConnect = new AddServiceConnect();
        Intent intent = new Intent();
        intent.setClassName("com.amit.aidl","com.amit.aidl.IMyAidlRemoteService");
        boolean ret = bindService(intent, addServiceConnect, Context.BIND_AUTO_CREATE);
        Log.d(TAG,"****** Call for Connect *********: "+ret);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(addServiceConnect);
        aidlInterfaceService = null;
    }

    class AddServiceConnect implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlInterfaceService = IMyAidlInterface.Stub.asInterface((IBinder) service);

            Log.d(TAG,"****** Connected with AIDL Service ***********");
            Toast.makeText(getBaseContext(),"Connected with AIDL Service", Toast.LENGTH_LONG).show();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            aidlInterfaceService = null;
            Log.d(TAG,"****** DisconnectedConnected from AIDL Service ***********");
            Toast.makeText(getBaseContext(),"Disconnected from AIDL Service", Toast.LENGTH_LONG).show();
        }
    }
}

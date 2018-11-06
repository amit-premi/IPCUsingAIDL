package com.amit.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by PREM AMIT on 23-Jun-17.
 */
public class IMyAidlRemoteService extends Service {

    private String TAG = "IMyAidlRemoteService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"****** IMyAidlRemoteService started **********");
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new IMyAidlInterface.Stub(){
           StudentInfo studentInfo;
            @Override
            public StudentInfo getName() throws RemoteException {
               return studentInfo;
            }

            @Override
            public void setName(StudentInfo st) throws RemoteException {
               this.studentInfo = st;
            }

            @Override
            public int addNumbers(int ValueFirst, int valueSecond) throws RemoteException {
                return (ValueFirst + valueSecond);
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"****** IMyAidlRemoteService destroyed/killed **********");
    }
}

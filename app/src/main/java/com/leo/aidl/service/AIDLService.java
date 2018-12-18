package com.leo.aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.leo.aidl.IService;

import org.json.JSONException;
import org.json.JSONObject;

public class AIDLService extends Service {
    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    IService.Stub stub = new IService.Stub() {
        @Override
        public String getData(String func, String params) throws RemoteException {
            Log.i("LEO", "接收到请求");
            Log.i("LEO", "func：" + func + "；params：" + params);
            JSONObject jsonObject = new JSONObject();
            switch (func) {
                case "char":
                    try {
                        jsonObject.put("name", "mazaizhong");
                        jsonObject.put("sex", "man");
                        long millis = System.currentTimeMillis();
                        jsonObject.put("time", millis);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case "":
                    break;
            }

            String s = jsonObject.toString();
            return s;
        }
    };
}

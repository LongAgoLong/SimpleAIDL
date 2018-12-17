package com.leo.client.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.leo.aidl.IService;

public class AIDLUtil {
    private static AIDLUtil aidlUtil;
    private IService iService;

    private AIDLUtil() {
    }

    public static AIDLUtil getInstance() {
        if (null == aidlUtil) {
            synchronized (AIDLUtil.class) {
                if (null == aidlUtil) {
                    aidlUtil = new AIDLUtil();
                }
            }
        }
        return aidlUtil;
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("LEO", "绑定成功");
            iService = IService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iService = null;
        }
    };

    public void bindService(Context context) {
        Intent intent = new Intent("com.leo.aidl");
        intent.setPackage("com.leo.aidl");
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void unbindService(Context context) {
        if (null == iService) {
            return;
        }
        Log.i("LEO", "取消绑定");
        context.unbindService(connection);
        iService = null;
    }

    public String request() {
        if (null == iService) {
            Log.i("LEO", "iService为null");
            return null;
        }
        Log.i("LEO", "发起AIDL请求");
        try {
            String data = iService.getData("testFunc", "参数");
            return data;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}

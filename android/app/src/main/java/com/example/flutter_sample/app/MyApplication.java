package com.example.flutter_sample.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.flutter_sample.MainActivity;
import com.example.flutter_sample.route.PageRouter;
import com.taobao.idlefish.flutterboost.FlutterBoostPlugin;
import com.taobao.idlefish.flutterboost.interfaces.IPlatform;

import java.util.Map;

import io.flutter.app.FlutterApplication;

public class MyApplication extends FlutterApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Flutter插件并实现回调
        FlutterBoostPlugin.init(new IPlatform() {
            @Override
            public Application getApplication() {
                return MyApplication.this;
            }

            /**
             * 获取应用入口的Activity,这个Activity在应用交互期间应该是一直在栈底的
             * @return
             */
            @Override
            public Activity getMainActivity() {
                return MainActivity.sRef.get();
            }

            @Override
            public boolean isDebug() {
                return true;
            }

            /**
             * 如果flutter想打开一个本地页面，将会回调这个方法，页面参数将会拼接在url中
             *
             * 例如：sample://nativePage?aaa=bbb
             *
             * 参数就是类似 aaa=bbb 这样的键值对
             *
             * @param context
             * @param url
             * @param requestCode
             * @return
             */
            @Override
            public boolean startActivity(Context context, String url, int requestCode) {
                Log.d("---> MyApplication","startActivity url "+url);
                return PageRouter.openPageByUrl(context,url,requestCode);
            }

            @Override
            public Map getSettings() {
                return null;
            }
        });
    }
}
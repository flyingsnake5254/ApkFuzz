package com.example.apkfuzz.ipc_fuzzing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.apkfuzz.R;
import com.example.apkfuzz.adapters.AppListAdapter;
import com.example.apkfuzz.entities.AppInfo;
import com.example.apkfuzz.util.Test;

import java.util.ArrayList;
import java.util.List;

public class IntentFuzzing extends AppCompatActivity {

    private ListView appList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_fuzzing);

        initial();
        setAppList();
    }

    private void initial() {
        appList = (ListView) findViewById(R.id.lv_applist_intent_fuzzing);
    }

    private void listListener() {
        appList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo selectedApp = (AppInfo) parent.getItemAtPosition(position);
                Test.Log("App Name", selectedApp.getAppName());
                Test.Log("Package Name", selectedApp.getPackageName());
            }
        });
    }
    private void setAppList() {
        PackageManager pm = getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        List<AppInfo> appArrList = new ArrayList<>();

        for (PackageInfo packageInfo : packages) {
            String appName = packageInfo.applicationInfo.loadLabel(pm).toString();
            Drawable appIcon = packageInfo.applicationInfo.loadIcon(pm);
            String packageName = packageInfo.packageName;
            appArrList.add(new AppInfo(appName, appIcon, packageName));
        }

        AppListAdapter adapter = new AppListAdapter(this, appArrList);
        appList.setAdapter(adapter);

        listListener();
    }

    // get Activities, Services, Receivers Info
    private void getApkPackageInfo(String packageName) {
        PackageManager pm = getPackageManager();

    }
}
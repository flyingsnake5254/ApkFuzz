package com.example.apkfuzz.ipc_fuzzing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
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
                getAppPackageInfo(selectedApp);

                Test.Log("App Name", selectedApp.getAppName());
                Test.Log("Package Name", selectedApp.getPackageName());

                sendNullIntent(selectedApp);
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
    private void getAppPackageInfo(AppInfo appInfo) {
        PackageManager pm = getPackageManager();
        String packageName = appInfo.getPackageName();

        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES | PackageManager.GET_RECEIVERS | PackageManager.GET_SERVICES);
            appInfo.setActivities(packageInfo.activities);
            appInfo.setServices(packageInfo.services);
            appInfo.setReceivers(packageInfo.receivers);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendNullIntent(AppInfo appInfo) {
        Test.Log("Start Send Null Intent");
        Intent nullIntent = new Intent();
        String packageName = appInfo.getPackageName();
        ActivityInfo[] activities = appInfo.getActivities();

        // send to activities
        if (activities != null) {
            Test.Log("activity length ", String.valueOf(activities.length));
            for (ActivityInfo activity : activities) {
                nullIntent.setComponent(new ComponentName(packageName, activity.name));
                try {
                    startActivity(nullIntent);
                    Test.Log("Activity Detail");
                    System.out.println("name : " + activity.name);
                    System.out.println("exported : " + activity.exported);
                    System.out.println("permission : " + activity.permission);
                } catch (Exception e) {
                    // System.out.println(e.toString());
                }
            }
        }

        Test.Log("Finish Send Null Intent");
    }
}
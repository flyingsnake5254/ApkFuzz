package com.example.apkfuzz.entities;

import android.content.pm.ActivityInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;

public class AppInfo {
    private String appName;
    private Drawable appIcon;
    private String packageName;
    private ActivityInfo[] activities;
    private ServiceInfo[] services;
    private ActivityInfo[] receivers;

    public AppInfo(String appName, Drawable appIcon, String packageName) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.packageName = packageName;

    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ActivityInfo[] getActivities() {
        return activities;
    }

    public void setActivities(ActivityInfo[] activities) {
        this.activities = activities;
    }

    public ServiceInfo[] getServices() {
        return services;
    }

    public void setServices(ServiceInfo[] services) {
        this.services = services;
    }

    public ActivityInfo[] getReceivers() {
        return receivers;
    }

    public void setReceivers(ActivityInfo[] receivers) {
        this.receivers = receivers;
    }
}

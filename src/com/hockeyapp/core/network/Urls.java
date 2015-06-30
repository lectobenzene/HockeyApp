package com.hockeyapp.core.network;

import org.jetbrains.annotations.NotNull;

/**
 * Created by tsaravana on 6/19/2015.
 */
public class Urls {
    public static final String BASE_URL = "https://rink.hockeyapp.net/api/2";
    public static final String LIST_APPS = "apps";
    private static final String CRASH_REASONS = "crash_reasons";
    // 54ab859f56eb4836ad6f4b6663e859cd

    @NotNull
    public static String getListApps() {
        return String.format("%s/%s", BASE_URL, LIST_APPS);

    }

    public static String getCrashReasons(String appId) {
        return String.format("%s/%s/%s/%s", BASE_URL, LIST_APPS, appId, CRASH_REASONS);
    }
}

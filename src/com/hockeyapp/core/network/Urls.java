package com.hockeyapp.core.network;

import org.jetbrains.annotations.NotNull;

/**
 * Created by tsaravana on 6/19/2015.
 */
public class Urls {
    private static final String BASE_URL = "https://rink.hockeyapp.net/api/2";
    private static final String LIST_APPS = "apps";
    private static final String CRASH_REASONS = "crash_reasons";
    private static final String CRASHES = "crashes";

    // 54ab859f56eb4836ad6f4b6663e859cd

    @NotNull
    public static String getListApps() {
        return String.format("%s/%s", BASE_URL, LIST_APPS);

    }

    @NotNull
    public static String getCrashReasons(String appId) {
        return String.format("%s/%s/%s/%s", BASE_URL, LIST_APPS, appId, CRASH_REASONS);
    }

    @NotNull
    public static String getListOfCrashes(String appId, String crashGroupId) {
        return String.format("%s/%s", getCrashReasons(appId), crashGroupId);
    }

    @NotNull
    public static String getCrash(String appId, String crashId) {
        return String.format("%s/%s/%s/%s/%s", BASE_URL, LIST_APPS, appId, CRASHES, crashId);
    }
}

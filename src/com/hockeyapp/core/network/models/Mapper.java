package com.hockeyapp.core.network.models;

/**
 * Created by tsaravana on 7/2/2015.
 */
public class Mapper {

    public static String getStatus(int statusId) {
        switch (statusId) {
            case 0:
                return "open";
            case 1:
                return "resolved";
            case 2:
                return "ignored";
            default:
                return "unknown";
        }
    }
}

package com.hockeyapp.core.network.models;

import com.intellij.ui.JBColor;

import java.awt.*;

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

    public static Color getStatusColor(int statusId) {
        switch (statusId) {
            case 0:
                return JBColor.decode("0X660000");
            case 1:
                return JBColor.decode("0X004700");
            case 2:
                return JBColor.BLACK;
            default:
                return JBColor.BLACK;
        }
    }
}

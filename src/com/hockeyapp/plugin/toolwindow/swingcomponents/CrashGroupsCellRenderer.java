package com.hockeyapp.plugin.toolwindow.swingcomponents;

import com.hockeyapp.core.network.models.crashreasons.CrashGroups;
import com.hockeyapp.core.network.models.crashreasons.CrashReason;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tsaravana on 7/1/2015.
 */
public class CrashGroupsCellRenderer extends JBPanel implements ListCellRenderer {

    private JPanel contentPanel;
    private JBLabel labelLineNumber;
    private JBLabel labelReason;

    public CrashGroupsCellRenderer() {
        this.add(contentPanel);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        CrashReason entry = (CrashReason) value;
        labelReason.setText(entry.getReason());
        labelLineNumber.setText(entry.getLine());
        if (isSelected) {
            setBackground(JBColor.BLACK);
            setForeground(JBColor.WHITE);
        } else {
            setBackground(JBColor.WHITE);
            setForeground(JBColor.BLACK);
        }
        return this;
    }
}

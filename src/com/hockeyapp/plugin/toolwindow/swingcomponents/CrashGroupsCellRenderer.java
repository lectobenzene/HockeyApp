package com.hockeyapp.plugin.toolwindow.swingcomponents;

import com.hockeyapp.core.network.models.Mapper;
import com.hockeyapp.core.network.models.crashreasons.CrashReason;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by tsaravana on 7/1/2015.
 */
public class CrashGroupsCellRenderer extends JBPanel implements ListCellRenderer {

    int jpContentWidth = 0;
    private JPanel contentPanel;
    private JBLabel lblReason;
    private JBLabel lblClassMethod;
    private JPanel jpContent;
    private JPanel jpCount;
    private JPanel jpDate;
    private JBLabel lblCount;
    private HARoundLabel lblStatus;
    private JBLabel lblLastCrashAt;
    private JBLabel lblVersion;
    private JBLabel lblLineNo;
    private JPanel jpTitle;

    public CrashGroupsCellRenderer() {
        this.setLayout(new BorderLayout(0, 0));
        this.add(contentPanel);
        setOpaque(true);
        setFieldOpaque(false);
        lblStatus.setBorder(new EmptyBorder(2, 2, 2, 2));
    }

    private void setFieldOpaque(boolean isOpaque) {
        contentPanel.setOpaque(isOpaque);
        lblReason.setOpaque(isOpaque);
        lblClassMethod.setOpaque(isOpaque);
        jpContent.setOpaque(isOpaque);
        jpCount.setOpaque(isOpaque);
        jpDate.setOpaque(isOpaque);
        lblCount.setOpaque(isOpaque);
        //lblStatus.setOpaque(isOpaque);
        lblLastCrashAt.setOpaque(isOpaque);
        lblVersion.setOpaque(isOpaque);
        lblLineNo.setOpaque(isOpaque);
        jpTitle.setOpaque(isOpaque);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        CrashReason entry = (CrashReason) value;

        if (index % 2 == 0) {
            setBackground(Color.decode("0XD7D3FF"));
        } else {
            setBackground(Color.decode("0XF6F7FA"));
        }

        lblCount.setText(String.valueOf(entry.getNumberOfCrashes()));
        lblStatus.setText(Mapper.getStatus((entry.getStatus().intValue())));
        lblStatus.setBackground(Mapper.getStatusColor(entry.getStatus().intValue()));

        lblClassMethod.setText(entry.getClass_() + "." + entry.getMethod());
        lblLineNo.setText("line " + entry.getLine());
        lblReason.setText(entry.getReason());

        lblLastCrashAt.setText(entry.getLastCrashAt());
        lblVersion.setText(entry.getBundleShortVersion() + "(" + entry.getBundleVersion() + ")");

        if (isSelected) {
            setBackground(Color.decode("0XFFFF99"));
        }
        return this;
    }
}

package com.hockeyapp.plugin.toolwindow.swingcomponents;

import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tsaravana on 7/5/2015.
 */
public class HARoundLabel extends JBLabel {

    public HARoundLabel() {
        super();
    }

    public HARoundLabel(UIUtil.ComponentStyle componentStyle) {
        super(componentStyle);
    }

    public HARoundLabel(Icon image) {
        super(image);
    }

    public HARoundLabel(String text) {
        super(text);
    }

    public HARoundLabel(String text, UIUtil.ComponentStyle componentStyle) {
        super(text, componentStyle);
    }

    public HARoundLabel(String text, UIUtil.ComponentStyle componentStyle, UIUtil.FontColor fontColor) {
        super(text, componentStyle, fontColor);
    }

    public HARoundLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    public HARoundLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
    }

    public HARoundLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(10, 10);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        //Draws the rounded opaque panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);//paint background
        graphics.setColor(JBColor.WHITE);
        graphics.drawString(getText(), 2, 14);
    }
}

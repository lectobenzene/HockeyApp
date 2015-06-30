package com.hockeyapp.plugin.preferences.configurable;

import com.hockeyapp.plugin.preferences.PreferenceService;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by tsaravana on 6/21/2015.
 */
public class HockeyAppSettings implements Configurable {
    private JPanel contentPanel;
    private JTextField textApiToken;

    @Nls
    @Override
    public String getDisplayName() {
        return "HockeyApp";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        final PreferenceService service = ServiceManager.getService(PreferenceService.class);
        if (service != null) {
            textApiToken.setText(service.getState());
        }
        return contentPanel;
    }


    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        storeData();
    }

    private void storeData() {
        final PreferenceService service = ServiceManager.getService(PreferenceService.class);
        service.loadState(textApiToken.getText());
        System.out.println("service.getState() = " + service.getState());
    }

    @Override
    public void reset() {

    }

    @Override
    public void disposeUIResources() {

    }

}

package com.hockeyapp.plugin.preferences;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by tsaravana on 7/5/2015.
 */
public class HAPreferenceManager {
    private static HAPreferenceManager instance;


    private HAPreferenceManager() {
    }

    @NotNull
    public static HAPreferenceManager getInstance() {
        if (instance == null) {
            synchronized (HAPreferenceManager.class) {
                if (instance == null) {
                    instance = new HAPreferenceManager();
                }
            }
        }
        return instance;
    }

    @Nullable
    public String getAppId(Project project) {
        System.out.println("project = " + project);
        final AssociateApplicationService service = ServiceManager.getService(project, AssociateApplicationService.class);
        return service.getState();
    }
}

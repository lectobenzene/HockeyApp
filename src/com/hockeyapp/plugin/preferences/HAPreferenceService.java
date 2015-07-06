package com.hockeyapp.plugin.preferences;

import com.intellij.openapi.components.*;
import org.jetbrains.annotations.Nullable;

/**
 * Created by tsaravana on 6/21/2015.
 */
@State(name = "HAPreferenceService", storages = {
        @Storage(id = "other", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/hockeyapp.xml", scheme = StorageScheme.DIRECTORY_BASED)
})
public class HAPreferenceService implements PersistentStateComponent<String> {

    private String publicIdentifier;

    @Nullable
    @Override
    public String getState() {
        return publicIdentifier;
    }

    @Override
    public void loadState(String state) {
        this.publicIdentifier = state;
    }
}

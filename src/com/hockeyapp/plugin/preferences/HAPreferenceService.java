package com.hockeyapp.plugin.preferences;

import com.hockeyapp.plugin.preferences.models.DataStore;
import com.intellij.openapi.components.*;
import org.jetbrains.annotations.Nullable;

/**
 * Created by tsaravana on 6/21/2015.
 */
@State(name = "HAPreferenceService", storages = {
        @Storage(id = "other", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/hockeyapp.xml", scheme = StorageScheme.DIRECTORY_BASED)
})
public class HAPreferenceService implements PersistentStateComponent<DataStore> {

    private DataStore dataStore;

    @Nullable
    @Override
    public DataStore getState() {
        return dataStore;
    }

    @Override
    public void loadState(DataStore state) {
        this.dataStore = state;
    }
}

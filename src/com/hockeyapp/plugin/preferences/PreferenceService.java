package com.hockeyapp.plugin.preferences;

import com.hockeyapp.plugin.preferences.models.PreferenceStore;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import org.jetbrains.annotations.Nullable;

/**
 * Created by tsaravana on 6/21/2015.
 */
@State(name = "PreferenceService", storages = {
        @Storage(id = "other", file = StoragePathMacros.APP_CONFIG + "/other.xml")
})
public class PreferenceService implements PersistentStateComponent<PreferenceStore> {

    private PreferenceStore store;

    @Nullable
    @Override
    public PreferenceStore getState() {
        return store;
    }

    @Override
    public void loadState(PreferenceStore state) {
        this.store = state;
    }
}

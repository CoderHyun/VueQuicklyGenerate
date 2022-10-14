package com.hyun.extension;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

@State(name = "vue-generate.cache", storages = {@Storage(value = "vue-generate-cache.xml")})
public class VueQuicklyGenerateCache implements PersistentStateComponent<VueQuicklyGenerateCache> {

    public Map<String, String> vueGenerateCache = new LinkedHashMap<>();

    public static VueQuicklyGenerateCache getInstance(Project project) {
        return project.getService(VueQuicklyGenerateCache.class);
    }

    @Override
    public @Nullable VueQuicklyGenerateCache getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull VueQuicklyGenerateCache state) {
        if (state.vueGenerateCache == null) {
            return;
        }
        this.vueGenerateCache = state.vueGenerateCache;
    }
}

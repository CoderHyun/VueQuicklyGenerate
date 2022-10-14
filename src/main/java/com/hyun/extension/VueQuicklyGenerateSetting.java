package com.hyun.extension;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "vue-generate", storages = {@Storage(value = "vueGenerate.xml")})
public class VueQuicklyGenerateSetting implements PersistentStateComponent<VueQuicklyGenerateSetting> {

    public String author;
    public String email;

    public static VueQuicklyGenerateSetting getInstance() {
        return ApplicationManager.getApplication().getService(VueQuicklyGenerateSetting.class);
    }

    @Override
    public @Nullable VueQuicklyGenerateSetting getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull VueQuicklyGenerateSetting state) {
        this.author = state.author;
        this.email = state.email;
    }
}

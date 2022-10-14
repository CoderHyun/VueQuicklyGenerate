package com.hyun.action;

import com.hyun.extension.VueQuicklyGenerateCache;
import com.hyun.extension.VueQuicklyGenerateSetting;
import com.hyun.utils.VueQuicklyUtils;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class VueQuicklyGenerateAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (VueQuicklyUtils.author == null || VueQuicklyUtils.email == null) {
            Notifications.Bus.notify(new Notification("VueQuicklyGenerate", "Vue快速生成", "检测到还未设置作者、邮箱，请前往Setting -> tools -> vueQuicklyGenerate 设置", NotificationType.ERROR), e.getProject());
            return;
        }
        String text = editor.getSelectionModel().getSelectedText();
        // 获取持久化对象
        Map<String, String> vueCache = VueQuicklyGenerateCache.getInstance(e.getProject()).vueGenerateCache;
        String vueResult;
        // 有缓存就查询缓存，不存在通过API查询
        if (vueCache.containsKey(text)) {
            vueResult = vueCache.get(text);
        } else {
            vueResult = VueQuicklyUtils.getTransResult(text, "auto", "zh");
            vueCache.put(text, vueResult);
        }

    }

}
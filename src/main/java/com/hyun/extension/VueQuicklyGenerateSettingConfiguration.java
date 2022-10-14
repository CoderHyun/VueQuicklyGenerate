package com.hyun.extension;

import javax.swing.*;

import com.hyun.utils.VueQuicklyUtils;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * 供用户自定义配置的类
 */
public class VueQuicklyGenerateSettingConfiguration implements Configurable{

    private final JComponent component;
    private final JTextField author;
    private final JTextField email;
    private final static String authorPlaceholder = "请输入自定义作者名";
    private final static String emailPlaceholder = "请输入自定邮箱";

    public VueQuicklyGenerateSettingConfiguration() {
        // 容器
        this.component = new JPanel();
        this.component.setLayout(new GridLayout(15, 1));

        // 设置作者名和邮箱的文本框
        this.author = new JTextField();
        this.email = new JTextField();


        this.author.addFocusListener(new TextFieldListener(this.author, authorPlaceholder));
        // 如果缓存中存有数据
        if (VueQuicklyGenerateSetting.getInstance().author != null) {
            this.author.setText(VueQuicklyGenerateSetting.getInstance().author);
        } else {
            // 设置输入框提示语
            this.author.setText(authorPlaceholder);
            this.author.setForeground(JBColor.GRAY);
        }

        this.email.addFocusListener(new TextFieldListener(this.email, emailPlaceholder));
        if (VueQuicklyGenerateSetting.getInstance().email != null) {
            this.email.setText(VueQuicklyGenerateSetting.getInstance().email);
        } else {
            this.email.setText(emailPlaceholder);
            this.email.setForeground(JBColor.GRAY);
        }

        this.component.add(this.author);
        this.component.add(this.email);

    }

    @Override
    public String getDisplayName() {
        return "VueQuicklyGenerate";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return component;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    // 点击配置页面中的 apply 按钮或者 OK 按钮，会调用该方法，在该方法中保存配置
    @Override
    public void apply() throws ConfigurationException {
        VueQuicklyGenerateSetting.getInstance().author = author.getText();
        VueQuicklyGenerateSetting.getInstance().email = email.getText();
    }
    static class TextFieldListener implements FocusListener {

        private final String defaultHint;
        private final JTextField textField;

        public TextFieldListener(JTextField textField, String defaultHint) {
            this.defaultHint = defaultHint;
            this.textField = textField;
        }

        @Override
        public void focusGained(FocusEvent e) {
            // 清空提示语，设置为黑色字体
            if (textField.getText().equals(defaultHint)) {
                textField.setText("");
                textField.setForeground(JBColor.BLACK);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            // 如果内容为空，设置提示语
            if (textField.getText().equals("")) {
                textField.setText(defaultHint);
                textField.setForeground(JBColor.GRAY);
            }
        }
    }
}

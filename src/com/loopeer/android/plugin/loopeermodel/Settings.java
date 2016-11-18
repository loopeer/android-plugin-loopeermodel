package com.loopeer.android.plugin.loopeermodel;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class Settings implements Configurable {
    public static final String MODIFIERS = "loopeer_model_default_modifier";
    // TODO: 2016/11/18
    public static final String SEPARATOR = "loopeer_model_default_separator";

    private JPanel mPanel;
    private JComboBox mComboBox;

    @Nls
    @Override
    public String getDisplayName() {
        return "LoopeerModel";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        reset();
        return mPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        PropertiesComponent.getInstance().setValue(MODIFIERS,String.valueOf(mComboBox.getSelectedItem()));
    }

    @Override
    public void reset() {
        mComboBox.setSelectedItem(getModifier());
    }

    @Override
    public void disposeUIResources() {

    }

    private static String getModifier(){
        return PropertiesComponent.getInstance().getValue(MODIFIERS,"public");
    }

    public static String getModifierText(){
        String modifier = PropertiesComponent.getInstance().getValue(MODIFIERS,"public");
       return modifier.equals("no modifier") ? "":modifier;
    }

    private static String getSeparator(){
        return null;
    }
}

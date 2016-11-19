package com.loopeer.android.plugin.loopeermodel;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.loopeer.android.plugin.loopeermodel.model.VariableEntity;
import com.loopeer.android.plugin.loopeermodel.utils.FormatUtils;
import com.loopeer.android.plugin.loopeermodel.utils.NotificationUtils;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class FormatDialog extends JFrame {

    private PsiClass mClz;
    private PsiFile mFile;
    private Project mProject;

    private JPanel mContentPanel;
    private JButton mBtnCancel;
    private JButton mBtnConfirm;
    private JLabel mClzLabel;
    private JButton mBtnFormat;
    private JTextArea mTextContent;
    private JLabel mErrorLabel;

    public FormatDialog(PsiClass clz, PsiFile file, Project project, Editor editor) throws HeadlessException {
        mClz = clz;
        mFile = file;
        mProject = project;
        initUIComponents();
        initListeners();
    }

    private void initUIComponents() {
        setContentPane(mContentPanel);
        setTitle("loopeer format");

        String className = ((PsiJavaFileImpl) mFile).getPackageName() + "." + mFile.getName().split("\\.")[0];
        mClzLabel.setText(className);

    }

    private void initListeners() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        mBtnConfirm.addActionListener(e -> onOk());
        mBtnCancel.addActionListener(e -> onCancel());
        mBtnFormat.addActionListener(e -> onFormat());
    }


    private void onFormat() {
        if(!checkValid()) return;
        if (!FormatUtils.isContentValid(mTextContent.getText())) {
            mErrorLabel.setText("data error!");
            NotificationUtils.showErrorNotification(mProject, "data error!");
            return;
        }
        mTextContent.setText(FormatUtils.formatContent(mTextContent.getText()));
        mErrorLabel.setText("");
    }

    private void onOk() {
        if(!checkValid()) return;
        ArrayList<VariableEntity> entities = generateVariables(mTextContent.getText());
        DataWriter writer = new DataWriter(mFile, mClz, "generate model", entities);
        writer.setCommandFinishListener(() -> {
            reset();
            onCancel();
        });
        writer.execute();
    }

    private ArrayList<VariableEntity> generateVariables(String content) {
        ArrayList<VariableEntity> entities = FormatUtils.generateContent(content);
        if (entities != null) {
            for (int i = 0; i < entities.size(); i++) {
                boolean isExist = mClz.findFieldByName(entities.get(0).formatName, false) != null;
                if (isExist) {
                    entities.remove(i);
                    i--;
                }
            }
        }
        NotificationUtils.showInfoNotification(mProject, entities.size() + " variables generated successfully");
        return entities;

    }

    private boolean checkValid(){
        String content = mTextContent.getText();

        if(TextUtils.isEmpty(content)) {
            mErrorLabel.setText("empty content!");
            return false;
        }
        if (!FormatUtils.isContentValid(content)) {
            mErrorLabel.setText("data error!");
            NotificationUtils.showErrorNotification(mProject, "data error!");
            return false;
        }
        return true;
    }


    private void reset() {
        mTextContent.setText("");
        mErrorLabel.setText("");
    }


    private void onCancel() {
        dispose();
    }


    public void display() {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

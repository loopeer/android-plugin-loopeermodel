package com.loopeer.android.plugin.loopeermodel;


import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.loopeer.android.plugin.loopeermodel.model.VariableEntity;

import java.util.ArrayList;

public class DataWriter extends WriteCommandAction.Simple {
    private Project mProject;
    private PsiFile mFile;
    private PsiClass mClass;
    private PsiElementFactory mFactory;
    private ArrayList<VariableEntity> mEntities;

    protected DataWriter(PsiFile file, PsiClass clazz, String commandName, ArrayList<VariableEntity> entities) {
        super(clazz.getProject(), commandName);
        mFile = file;
        mProject = clazz.getProject();
        mClass = clazz;
        mEntities = entities;
        mFactory = JavaPsiFacade.getElementFactory(mProject);

    }


    @Override
    protected void run() throws Throwable {

        generateVariables();
        // import class needed
        JavaCodeStyleManager styleManager = JavaCodeStyleManager.getInstance(mProject);
        styleManager.optimizeImports(mFile);
        styleManager.shortenClassReferences(mClass);
        //reformat
        new ReformatCodeProcessor(mProject, mClass.getContainingFile(), null, false).runWithoutProgress();
    }


    private void generateVariables(){
        // add variables
        for (VariableEntity entity:
             mEntities) {
            StringBuilder variable = new StringBuilder();
            if(entity.isSerialized) variable.append(String.format("@SerializedName(\"%s\")%n",entity.name));
            variable.append(entity.modifier);
            variable.append(" ");
            variable.append(entity.type);
            variable.append(" ");
            variable.append(entity.formatName);
            variable.append(";");
            variable.append(" ");
            variable.append("//");
            variable.append(entity.note);
            mClass.add(mFactory.createFieldFromText(variable.toString(), mClass));
        }
    }
}

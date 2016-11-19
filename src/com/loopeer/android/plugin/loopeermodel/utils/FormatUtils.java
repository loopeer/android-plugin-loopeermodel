package com.loopeer.android.plugin.loopeermodel.utils;


import com.loopeer.android.plugin.loopeermodel.Settings;
import com.loopeer.android.plugin.loopeermodel.model.VariableEntity;
import org.apache.http.util.TextUtils;

import java.util.ArrayList;

public final class FormatUtils {

    private static final String HEADER_REGEX = ".*\\s\\[.*\\]";


    public static ArrayList<VariableEntity> generateContent(String content) {
        ArrayList<VariableEntity> entities = new ArrayList<>();
        String[] lines = content.split("\\n");
        int start = hasHeader(lines[0]) ? 1 : 0;
        for (int i = start; i < lines.length; i++) {
            entities.add(generateLine(lines[i]));
        }
        return entities;
    }

    private static VariableEntity generateLine(String line) {
        VariableEntity entity = new VariableEntity();
        line = line.replace("*", "").trim();
        String[] s = line.split("：");
        boolean isFormat = s[0].equals(formatVariable(s[0]));
        if (!isFormat) {
            entity.isSerialized = true;
        }
        entity.name = s[0];
        entity.formatName = formatVariable(s[0]);
        entity.modifier = Settings.getModifierText();
        entity.type = "String";
        entity.note = s[1];
        return entity;
    }

    private static String formatVariable(String var) {
        String[] vars = var.split("_");
        if (var.length() == 0) return var;
        StringBuilder sb = new StringBuilder();
        sb.append(vars[0]);
        for (int i = 1; i < vars.length; i++) {
            char first = vars[i].charAt(0);
            sb.append(Character.toUpperCase(first));
            sb.append(vars[i].substring(1));
        }
        return sb.toString();
    }

    public static String formatContent(String content) {
        String[] lines = content.split("\\n");
        int start = hasHeader(lines[0]) ? 1 : 0;
        if (start == 1) {
            lines[0] = formatHeader(lines[0]);
        }
        for (int i = start; i < lines.length; i++) {
            lines[i] = lines[i].replace("*", "").trim();
        }
        return Array2WrapString(lines);
    }

    private static String formatHeader(String header) {
        return header.replace("###", "").trim();
    }

    public static boolean isContentValid(String content) {
        content = formatContent(content);
        if (TextUtils.isEmpty(content)) return false;
        String[] lines = content.split("\\n");
        if (lines.length == 0) return false;
        String head = lines[0];
        int start = hasHeader(head) ? 1 : 0;
        for (int i = start; i < lines.length; i++) {
            if (!isLineValid(lines[i])) return false;
        }
        return true;
    }

    private static boolean hasHeader(String header) {
        return header.matches(HEADER_REGEX);
    }

    private static boolean isLineValid(String line) {
        line = line.trim();
        return line.contains("：") && line.split("：").length == 2;
    }



    private static String Array2WrapString(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            if(!TextUtils.isEmpty(s)) {
                sb.append(s);
                sb.append("\n");
            }
        }
        return sb.toString();
    }


}

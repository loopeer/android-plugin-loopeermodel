package com.loopeer.android.plugin.loopeermodel.model;

public enum Modifier {

    PUBLIC("public"),
    PROTECTED("protected"),
    NO_MODIFIER("no_modifier"),
    PRIVATE("private");

    String tag;

    Modifier(String tag) {
        this.tag = tag;
    }
}

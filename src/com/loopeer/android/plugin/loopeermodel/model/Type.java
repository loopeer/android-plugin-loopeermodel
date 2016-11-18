package com.loopeer.android.plugin.loopeermodel.model;


public enum Type {
    INT("int"),
    LONG("long"),
    FLOAT("float"),
    DOUBLE("double"),
    STRING("String");

    String tag;

    Type(String tag) {
        this.tag = tag;
    }
}

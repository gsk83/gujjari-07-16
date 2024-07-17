package com.app.tool_rental.models;

public enum ToolCode {
    CHNS("CHNS"),
    LADW("LADW"),
    JAKD("JAKD"),
    JAKR("JAKR");

    private final String toolCode;

    ToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    @Override
    public String toString() {
        return this.toolCode;
    }
}

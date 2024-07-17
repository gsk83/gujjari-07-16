package com.app.tool_rental.models;

public enum ToolType {
    CHAINSAW("Chainsaw"),
    LADDER("Ladder"),
    JACKHAMMER("Jackhammer");

    private final String toolType;

    ToolType(String toolType) {
        this.toolType = toolType;
    }

    @Override
    public String toString() {
        return this.toolType;
    }
}

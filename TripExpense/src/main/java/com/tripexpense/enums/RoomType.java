package com.tripexpense.enums;

public enum RoomType {
    SINGLE("Individual"),
    DOUBLE("Doble"),
    TWIN("Twin (dos camas individuales)"),
    TRIPLE("Triple"),
    SUITE("Suite"),
    FAMILY("Familiar"),
    KING("King"),
    QUEEN("Queen"),
    DELUXE("De lujo"),
    STUDIO("Estudio"),
    PENTHOUSE("Ático/Penthouse"),
    BUNGALOW("Bungalow"),
    CABIN("Cabaña"),
    VILLA("Villa");

    private final String displayName;

    RoomType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

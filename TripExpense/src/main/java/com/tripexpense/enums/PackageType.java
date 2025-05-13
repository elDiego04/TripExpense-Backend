package com.tripexpense.enums;

public enum PackageType {
    ALL_INCLUSIVE("Todo incluído"),
    ADVENTURE("Aventura"),
    ROMANTIC("Romántico"),
    FAMILY("Familiar"),
    LUXURY("Lujo"),
    BUSINESS("Negocios");

    private final String displayName;

    PackageType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

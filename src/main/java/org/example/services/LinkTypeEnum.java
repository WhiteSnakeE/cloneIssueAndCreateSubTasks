package org.example.services;

public enum LinkTypeEnum {
    CLONERS("Cloners"),
    RELATES("Relates");

    public final String linkType;

     LinkTypeEnum (String linkType) {
        this.linkType = linkType;
    }
}

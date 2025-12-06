package com.bc.bissapp.config;

/**
 * Project Metadata - Contains ownership and version information This file is
 * part of the BissApp project
 */
public final class ProjectMetadata {

    // Original developer and project owner
    public static final String PROJECT_OWNER = "Amine Hached";
    public static final String PROJECT_NAME = "BissApp";
    public static final String VERSION = "0.0.1";

    // Ownership watermark - embedded in application
    public static final String OWNERSHIP_WATERMARK = "© " + PROJECT_OWNER + " - " + PROJECT_NAME;

    private ProjectMetadata() {
        // Private constructor to prevent instantiation
    }

    /**
     * Returns the project watermark with owner information
     */
    public static String getWatermark() {
        return OWNERSHIP_WATERMARK;
    }

    /**
     * Returns full project identity
     */
    public static String getProjectIdentity() {
        return PROJECT_NAME + " v" + VERSION + " - " + OWNERSHIP_WATERMARK;
    }
}
